package com.johnny.atcrowdfunding.controller;

import com.johnny.atcrowdfunding.model.Role;
import com.johnny.atcrowdfunding.model.User;
import com.johnny.atcrowdfunding.service.RoleService;
import com.johnny.atcrowdfunding.util.AjaxResult;
import com.johnny.atcrowdfunding.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author johnny
 * @create 2018-07-20 下午3:42
 **/
@RequestMapping("role")
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/rolePage")
    public String rolePage() {
        return "role/role";
    }


    @RequestMapping("/assignPermissionPage/{id}")
    public String assignPermissionPage(@PathVariable("id") Integer id, Map<String, Object> map) {
        map.put("roleId", id);
        return "role/rolePermission";
    }


    @RequestMapping("/assignRolePermission")
    @ResponseBody
    public Object assignRolePermission(@RequestParam("roleId") Integer roleId,
                                       @RequestParam("permissionIds") Integer[] permissionIds, HttpSession session) {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            Map<String, Object> map = new HashMap<>();
            roleService.deleteRolePermission(roleId);
            map.put("roleId", roleId);
            map.put("permissionIds", permissionIds);
            roleService.addRolePermission(map);
            User user = roleService.queryUserByRoleId(roleId);
            User existUser = (User) session.getAttribute("user");
            if (user != null) {
                //表示当前修改的权限是当前登录的用户的
                if (user.getId() == existUser.getId()) {
                    session.invalidate();
                    ajaxResult.setCode(0);
                }
            }
            ajaxResult.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
        }
        return ajaxResult;
    }


    @GetMapping("/queryAllRoleForAjax")
    @ResponseBody
    public Object queryAllRoleForAjax(@RequestParam("pageNum") Integer pageNum) {
        AjaxResult ajaxResult = new AjaxResult();
        Page<Role> pageInfo = new Page<>();
        pageInfo.setPageNum(pageNum);
        Map<String, Object> map = new HashMap<>();
        map.put("pageSize", pageInfo.getPageSize());
        map.put("fromCount", (pageInfo.getPageNum() - 1) * pageInfo.getPageSize());
        try {
            List<Role> roleList = roleService.queryRoleForMap(map);
            pageInfo.setSumCount(roleService.queryRoleCount(map));
            pageInfo.setContent(roleList);
            ajaxResult.setData(pageInfo);
            ajaxResult.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
        }
        return ajaxResult;
    }

}