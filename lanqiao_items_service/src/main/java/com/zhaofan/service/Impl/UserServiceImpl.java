package com.zhaofan.service.Impl;

import com.github.pagehelper.PageHelper;
import com.zhaofan.dao.UserDao;
import com.zhaofan.domain.Role;
import com.zhaofan.domain.UserInfo;
import com.zhaofan.service.UserService;
import com.zhaofan.utils.BCryptPasswordEncoderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    //登录方法,使用
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo=userDao.findByUsername(username);
        //因为没有使用加密方式,所以需要加{noop}
        // User(String username, String password, boolean enabled,boolean accountNonExpired, boolean credentialsNonExpired,boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities
        User user = new User(userInfo.getUsername(), userInfo.getPassword(), userInfo.getStatus()==0?false:true,true,true,true, getAuthority(userInfo.getRoles()));
        return user;
    }

    public List<GrantedAuthority> getAuthority(List<Role> list){
        List<GrantedAuthority> list1=new ArrayList<GrantedAuthority>();
        for(Role li:list){
            //将角色名添加入集合中
            list1.add(new SimpleGrantedAuthority(li.getRoleName()));
            System.out.println("........................"+li.getRoleName());
        }
        return list1;
    }

    public List<UserInfo> findAll(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return userDao.findAll();
    }

    public void save(UserInfo userInfo) {
        //为密码加密
        userInfo.setPassword(BCryptPasswordEncoderUtils.encodePassword(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    public UserInfo findById(String id) throws Exception {
        return userDao.findById(id);

    }

    public void addRoleToUser(String userId, String[] ids) throws Exception {
        if(ids.length==1) return;
        //由于可能添加多个角色,所以使用循环,每添加一个角色,调用一次dao
        for (String id:ids){
            userDao.addRoleToUser(userId,id);
        }
    }

}
