package com.home.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.home.dao.ManagerDao;
import com.home.entity.*;
import jdk.management.resource.internal.inst.FileOutputStreamRMHooks;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.*;

@Service
public class ManagerServiceImpl {
    @Resource
    ManagerDao managerDao;

    public Manager ManagerLogin(BigInteger managerId) {
        List<Manager> manager = managerDao.ManagerLogin(managerId);
        if (manager == null || manager.size() == 0)
            return null;
        return manager.get(0);
    }

    public ManagerInFo GetManagerInFo(BigInteger managerId) {
        ManagerInFo managerInFo = null;
        List<Object> roles = managerDao.GetManagerRoles(managerId);
        List<ManagerInFo> manager = managerDao.GetManagerInFo(managerId);
        if (manager == null) {

        } else {
            managerInFo = manager.get(0);
            managerInFo.setRoles(roles);
        }
        return managerInFo;
    }

    //返回 1代表成功 0 代表失败 2代表已存在
    public int ManagerRegister(Manager manager) {

        List<Manager> managers = managerDao.ManagerLogin(manager.getManagerId());
        if (managers.size() == 0) {
            int res = managerDao.ManagerRegister(manager);
            if (res > 0) {
                return 1;
            } else {
                return 0;
            }
        }
        return 2;
    }

    public int ManagerPasswordUpdate(BigInteger managerid, String password) {

        int res = managerDao.ManagerPasswordUpdate(managerid, password);
        if (res > 0) {
            return 1;
        }
        return 0;
    }

    public List<Object> GetAllManager() {
        List<Manager> managers = managerDao.GetAllManager();
        List<Object> managerlist = new LinkedList<>();
        for (Manager manager : managers) {
            List<ManagerInFo> managerInFos = managerDao.GetManagerInFo(manager.getManagerId());
            List<Object> rolesids = managerDao.GetManagerRoles(manager.getManagerId());
            List<Object> roleList = new LinkedList<>();
            for (Object roleid : rolesids) {
                List<Role> roles = managerDao.ShwoRoles(new BigInteger(roleid.toString()));
                roleList.add(roles.get(0));
            }
            ManagerInFo manager1 = managerInFos.get(0);
            manager1.setRoles(roleList);
            JSON jsons1 = (JSON) JSON.toJSON(manager);
            Map paramMap1 = (Map) JSONObject.parseObject(jsons1.toString());
            JSON jsons2 = (JSON) JSON.toJSON(manager1);
            Map paramMap2 = (Map) JSONObject.parseObject(jsons2.toString());
            paramMap1.putAll(paramMap2);
            managerlist.add(paramMap1);
        }
        return managerlist;

    }

    public Map<String, Object> GetManagerById(BigInteger managerId) {

        List<Manager> managers = managerDao.ManagerLogin(managerId);
        List<ManagerInFo> managerInFo = managerDao.GetManagerInFo(managers.get(0).getManagerId());

        JSON jsons1 = (JSON) JSON.toJSON(managers.get(0));
        Map paramMap1 = (Map) JSONObject.parseObject(jsons1.toString());
        JSON jsons2 = (JSON) JSON.toJSON(managerInFo.get(0));
        Map paramMap2 = (Map) JSONObject.parseObject(jsons2.toString());
        paramMap1.putAll(paramMap2);

        return paramMap1;
    }

    public Boolean ChangeManagerInfo(String managerid, String managername, String introduction) {

        int res = managerDao.ChangeManagerInfo(managerid, managername, introduction);
        if (res == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public Boolean DeletManager(String managerid) {
        //从用户表中删除用户
        int res = managerDao.Deletmanager(new BigInteger(managerid));
        //从用户-角色表中删除用户的角色
        int re = managerDao.DeletManagerRole(new BigInteger(managerid));
        return true;
    }

    public Map<String, Object> GetManagerRoles(BigInteger managerId) {

        List<Role> roleList = managerDao.GetAllRoles();

        List<Role> managerRoles = managerDao.GetManagerRolesPlus(managerId);
        Map<String, Object> managersroles = new LinkedHashMap<>();
        managersroles.put("list1", roleList);
        managersroles.put("list2", managerRoles);
        return managersroles;
    }

    public boolean AddManagerRoles(List roles, BigInteger managerId) {
        boolean flag = true;
        for (int i =0; i < roles.size(); i++) {
          int res = managerDao.AddManagerRoles(new BigInteger(roles.get(i).toString()), managerId);
          if (res >1)flag = false;
        }
        return flag;
    }

    public boolean DeletManagerRoles(List roles, BigInteger managerId) {
        boolean flag = true;
        for (int i= 0;i<roles.size();i++) {
            int res = managerDao.DeletManagerRoles(new BigInteger(roles.get(i).toString()),managerId);
            if (res >1)flag = false;
        }
        return flag;
    }

    public boolean AddManager(String managerId,String managerName,
                              String introduction,String managerpassword){
        String avatar = "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif";
     int res = managerDao.AddManager(new BigInteger(managerId),managerName,
                                managerpassword,avatar,introduction);
        if (res>=1){
            return true;
        }else {
            return false;
        }
    }
}
