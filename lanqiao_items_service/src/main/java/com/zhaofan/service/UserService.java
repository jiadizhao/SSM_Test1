package com.zhaofan.service;

import com.zhaofan.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserInfo> findAll(int pageNum,int pageSize);

    void save(UserInfo userInfo);

    UserInfo findById(String id) throws Exception;

    void addRoleToUser(String userId, String[] ids)throws Exception;
}
