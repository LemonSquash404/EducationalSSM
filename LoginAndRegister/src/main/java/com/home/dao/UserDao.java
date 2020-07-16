package com.home.dao;

import com.home.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {

    public List<User> UserLogin(@Param("userid") BigInteger userId);

    public int UserRegister(User user);

    public int UserPasswordUpdate(@Param("userid") BigInteger userId,
                                  @Param("password") String password);

    public List<UserInFo> UserLoginInfo(@Param("userid") BigInteger userId);

    public List<String> GetUserRoles(@Param("userid") BigInteger userId);

    public List<Router> GetUserRouters(@Param("rolesid") BigInteger roleId);

    public List<Router> GetAllRouter();

    public List<Router> GetRoleRouter(@Param("roleid")BigInteger roleid);

    public int DeletRole(@Param("roleid")BigInteger roleid);

    public int AddRoleRouter(@Param("roleid")BigInteger roleid,
                          @Param("name")String name,
                          @Param("routertem")String routertem,
                          @Param("parent")String parent);

    public int DeletRoleAllRouter(@Param("roleid")BigInteger roleid);

    public void AddRole(@Param("rolename")String rolename,
                       @Param("describe")String describe);

    public String FindRoleByName(@Param("rolename")String rolename);

    public List<Router> AllRouter();

    public int UpdateUserAvatar(@Param("avatar")String avatar,
                                @Param("userId")BigInteger userId);

    public int FindUserAvatar(@Param("userId")BigInteger userId);
}
