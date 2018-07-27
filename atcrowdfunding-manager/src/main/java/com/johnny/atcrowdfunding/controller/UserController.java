package com.johnny.atcrowdfunding.controller;

import com.johnny.atcrowdfunding.model.Role;
import com.johnny.atcrowdfunding.model.User;
import com.johnny.atcrowdfunding.service.RoleService;
import com.johnny.atcrowdfunding.service.UserService;
import com.johnny.atcrowdfunding.util.AjaxResult;
import com.johnny.atcrowdfunding.util.DateUtil;
import com.johnny.atcrowdfunding.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author johnny
 * @create 2018-07-19 上午11:26
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    //@GetMapping("/userIndex/{pageNum}")
    public String userIndex(@PathVariable("pageNum") Integer pageNum, Map<String, Object> map) {
        Page<User> pageInfo = new Page<>();
        pageInfo.setPageNum(pageNum);
        map.put("pageSize", pageInfo.getPageSize());
        map.put("fromCount", (pageInfo.getPageNum() - 1) * pageInfo.getPageSize());
        List<User> users = userService.queryUserForMap(map);
        pageInfo.setContent(users);
        pageInfo.setSumCount(userService.queryUserCount(map));
        if (users.size() == 0 && pageInfo.getPrePageNum() != 0) {

        }
        map.clear();
        map.put("pageInfo", pageInfo);
        return "user/user";
    }


    @GetMapping("/userIndex")
    public String userIndex() {
        return "user/user";
    }


    @PostMapping("/userForAjax")
    @ResponseBody
    public Object userForAjax(@RequestParam("queryCondition") String queryCondition,
                              @RequestParam("pageNum") Integer pageNum, Map<String, Object> map) {
        AjaxResult ajaxResult = new AjaxResult();
        if (!StringUtils.isEmpty(queryCondition)) {
            map.put("queryCondition", queryCondition);
        }
        Page<User> pageInfo = new Page<>();
        pageInfo.setPageNum(pageNum);
        map.put("pageSize", pageInfo.getPageSize());
        map.put("fromCount", (pageInfo.getPageNum() - 1) * pageInfo.getPageSize());
        List<User> users = userService.queryUserForMap(map);
        pageInfo.setSumCount(userService.queryUserCount(map));
        //主要是为了避免，前台删除 后面一页的数据时候 还停留在后面一页空数据，让其返回到前一页
        if (users.size() == 0 && pageInfo.getPrePageNum() != 0) {
            map.put("fromCount", (pageInfo.getPageNum() - 1 - 1) * pageInfo.getPageSize());
            users = userService.queryUserForMap(map);
            pageInfo.setPageNum(pageNum - 1);
        }
        pageInfo.setContent(users);
        ajaxResult.setSuccess(true);
        ajaxResult.setData(pageInfo);
        return ajaxResult;
    }


    @PostMapping("/addUser")
    @ResponseBody
    public Object addUser(User user) {
        if (user.getId() == 0) {
            user.setPassword("123456");
            user.setCreateTime(DateUtil.dateFormat(new Date()));
            userService.addUser(user);
        } else {
            userService.updateUser(user);
        }
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setSuccess(true);
        return ajaxResult;
    }

    @DeleteMapping("/del/{id}")
    @ResponseBody
    public Object delUser(@PathVariable("id") Integer id) {
        AjaxResult ajaxResult = new AjaxResult();
        int status = userService.deleteUser(id);
        if (status == 1) {
            ajaxResult.setSuccess(true);
        } else {
            ajaxResult.setSuccess(false);
        }
        return ajaxResult;
    }


    @GetMapping("/assign/{id}")
    public String assign(@PathVariable("id") Integer id, Map<String, Object> map) {
        //List<Role> roles = roleService.queryAllRoles();

        List<Role> userRoles = roleService.queryRoleByUserId(id);
        List<Role> userNoRoles = roleService.queryNoRoleUserId(id);
        map.put("user", userService.queryUserById(id));
        map.put("userRoles", userRoles);
        map.put("userNoRoles", userNoRoles);
        return "user/assignRole";
    }

    /**
     * 添加用户的角色
     *
     * @param roleIds
     * @param userId
     * @return
     */
    @PostMapping("/assignRole")
    @ResponseBody
    public Object assignRole(@RequestParam("unAssignRoleIds") Integer[] roleIds,
                             @RequestParam("userId") Integer userId) {
        AjaxResult ajaxResult = new AjaxResult();
        Map<String, Object> map = new HashMap<>();
        map.put("roleIds", roleIds);
        map.put("userId", userId);
        if (roleService.addUserRoleMiddle(map) > 0) {
            ajaxResult.setSuccess(true);
        } else {
            ajaxResult.setSuccess(false);
        }
        return ajaxResult;
    }

    /**
     * 解除用户的角色
     *
     * @param roleIds
     * @param userId
     * @return
     */
    @PostMapping("/removeAssignRole")
    @ResponseBody
    public Object removeAssignRole(@RequestParam("assignRoleIds") Integer[] roleIds,
                                   @RequestParam("userId") Integer userId) {
        AjaxResult ajaxResult = new AjaxResult();
        Map<String, Object> map = new HashMap<>();
        map.put("roleIds", roleIds);
        map.put("userId", userId);
        if (roleService.removeUserRoleMiddle(map) > 0) {
            ajaxResult.setSuccess(true);
        } else {
            ajaxResult.setSuccess(false);
        }
        return ajaxResult;
    }
}