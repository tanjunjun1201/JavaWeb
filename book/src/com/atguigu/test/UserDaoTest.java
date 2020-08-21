package com.atguigu.test;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {
    UserDao userDao =new UserDaoImpl();
    @Test
    public void queryUserByUsername() {

        System.out.println(userDao.queryUserByUsername("admin"));
    }

    @Test
    public void queryUserByusernameAndPassword() {
       if(userDao.queryUserByusernameAndPassword("admin", "admin") == null){
           System.out.println("用户名密码错误");
       }else{
           System.out.println("查询成功");
       }

    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null,"admin1","admin","123@qq.com")));
    }
}