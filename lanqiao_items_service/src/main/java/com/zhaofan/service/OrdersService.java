package com.zhaofan.service;

import com.zhaofan.domain.Orders;

import java.util.List;

public interface OrdersService {
    //查询所有
    List<Orders> findAll(Integer pageNum,Integer pageSize);

    Orders findById(String id);
}
