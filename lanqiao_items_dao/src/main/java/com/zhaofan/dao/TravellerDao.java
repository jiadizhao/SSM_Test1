package com.zhaofan.dao;

import com.zhaofan.domain.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 旅客操作
 */
@Repository
public interface TravellerDao {

    //通过查找ordersId找到对应的traveller,此操作和ordersDao表的查询关联
    @Select("SELECT * FROM traveller WHERE id IN " +
            "(SELECT travellerId FROM order_traveller WHERE orderId=#{ordersId})")
    List<Traveller> findByOrdersId(String ordersId);

}
