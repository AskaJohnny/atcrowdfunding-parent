package com.johnny.atcrowdfunding.controller;

import com.johnny.atcrowdfunding.model.User;
import com.johnny.atcrowdfunding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author johnny
 * @create 2018-07-18 上午9:49
 **/
@Controller
public class TestController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/hello")
    public Map<String, Object> hello() {

        Map<String, Object> map = new HashMap<>();
        map.put("hello", "johnny");
        return map;
    }

    @ResponseBody
    @GetMapping("/queryAllUser")
    public List<User> queryAllUser() {
        return userService.queryAllUser();
    }



}