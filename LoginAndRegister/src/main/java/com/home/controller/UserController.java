package com.home.controller;

import com.github.pagehelper.dialect.helper.HsqldbDialect;
import com.home.entity.CommonResult;
import com.home.entity.Router;
import com.home.entity.User;
import com.home.entity.UserInFo;
import com.home.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;


@RestController
@Slf4j
public class UserController {

    @Resource
    UserServiceImpl userService;

    @PostMapping(path = "/ULogin")
    public CommonResult UserLogin(@RequestBody Map<String,String> map,
                                  HttpSession session)  {
        System.out.println("用户登陆了");
        BigInteger userId = new BigInteger(map.get("username"));
        String password = map.get("password");
        if (session.getAttribute(userId.toString()) == null){
            User user = userService.UserLogin(userId);
            if (user == null){
                return new CommonResult(300,"不存在该用户");
            }
            if (!user.getUserpassword().equals(password)){
                return new CommonResult(300,"密码错误");
            }
            //封装token返回
            Map<String,String> token = new HashMap<>();
            token.put("token",userId.toString());
            session.setAttribute(userId.toString(),userId);
            return new CommonResult(200,"登陆成功",token);
        }
           return new CommonResult(200,"账号在登陆中");
    }
    //获取用户信息
    @GetMapping(path = "/ULoginInfo")
    public CommonResult UserLoginInfo(@RequestParam("token") String token,
                                      HttpSession session){
        System.out.println(session.getId());
        System.out.println(token);
        System.out.println("获取用信息了");
        BigInteger stuid2 = new BigInteger(token);
        UserInFo userInFo = userService.UserLoginInfo(stuid2);
        return new CommonResult(200,"登陆成功",userInFo);
    }

    //用户注销
    @PostMapping(path = "/ULoginOut")
    public CommonResult UserLogOut( @RequestParam("token") String token,
                                   HttpSession session){
        System.out.println("用户注销了");
        session.removeAttribute(token);
        return new CommonResult(200,"退出成功");
    }
    @PostMapping(path = "/URegister")
    //用户注册
    public CommonResult UserRegister(@RequestBody @Validated User user){

        int res = userService.UserRegister(user);
        switch (res){
            case 0:
                return new CommonResult(200,"注册失败请重新注册");
            case 1:
                return new CommonResult(200,"注册成功");
            case 2:
                return new CommonResult(200,"用户已存在");
            default:
                throw  new RuntimeException();
        }
    }

    //修改密码
    @PostMapping(path = "/UPwdUpdate")
    public CommonResult UserPasswordUpdate(@RequestParam(value = "userid") BigInteger userid ,
                                           @RequestParam(value = "password" )String password) {

        int res = userService.UserPasswordUpdate(userid,password);
        if (res > 0){
            return new CommonResult(200,"修改成功");
        }else {
            return new CommonResult(400,"修改失败");
        }
    }

    //获取角色对应路由表
    @GetMapping(path = "/GetRouter")
    public CommonResult GetRouters(@RequestParam("roles") String role){
        System.out.println("获取角色成功");
        System.out.println(role);
        List<String> roles = new ArrayList<>();
        //匹配角色信息
        for(int i = 0; i<role.length(); i++){
            if (role.charAt(i) == '='){
                StringBuilder str = new StringBuilder();
                for (int j = ++i;j<role.length();j++){
                    if (role.charAt(j) == '&'){
                        break;
                    }else {
                        str.append(role.charAt(j));
                    }
                }
                roles.add(str.toString());
            }
        }
        System.out.println(roles);

      try {
          Map<String,Object> map = new LinkedHashMap<>();
          List<Object> list = new LinkedList<>();
          for (String strs : roles){
              BigInteger bigInteger = new BigInteger(strs);
              List<Object> lst = userService.GetUserRouters(bigInteger);
              list.addAll(lst);
          }
          map.put("Routers",list);
          System.out.println(map);
          return new CommonResult(200,"成功",map);
      }catch (Exception e){
          return new CommonResult(200,"失败",e.getMessage());
      }
    }


    //获取所有路由表
    @GetMapping(path = "/GetAllRouter")
    public CommonResult GetAllRouter(){
       List<Object> routerlist =  userService.GetAllRouter();
       return new CommonResult(200,"全部路由",routerlist);
    }

    //根据角色id获取对应路由
    @GetMapping(path = "/GetRolesAndRouter")
    public CommonResult GetRolesAndRouter(){
        List<Object> rolesAndRouter =  userService.GetRolesAndRouter();
        return new CommonResult(200,"全部路由",rolesAndRouter);
    }

    //根据角色id获取对应路由
    @DeleteMapping(path = "/DeletRole/{id}")
    public CommonResult DeletRole(@PathVariable("id") String roleid){
        boolean flag =  userService.DeletRole(roleid);
        if (flag){
            return new CommonResult(200,"成功");
        }
        return new CommonResult(500,"出错");
    }

    //根据角色id修改对应路由
    @PutMapping(path = "/UpdateRole/{id}")
    public CommonResult UpdateRole(@PathVariable("id") String roleid,
                                   @RequestBody Map<String,Object> map){

        boolean flag = userService.UpdateRole(roleid,map);
     if (flag){
         return new CommonResult(200,"正常");
     }else {
         return new CommonResult(500,"出错");
     }

    }

    //根据角色id修改对应路由
    @PostMapping(path = "/AddRole")
    public CommonResult AddRole(@RequestBody Map<String,Object> map){
      String roleid =  userService.AddRole(map);
        Map<String,String> data= new HashMap<>();
        data.put("key",roleid);
      return new CommonResult(200,"正确",data);
    }

    //未处理的原路由表单
    @GetMapping(path = "/AllRouter")
    public CommonResult AllRouter(){
        List<Router> routerList = userService.AllRouter();
        return new CommonResult(200,"成功",routerList);
    }

//    @ResponseBody
//    @GetMapping(path = "/test")
//    public String test(){
//        return "sss";
//    }
}
