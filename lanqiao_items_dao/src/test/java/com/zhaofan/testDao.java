package com.zhaofan;

import com.zhaofan.dao.ProductDao;
import com.zhaofan.domain.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class testDao {
    private ProductDao mapperDao;

    @Before
    public void init() throws IOException {
        InputStream is = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory=sqlSessionFactoryBuilder.build(is);
        SqlSession sqlSession=sqlSessionFactory.openSession();
        mapperDao = sqlSession.getMapper(ProductDao.class);
    }

    @Test
    public void testFindAll() {
        List<Product> all = mapperDao.findAll();
        
    }
}
