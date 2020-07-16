package com.home.service;

import com.alibaba.fastjson.JSON;
import com.home.controller.compoment.RouterFactry;
import com.home.dao.ManagerDao;
import com.home.dao.UserDao;
import com.home.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.*;

@Service
public class UserServiceImpl {

    @Resource
    private UserDao userDao;

    @Resource
    private ManagerDao managerDao;

    public User UserLogin(BigInteger userId) {
        List<User> users = userDao.UserLogin(userId);
        if (users == null || users.size() == 0)
            return null;
        return users.get(0);
    }

    //返回 1代表成功 0 代表失败 2代表已存在
    public int UserRegister(User user) {

        List<User> users = userDao.UserLogin(user.getUserId());
        if (users.size() == 0) {
            int res = userDao.UserRegister(user);
            if (res > 0) {
                return 1;
            } else {
                return 0;
            }
        }
        return 2;
    }

    public int UserPasswordUpdate(BigInteger userid ,String password) {

            int res = userDao.UserPasswordUpdate(userid,password);
            if (res > 0) {
                return 1;
            }
                return 0;
    }


    public UserInFo UserLoginInfo( BigInteger userid){
        UserInFo userInFo = null;
        List<UserInFo> userInFos = userDao.UserLoginInfo(userid);
        List<String> role = userDao.GetUserRoles(userid);
        if (userInFos == null){

        }else {
            userInFo = userInFos.get(0);
            userInFo.setRoles(role);
        }
        return userInFo;
    }

    public List<Object> GetUserRouters(BigInteger roleid){
       List<Router> routers =  userDao.GetUserRouters(roleid);
       //无特殊权限
       if (routers == null){
            List<Object> lst = new ArrayList<>();
            return lst;
        }
       List<Router> routerList = new LinkedList<>();
       //路由排版
      for (Router router : routers){
          if (router.getParent() == null){
              routerList.add(FilterRoutes(routers,router));
          }
      }
      //路由包装
        List<Object> lst = RouterFactry.RouterMapFactry(routerList);

        return lst;
    }
    //获取所有路由
    public List<Object> GetAllRouter(){
      List<Router> routerLists =  userDao.GetAllRouter();
        List<Router> routerList = new LinkedList<>();
        for (Router router : routerLists){
            if (router.getParent() == null){
                routerList.add(FilterRoutes(routerLists,router));
            }
        }
        List<Object> lst = RouterFactry.RouterMapFactry(routerList);
        return lst;
    }
    //路由排版
    public Router FilterRoutes(List<Router> routerList,Router router /*传进来父节点*/) {
        List<Router> routerchiled = new LinkedList<>();
        //传入父节点
        //找到父节点的所有子节点
        for (Router Tem : routerList) {
            if (Tem.getParent()!= null && Tem.getParent().equals(router.getName())) {
                //判断之中的节点是不是子节点
                for (Router routerp : routerList) {
                    if (routerp.getParent() != null && routerp.getParent().equals(Tem.getName())) {
                        //如果该节点还有子节点，则递归
                        FilterRoutes(routerList, Tem);
                        break;
                    }
                }
                //该节点没有子节点了,将该节点加入子节点队列
                routerchiled.add(Tem);
            }
        }
        //子节点全部加入父节点
        router.setChildren(routerchiled);
        return router;
    }

    public List<Object> GetRolesAndRouter(){
        List<Role> roleList = managerDao.GetAllRoles();
        List<Object> roleAndRouter = new LinkedList<>();
        for (Role role1 : roleList){
            List<Router> routerlists = userDao.GetRoleRouter(role1.getRoleid());
            List<Router> routerList = new LinkedList<>();
            //路由排版
            for (Router router : routerlists){
                if (router.getParent() == null){
                    routerList.add(FilterRoutes(routerlists,router));
                }
            }
            //路由包装
            List<Object> lst = RouterFactry.RouterMapFactry(routerList);
            Map<String,Object> role = new HashMap<>();
            role.put("key",role1.getRoleid());
            role.put("name",role1.getRolename());
            role.put("description",role1.getRoledecribe());
            role.put("routes",lst);
            roleAndRouter.add(role);
        }
        return roleAndRouter;
    }

    //删除角色
    @Transactional
    public boolean DeletRole(String roleid){
        int res = userDao.DeletRole(new BigInteger(roleid));
        if (res>=1){
            return true;
        }else {
            return false;
        }

    }

    //修改角色匹配的路由，我将路由拆成了最小单位和用户一一匹配
    @Transactional
    public boolean UpdateRole( String roleid,Map<String,Object> map){

        // List<Router> routerList = userDao.GetRoleRouter(new BigInteger(roleid));
        List<Object> routers = new LinkedList<>();
        //拆解成单路由
        List parentRouter = (List) map.get("routes");
        if (parentRouter != null && parentRouter.size()!=0){
            for (Object object:parentRouter){
                List<Object> routerLists = new LinkedList<>();
                Map parent = (Map) object;
                //获取父节点里的特殊字段
                Map<String,Object> parentMap= new HashMap<>();
                Map<String,Object> routertem= new HashMap<>();
                for (Object key : parent.keySet()){
                    if (key == null){
                        continue;
                    }
                    if (key == "children"){
                        continue;
                    }
                    if (key == "name"){
                        parentMap.put("name",parent.get("name"));
                        continue;
                    }
                    routertem.put((String) key,parent.get(key));
                }
                parentMap.put("parent",null);
                parentMap.put("routertem",JSON.toJSONString(routertem));
                routers.add(parentMap);
                //获取该节点的所有子节点
                List<Object> res = DisassemblyRouter(routerLists,object,null);
                routers.addAll(res);
            }
        }

        int res = 0;
        // 删除角色的所有权限，并重新赋权限
        //删除所有权限
        int del = userDao.DeletRoleAllRouter(new BigInteger(roleid));
        //赋予选中权限
        for (Object obj : routers){
            Map router = (Map) obj;
            BigInteger roleId = new BigInteger(roleid);
            String name = (String) router.get("name");
            String routertem = (String) router.get("routertem");
            String parent = (String) router.get("parent");
           int insert = userDao.AddRoleRouter(roleId,name,routertem,parent);
        }
        return true;
    }

    public List<Object> DisassemblyRouter(List<Object> routerList,Object router,Object ParentRouter){
        Map RouterP = (Map)router;
        List childrens = (List) RouterP.get("children");
        if ( childrens != null && childrens.size()!=0){
            for (Object object : childrens){
                DisassemblyRouter(routerList,object,RouterP);
            }
        }else {
            Map<String,Object> RouterMap= new HashMap<>();
            Map<String,Object> routertem= new HashMap<>();
            for (Object key : RouterP.keySet()){
                if (key == null){
                    continue;
                }
                if (key == "children"){
                    continue;
                }
                if (key == "name"){
                    RouterMap.put("name",RouterP.get("name"));
                    continue;
                }
                routertem.put((String) key,RouterP.get(key));
            }
            Map parentRouter = (Map) ParentRouter;
            RouterMap.put("parent",parentRouter.get("name"));
            RouterMap.put("routertem", JSON.toJSONString(routertem));
            routerList.add(RouterMap);
        }
        return routerList;
    }

    //添加角色，同时添加角色对用路由
    @Transactional
    public String AddRole(Map<String,Object> map){
        String rolename = (String) map.get("name");
        String rolesdescribe = (String) map.get("description");
         userDao.AddRole(rolename,rolesdescribe);
        List<Object> routers = new LinkedList<>();
        List parentRouter = (List) map.get("routes");
        if (parentRouter != null && parentRouter.size()!=0){
            for (Object object:parentRouter){
                List<Object> routerLists = new LinkedList<>();
                Map parent = (Map) object;
                //获取父节点里的特殊字段
                Map<String,Object> parentMap= new HashMap<>();
                Map<String,Object> routertem= new HashMap<>();
                for (Object key : parent.keySet()){
                    if (key == null){
                        continue;
                    }
                    if (key == "children"){
                        continue;
                    }
                    if (key == "name"){
                        parentMap.put("name",parent.get("name"));
                        continue;
                    }
                    routertem.put((String) key,parent.get(key));
                }
                parentMap.put("parent",null);
                parentMap.put("routertem",JSON.toJSONString(routertem));
                routers.add(parentMap);
                //获取该节点的所有子节点
                List<Object> res = DisassemblyRouter(routerLists,object,null);
                routers.addAll(res);
                 }
             }
        //赋予选中权限
        String roleid = userDao.FindRoleByName(rolename);
        for (Object obj : routers){
            Map router = (Map) obj;
            BigInteger roleId = new BigInteger(roleid);
            String name = (String) router.get("name");
            String routertem = (String) router.get("routertem");
            String parent = (String) router.get("parent");
            int insert = userDao.AddRoleRouter(roleId,name,routertem,parent);
        }
        return roleid;
    }

    // 查询全部路由（不做处理）
    public List<Router> AllRouter(){
        return userDao.AllRouter();
    }

    //修改用户头像
    public void UpdateUserAvatar(String avatar,String userId){
    }
}
