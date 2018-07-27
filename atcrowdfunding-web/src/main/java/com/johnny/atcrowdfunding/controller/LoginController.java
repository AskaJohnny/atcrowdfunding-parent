package com.johnny.atcrowdfunding.controller;

import com.johnny.atcrowdfunding.model.Permission;
import com.johnny.atcrowdfunding.model.User;
import com.johnny.atcrowdfunding.service.PermissionService;
import com.johnny.atcrowdfunding.service.UserService;
import com.johnny.atcrowdfunding.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author johnny
 * @create 2018-07-18 上午11:08
 **/
@RequestMapping("login")
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;


    //@PostMapping("/login")
    public String login(User user, Map<String, Object> map, HttpSession session) {
        String errorMsg = "";
        if (user != null) {
            User existUser = userService.queryforUser(user);
            if (existUser == null) {
                errorMsg = "用户名或密码错误";
            } else {
                session.setAttribute("user", user);
                return "redirect:/index";
            }
        }
        map.put("errorMsg", errorMsg);
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object loginForAjax(User user, HttpSession session) {
        AjaxResult ajaxResult = new AjaxResult();
        if (user != null) {
            User existUser = userService.queryforUser(user);
            if (existUser == null) {
                ajaxResult.setSuccess(false);
            } else {
                Permission root = null;
                List<Permission> permissionList = permissionService.queryPermissionsByUserId(existUser.getId());
                Map<Integer, Object> perMap = new HashMap<>();
                for (Permission permission : permissionList) {
                    perMap.put(permission.getId(), permission);
                }
                for (Permission permission : permissionList) {
                    if (permission.getPid() == 0) {
                        root = permission;
                    } else {
                        Permission parent = (Permission) perMap.get(permission.getPid());
                        parent.getChildren().add(permission);
                    }
                }
                session.setAttribute("user", existUser);
                session.setAttribute("rootPermission", root.getChildren());
                ajaxResult.setSuccess(true);
            }
        }
        return ajaxResult;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        //直接让 session失效， 而不是 session.remove...
        session.invalidate();
        return "redirect:/";
    }
}