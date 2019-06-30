package com.zhaofan.service.Impl;

import com.github.pagehelper.PageHelper;
import com.zhaofan.dao.OrdersDao;
import com.zhaofan.domain.Orders;
import com.zhaofan.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;

    public List<Orders> findAll(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);//pageNum 当前页数,pageSize 数据条数.该语句必须写在真正执行查询语句的上面,且中间不可以有其他语句
        return ordersDao.findAll();
    }

    public Orders findById(String id) {
        return ordersDao.findById(id);
    }
}
