package com.zhaofan.dao;

import com.zhaofan.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

//角色的数据库操作
@Repository
public interface RoleDao {

    //因为user详情需要查询role和role其中的permission,所以此查询语句要将permission也要查出来
    @Select("select * from role where id in (select roleId from users_role where userId=#{userInfoId})")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "roleName",property = "roleName"),
            @Result(column = "roleDesc",property = "roleDesc"),
            @Result(column = "id",property = "permissions",
            many = @Many(select = "com.zhaofan.dao.PermissionDao.findByRoleId"))
    })
    List<Role> findByUserInfoId(String userInfoId);

    @Select("select * from role")
    List<Role> findAll() throws Exception;

    @Insert("insert into role values( REPLACE(UUID(),'-',''),#{roleName},#{roleDesc})")
    void save(Role role) throws Exception;

    @Select("select * from role where id not in (select roleId from users_role where userId=#{id})")
    List<Role> findOtherByUserId(String id);

    @Select("select * from role where id=#{id}")
    Role findById(String id) throws  Exception;

    @Insert("insert into role_permission values(#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId,@Param("permissionId") String permissionId);


}
