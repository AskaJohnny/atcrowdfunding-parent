package com.johnny.atcrowdfunding.service;

import com.johnny.atcrowdfunding.model.Role;
import com.johnny.atcrowdfunding.model.User;

import java.util.List;
import java.util.Map;


public interface UserService {
    List<User> queryAllUser();

    User queryforUser(User user);

    List<User> queryUserForMap(Map<String, Object> map);

    int queryUserCount(Map<String, Object> map);

    void addUser(User user);

    void updateUser(User user);

    int deleteUser(Integer id);

    User queryUserById(Integer id);


}
