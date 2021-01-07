package com.bytedance.dao;

import com.bytedance.pojo.User;
import com.bytedance.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.List;



public class UserDaoTest {
    @Test
    public void test(){
        //获取sqlSession对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        //执行SQL
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        List<User> userList = mapper.getUserList();

        //输出结果
        for (User user : userList) {
            System.out.println(user);
        }

        //关闭sqlSession
        sqlSession.close();
    }

    @Test
    public void getUserById(){
        //获取sqlSession对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        //执行SQL
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User userById = mapper.getUserById(2);

        //输出结果
        System.out.println(userById);

        //关闭sqlSession
        sqlSession.close();
    }

    @Test
    public void addUser(){
        //获取sqlSession对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        //执行SQL
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        int res = mapper.addUser(new User(3, "Jacky", 4567));
        if(res > 1){
            System.out.println("执行成功");
        }
        //提交事务
        sqlSession.commit();
        //关闭sqlSession
        sqlSession.close();
    }

    @Test
    public void updateUser(){
        //获取sqlSession对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        //执行SQL
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        mapper.updateUser(new User(1, "Mike", 9999));
        //提交事务
        sqlSession.commit();
        //关闭sqlSession
        sqlSession.close();
    }

    @Test
    public void deleteUser(){
        //获取sqlSession对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        //执行SQL
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        mapper.deleteUser(1);
        //提交事务
        sqlSession.commit();
        //关闭sqlSession
        sqlSession.close();
    }
    static Logger logger = Logger.getLogger(UserDaoTest.class);
    @Test
    public void testLog4j(){
        logger.info("info");
        logger.debug("debug");
        logger.error("error");
    }
}
