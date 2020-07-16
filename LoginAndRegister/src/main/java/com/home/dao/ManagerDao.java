package com.home.dao;

import com.home.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Repository
public interface ManagerDao {

    public List<Manager> ManagerLogin(@Param("Managerid") BigInteger managerId);

    public List<ManagerInFo> GetManagerInFo(@Param("Managerid") BigInteger managerId);

    public List<Object> GetManagerRoles(@Param("Managerid") BigInteger userId);

    public List<Role> GetManagerRolesPlus(@Param("Managerid") BigInteger userId);

    public int ManagerRegister(Manager manager);

    public int ManagerPasswordUpdate(@Param("Managerid") BigInteger managerId,
                                     @Param("password") String password);

    public List<Manager> GetAllManager();

    public List<Role> ShwoRoles(@Param("Roleid") BigInteger roleid);

    public int ChangeManagerInfo(@Param("managerid") String managerid,
                                 @Param("managername") String managername,
                                 @Param("introduction") String introduction);

    public int Deletmanager(@Param("managerid") BigInteger managerid);
    //删除全部role
    public int DeletManagerRole(@Param("managerid") BigInteger managerid);

    public List<Role> GetAllRoles();

    public int AddManagerRoles(@Param("rolesid") BigInteger rolesid,
                                @Param("managerId") BigInteger managerId);
    //删除一部分role
    public int DeletManagerRoles(@Param("rolesid") BigInteger rolesid,
                               @Param("managerId") BigInteger managerId);

    public int AddManager(@Param("managerId")BigInteger managerId,
                          @Param("managerName")String managerName,
                          @Param("managerPassword")String managerPassword,
                          @Param("avatar")String avatar,
                          @Param("introduction")String introduction);

}
