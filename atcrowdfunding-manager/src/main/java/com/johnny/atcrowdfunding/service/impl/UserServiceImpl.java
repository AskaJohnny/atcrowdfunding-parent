package com.johnny.atcrowdfunding.service.impl;

import com.johnny.atcrowdfunding.dao.UserDao;
import com.johnny.atcrowdfunding.model.Role;
import com.johnny.atcrowdfunding.model.User;
import com.johnny.atcrowdfunding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author johnny
 * @create 2018-07-18 上午10:16
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> queryAllUser() {
        return userDao.queryAllUser();
    }

    @Override
    public User queryforUser(User user) {
        return userDao.queryforUser(user);
    }

    @Override
    public List<User> queryUserForMap(Map<String, Object> map) {
        return userDao.queryUserForMap(map);
    }

    @Override
    public int queryUserCount(Map<String, Object> map) {
        return userDao.queryUserCount(map);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public int deleteUser(Integer id) {
        return userDao.deleteUser(id);
    }

    @Override
    public User queryUserById(Integer id) {
        return userDao.queryUserById(id);
    }

}