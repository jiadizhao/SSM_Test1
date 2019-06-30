package com.zhaofan.controller;

import com.github.pagehelper.PageInfo;
import com.zhaofan.domain.SysLog;
import com.zhaofan.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/sysLog")
@Controller
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("findAll")
    public ModelAndView findAll(@RequestParam(defaultValue = "1",name = "pageNum") Integer pageNum, @RequestParam(defaultValue = "4",name = "pageSize")Integer pageSize) throws Exception {
        ModelAndView mv=new ModelAndView();
        List<SysLog> sysLogList = sysLogService.findALl(pageNum,pageSize);
        PageInfo pageInfo=new PageInfo(sysLogList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("syslog-list");
        return mv;
    }

}
