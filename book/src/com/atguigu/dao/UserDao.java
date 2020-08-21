package com.atguigu.dao;

import com.atguigu.pojo.User;

public interface UserDao {
    public User queryUserByUsername(String username);
    public User queryUserByusernameAndPassword(String username,String password);
    public int saveUser(User user);




}
