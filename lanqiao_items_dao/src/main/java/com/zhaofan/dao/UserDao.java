package com.zhaofan.dao;

import com.zhaofan.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "email",property = "email"),
            @Result(column = "password",property = "password"),
            @Result(column = "phoneNum",property = "phoneNum"),
            @Result(column = "status",property = "status"),
            @Result(column = "id",property = "roles",
                    many = @Many(select = "com.zhaofan.dao.RoleDao.findByUserInfoId"))
    })
    UserInfo findByUsername(String username);

    @Select("select * from users")
    List<UserInfo> findAll();

    @Insert("insert into users(id,email,username,password,phoneNum,status) " +
            "values(REPLACE(UUID(),'-',''),#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo);

    //根据id查询用户的详细内容,用户中包括角色表,角色表中包含权限资源表
    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "email",property = "email"),
            @Result(column = "password",property = "password"),
            @Result(column = "phoneNum",property = "phoneNum"),
            @Result(column = "status",property = "status"),
            @Result(column = "id",property = "roles",
                    many = @Many(select = "com.zhaofan.dao.RoleDao.findByUserInfoId"))
    })
    UserInfo findById(String id);

    //向users_role表中添加关联关系
    @Insert("insert into users_role values(#{userId},#{id})")
    void addRoleToUser(@Param("userId") String userId, @Param("id") String id);
}
