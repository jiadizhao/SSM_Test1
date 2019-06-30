package com.zhaofan.service;

import com.zhaofan.domain.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> findAll() throws Exception;
    List<Permission> findAll(int pageNum,int pageSize) throws Exception;

    void save(Permission permission)throws Exception;
}
