package com.zhaofan.dao;

import com.zhaofan.domain.Member;
import com.zhaofan.domain.Orders;
import com.zhaofan.domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 关于订单的页面
 */
@Repository
public interface OrdersDao {

    //* 查询所有,注意:订单bean中包含其他类需要查询
    @Select("select * from orders")
    @Results(id = "orders", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "orderNum", property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",
                    one=@One(select="com.zhaofan.dao.ProductDao.findById"))
    })
    List<Orders> findAll();

    //根据id查找order,关联表:product,member,traveller
    @Select("select * from orders where id=#{id}")
    @Results(value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "orderNum", property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",javaType = Product.class,
                    one=@One(select="com.zhaofan.dao.ProductDao.findById")),
            @Result(column = "memberId",property ="member",javaType = Member.class,
                    one = @One(select="com.zhaofan.dao.MemberDao.findById")),
            //订单和游客是多对多关系的,需要用到中间表,所以传的id参数为ordersId,通过Traveller的查询关联中间表
            @Result(column = "id",property = "travellers",javaType = List.class,
                    many = @Many(select="com.zhaofan.dao.TravellerDao.findByOrdersId"))
    })
    Orders findById(String id);


}
