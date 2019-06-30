package com.zhaofan.service;

import com.zhaofan.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    List<Product> findAll(int pageNum,int pageSize);
    void save(Product product);
}
