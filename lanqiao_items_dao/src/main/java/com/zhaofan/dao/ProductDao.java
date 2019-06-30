package com.zhaofan.dao;

import com.zhaofan.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao {
    @Select("select * from product")
    public List<Product> findAll();

    //通过id来查询产品
    @Select("select * from product where id=#{id}")
    Product findById(String id);

    @Insert("insert into product(id,productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) " +
            "values( REPLACE(UUID(),'-',''),#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);


}
