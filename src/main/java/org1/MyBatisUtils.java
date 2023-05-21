package org1;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MyBatisUtils {
    public static SqlSessionFactory sqlSessionFactory=null;
    static {
        try {
            Reader reader = Resources.getResourceAsReader("org1/mybatie-config.xml");
            sqlSessionFactory= new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static SqlSession getSession(){

        return  sqlSessionFactory.openSession();
    }
}
