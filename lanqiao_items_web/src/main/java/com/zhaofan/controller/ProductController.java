package com.zhaofan.controller;

import com.github.pagehelper.PageInfo;
import com.zhaofan.domain.Product;
import com.zhaofan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("findAll")
    public ModelAndView findAll(@RequestParam(defaultValue = "1",name = "pageNum") Integer pageNum, @RequestParam(defaultValue = "1",name = "pageSize") Integer pageSize){
        List<Product> products = productService.findAll(pageNum,pageSize);
        ModelAndView mv=new ModelAndView();
        PageInfo pageInfo=new PageInfo(products);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("product-list");
        return mv;
    }

    @RequestMapping("save")
    public String addProduct(Product product){
        productService.save(product);
        return "redirect:findAll.do";
    }


}
