package com.zhaofan.controller;

import com.github.pagehelper.PageInfo;
import com.zhaofan.domain.Permission;
import com.zhaofan.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("permission")
@Controller
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @RequestMapping("findAll")
    public ModelAndView findAll(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "4") Integer pageSize) throws Exception {
        List<Permission> permissionList= permissionService.findAll(pageNum,pageSize);
        ModelAndView mv=new ModelAndView();
        PageInfo pageInfo=new PageInfo(permissionList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("permission-list");
        return  mv;
    }

    @RequestMapping("save")
    public String save(Permission permission) throws Exception {
        permissionService.save(permission);
        return "redirect:/permission/findAll.do";
    }

}
