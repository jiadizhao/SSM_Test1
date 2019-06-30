package com.zhaofan.service.Impl;

import com.github.pagehelper.PageHelper;
import com.zhaofan.dao.PermissionDao;
import com.zhaofan.dao.RoleDao;
import com.zhaofan.domain.Permission;
import com.zhaofan.domain.Role;
import com.zhaofan.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    public List<Role> findAll(int pageNum, int pageSize) throws Exception {
        PageHelper.startPage(pageNum,pageSize);
        return roleDao.findAll();
    }

    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    public List<Role> findOtherByUserId(String id) throws Exception {
        return roleDao.findOtherByUserId(id);
    }

    public Role findById(String id)throws Exception {
        return roleDao.findById(id);
    }

    public List<Permission> findPermissionsByRoleId(String id) throws Exception {
        return permissionDao.findPermissionsByRoleId(id);
    }

    public void addPermissionToRole(String roleId, String[] permissionIds) throws Exception {
        if(permissionIds.length==1) return;
        for(String permissionId:permissionIds){
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
