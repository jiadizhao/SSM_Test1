package com.zhaofan.controller;

import com.github.pagehelper.PageInfo;
import com.zhaofan.domain.Permission;
import com.zhaofan.domain.Role;
import com.zhaofan.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("role")
@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("findAll")
    public ModelAndView findAll(@RequestParam(defaultValue = "1",name = "pageNum") Integer pageNum, @RequestParam(defaultValue = "1",name = "pageSize")Integer pageSize) throws Exception {
        List<Role> roles=roleService.findAll(pageNum,pageSize);
        ModelAndView mv=new ModelAndView();
        PageInfo pageInfo=new PageInfo(roles);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("save")
    public String save(Role role) throws Exception {
        roleService.save(role);
        return "redirect:findAll.do";
    }

    @RequestMapping("findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(String id) throws Exception {
        ModelAndView mv=new ModelAndView();
        Role role=roleService.findById(id);
        List<Permission> permissionList = roleService.findPermissionsByRoleId(id);
        mv.addObject("role",role);
        mv.addObject("permissionList",permissionList);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("addPermissionToRole")
    public String addPermissionToRole(String roleId,@RequestParam(name = "ids") String[] permissionIds) throws Exception {
        roleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:findAll.do";
    }

    @RequestMapping("findById")
    public ModelAndView findById(String id) throws Exception {
        Role byId = roleService.findById(id);
        List<Role> roleList=new ArrayList<>();
        roleList.add(byId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("roleList",roleList);
        mv.setViewName("role-list");
        return mv;
    }
}
