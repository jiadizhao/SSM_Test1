package com.zhaofan.dao;

import com.zhaofan.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysLogDao {

    @Insert("insert into SysLog(id,visitTime,username,ip,url,executionTime,method) values(REPLACE(UUID(),'-',''),#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog sysLog);

    @Select("select * from SysLog")
    List<SysLog> findAll();
}
