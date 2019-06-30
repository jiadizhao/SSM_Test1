package com.zhaofan.dao;

import com.zhaofan.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionDao {
    @Select("select * from permission where id in " +
            "(select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findByRoleId(String roleId);

    @Select("select * from permission")
    List<Permission> findAll() throws Exception;

    @Insert("insert into permission values( REPLACE(UUID(),'-',''),#{permissionId},#{url})")
    void save(Permission permission)throws Exception;

    //通过role的id,找到该角色没有获得的资源权限
    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{id})")
    List<Permission> findPermissionsByRoleId(String roleId) throws Exception;
}
