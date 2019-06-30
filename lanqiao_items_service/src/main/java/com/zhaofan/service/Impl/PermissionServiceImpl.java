package com.zhaofan.service.Impl;

import com.github.pagehelper.PageHelper;
import com.zhaofan.dao.PermissionDao;
import com.zhaofan.domain.Permission;
import com.zhaofan.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;

    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }

    //分页用这个
    public List<Permission> findAll(int pageNum, int pageSize) throws Exception {
        PageHelper.startPage(pageNum,pageSize);
        return permissionDao.findAll();
    }

    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }
}
