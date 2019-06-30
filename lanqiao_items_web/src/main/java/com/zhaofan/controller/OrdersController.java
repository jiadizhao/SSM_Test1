package com.zhaofan.controller;

import com.github.pagehelper.PageInfo;
import com.zhaofan.domain.Orders;
import com.zhaofan.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 订单页面视图
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    private ModelAndView mv=new ModelAndView();

    //查询所有
    @RequestMapping("findAll")
    public ModelAndView findAll(@RequestParam(name="pageNum",required = true,defaultValue = "1") Integer pageNum, @RequestParam(name="pageSize",defaultValue = "4") Integer pageSize) {
        List<Orders> ordersList = ordersService.findAll(pageNum,pageSize);
        System.out.println(ordersList.size());
        PageInfo ordersInfo=new PageInfo(ordersList);
        mv.addObject("ordersInfo",ordersInfo);
        mv.setViewName("orders-list");
        return mv;
    }

    @RequestMapping("findById")
    public ModelAndView findById(String id) {
        Orders orders=ordersService.findById(id);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }
}
