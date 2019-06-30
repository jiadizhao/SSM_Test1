package com.zhaofan.controller;

import com.github.pagehelper.PageInfo;
import com.zhaofan.domain.Role;
import com.zhaofan.domain.UserInfo;
import com.zhaofan.service.RoleService;
import com.zhaofan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("findAll")
    public ModelAndView findAll(@RequestParam(defaultValue = "1",name = "pageNum") Integer pageNum,@RequestParam(defaultValue = "4",name = "pageSize")Integer pageSize) {
        List<UserInfo> userList = userService.findAll(pageNum,pageSize);
        ModelAndView mv=new ModelAndView();
        PageInfo pageInfo=new PageInfo(userList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("save")
    public String save(UserInfo userInfo){
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("findById")
    public ModelAndView findById(String id) throws Exception {
        UserInfo user=userService.findById(id);
        ModelAndView mv=new ModelAndView();
        mv.addObject("user",user);
        mv.setViewName("user-show");
        return mv;
    }

    @RequestMapping("findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(String id) throws Exception {
        ModelAndView mv=new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        List<Role> roleList = roleService.findOtherByUserId(id);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",roleList);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId") String userId,@RequestParam(name = "ids",required = false) String[] ids) throws Exception {
        userService.addRoleToUser(userId,ids);
        return "redirect:findAll.do";
    }

}
