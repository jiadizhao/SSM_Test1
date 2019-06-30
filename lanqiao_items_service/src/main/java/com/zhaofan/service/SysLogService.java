package com.zhaofan.service;

import com.zhaofan.domain.SysLog;

import java.util.List;

public interface SysLogService {
    void save(SysLog sysLog)throws Exception;
    List<SysLog> findALl()throws  Exception;
    List<SysLog> findALl(int pageNum,int pageSize)throws  Exception;
}
