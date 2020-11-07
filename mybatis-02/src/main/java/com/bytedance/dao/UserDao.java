package com.bytedance.dao;

import com.bytedance.pojo.User;

import java.util.List;

public interface UserDao {
    //查询全部用户
    List<User> getUserList();
    //根据ID查询用户
    User getUserById(int id);
    //插入一个用户
    int addUser(User user);
    //修改用户
    void updateUser(User user);
    //删除一个用户
    void deleteUser(int id);
}
