package com.zhaofan.service;

import com.zhaofan.domain.Permission;
import com.zhaofan.domain.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll() throws Exception;

    List<Role> findAll(int pageNum,int pageSize) throws Exception;

    void save(Role role) throws Exception;

    List<Role> findOtherByUserId(String id) throws Exception;

    Role findById(String id) throws Exception;

    List<Permission> findPermissionsByRoleId(String id) throws Exception;

    void addPermissionToRole(String roleId, String[] permissionIds) throws Exception;
}
