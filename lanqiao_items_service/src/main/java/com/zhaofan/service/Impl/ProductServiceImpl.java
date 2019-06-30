package com.zhaofan.service.Impl;

import com.github.pagehelper.PageHelper;
import com.zhaofan.dao.ProductDao;
import com.zhaofan.domain.Product;
import com.zhaofan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    public List<Product> findAll() {
        return productDao.findAll();
    }

    public List<Product> findAll(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return productDao.findAll();
    }



    public void save(Product product) {
        productDao.save(product);
    }
}
