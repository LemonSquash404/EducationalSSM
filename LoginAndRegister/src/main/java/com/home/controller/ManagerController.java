package com.home.controller;


import com.home.entity.*;
import com.home.service.ManagerServiceImpl;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
public class ManagerController {

    @Resource
    ManagerServiceImpl managerService;

    @PostMapping(path = "/MLogin")
    //要使用用户id登录
    public CommonResult UserLogin(@RequestBody Map<String,String> map,
                                  HttpSession session){
        System.out.println("管理员登陆了");
        BigInteger userId = new BigInteger(map.get("username"));
        String password = map.get("password");
        if (session.getAttribute(userId.toString()) == null){
            Manager manager = managerService.ManagerLogin(userId);
            if (manager == null){
                return new CommonResult(300,"不存在该用户");
            }
            if (!manager.getManagerpassword().equals(password)){
                return new CommonResult(300,"密码错误");
            }
            //封装token返回
            Map<String,String> token = new HashMap<>();
            token.put("token",userId.toString());
            session.setAttribute(userId.toString(),userId);
            System.out.println(userId);
            return new CommonResult(200,"登陆成功",token);
        }
        return new CommonResult(200,"账号在登陆中");

    }

    @GetMapping(path = "/MLoginInFo")
    public CommonResult GetManagerInFo(@RequestParam("token") String token,
                                  HttpSession session){
        System.out.println(token);
        System.out.println("获取管理员信息了");
        BigInteger managerId = new BigInteger(token);
        ManagerInFo managerInFo = managerService.GetManagerInFo(managerId);
        return new CommonResult(200,"登陆成功",managerInFo);
    }

    @PostMapping(path = "/MRegister")
    //管理员注册
    public CommonResult ManagerRegister(@RequestBody @Validated Manager manager){
        int res = managerService.ManagerRegister(manager);
        switch (res){
            case 0:
                return new CommonResult(200,"注册失败请重新注册");
            case 1:
                return new CommonResult(200,"注册成功");
            case 2:
                return new CommonResult(200,"管理员已存在");
            default:
                throw  new RuntimeException();
        }
    }


    //修改密码
    @PostMapping(path = "/MPwdUpdate")
    public CommonResult UserPasswordUpdate(@RequestParam(value = "userid") BigInteger managerid ,
                                           @RequestParam(value = "password" )String password) {

        int res = managerService.ManagerPasswordUpdate(managerid,password);
        if (res > 0){
            return new CommonResult(200,"修改成功");
        }else {
            return new CommonResult(400,"修改失败");
        }
    }

    //获取全部管理和对应角色
    @GetMapping(path = "/GetManager")
    public CommonResult GetManagerRoles() {
       List<Object>  managers = managerService.GetAllManager();
        return new CommonResult(200,"获取成功",managers);
    }

    //获取单独管理信息
    @GetMapping(path = "/GetManagerById")
    public CommonResult GetManagerRolesById(@RequestParam("id") BigInteger managerId) {

        Map<String,Object> managers = managerService.GetManagerById(managerId);

        return new CommonResult(200,"获取成功",managers);
    }

    //修改管理员的信息
    @PostMapping(path = "/ChangeManagerInfo")
    public CommonResult ChangeManagerInfo(@RequestBody Map<String,String> data) {
        System.out.println("修改了用户信息");
        String managerid = data.get("managerid");
        String managername = data.get("managername");
        String introduction = data.get("introduction");
        Boolean res = managerService.ChangeManagerInfo(managerid,managername,introduction);
        if (res){
            return new CommonResult(200,"获取成功");
        }else {
            return new CommonResult(500,"获取失败");
        }
    }

    //删除管理员
    @DeleteMapping(path = "/DeletManager")
    public CommonResult DeletManager(@RequestParam("id") String id) {

        Boolean res = managerService.DeletManager(id);
        if (res){
            return new CommonResult(200,"获取成功");
        }else {
            return new CommonResult(500,"获取失败");
        }
    }

    //获取单独管理角色
    @GetMapping(path = "/GetManagerRoles")
    public CommonResult GetManagerRoles(@RequestParam("id") BigInteger managerId) {

        Map<String,Object> managers = managerService.GetManagerRoles(managerId);

        return new CommonResult(200,"获取成功",managers);
    }


    //增加manager的角色
    @PostMapping (path = "/AddManagerRoles")
    public CommonResult AddManagerRoles(@RequestBody Map<String,Object> managerRoles) {
        System.out.println(managerRoles);
        Integer managerId =  (Integer)managerRoles.get("managerId");
        List roles = (List) managerRoles.get("changerole");

        boolean flag = managerService.AddManagerRoles(roles, new BigInteger(managerId.toString()));
        if (flag) {
            return new CommonResult(200, "增加成功");
        }
        return new CommonResult(400,"增加失败");
    }

    //减少manager的角色
    @PostMapping (path = "/DeletManagerRoles")
    public CommonResult DeletManagerRoles(@RequestBody Map<String,Object> managerRoles) {
        Integer managerId = (Integer) managerRoles.get("managerId");
        List roles = (List) managerRoles.get("changerole");

        boolean flag = managerService.DeletManagerRoles(roles, new BigInteger(managerId.toString()));
        if (flag) {
            return new CommonResult(200, "增加成功");
        }
        return new CommonResult(400,"增加失败");
    }

    //增加manager
    @PostMapping (path = "/AddManager")
    public CommonResult AddManager(@RequestBody Map<String,Object> Manager) {
        String managerId = (String) Manager.get("managerId");
        String managername = (String) Manager.get("managername");
        String introduction = (String) Manager.get("introduction");
        String managerpassword = (String) Manager.get("managerpassword");
       boolean res =  managerService.AddManager(managerId,managername,introduction,managerpassword);
    if (res){
        return new CommonResult(200,"成功");
    }
       return new CommonResult(500,"失败");
    }

}
