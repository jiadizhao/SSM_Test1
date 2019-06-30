package com.zhaofan.service.Impl;

import com.github.pagehelper.PageHelper;
import com.zhaofan.dao.SysLogDao;
import com.zhaofan.domain.SysLog;
import com.zhaofan.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogDao sysLogDao;

    public void save(SysLog sysLog) throws Exception {
        sysLogDao.save(sysLog);
    }

    public List<SysLog> findALl() throws Exception {
        return sysLogDao.findAll();
    }

    public List<SysLog> findALl(int pageNum, int pageSize) throws Exception {
        PageHelper.startPage(pageNum,pageSize);
        return sysLogDao.findAll();
    }
}
