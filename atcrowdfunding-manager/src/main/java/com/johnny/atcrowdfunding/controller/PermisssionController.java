package com.johnny.atcrowdfunding.controller;

import com.johnny.atcrowdfunding.model.Permission;
import com.johnny.atcrowdfunding.service.PermissionService;
import com.johnny.atcrowdfunding.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author johnny
 * @create 2018-07-21 下午1:10
 **/
@RequestMapping("/permission")
@Controller
public class PermisssionController {

    @Autowired
    private PermissionService permissionService;


    @DeleteMapping("/delPermission/{id}")
    @ResponseBody
    public Object delPermission(@PathVariable("id") Integer id) {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            permissionService.deletePermission(id);
            ajaxResult.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
        }
        return ajaxResult;

    }

    @RequestMapping("/editPermission")
    @ResponseBody
    public Object editPermission(Permission permission) {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            permissionService.editPermission(permission);
            ajaxResult.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
        }
        return ajaxResult;
    }

    /**
     * 去 修改permission 页面带上 数据
     */
    @RequestMapping("/permissionEditPage/{id}")
    public String permissionEdit(@PathVariable("id") Integer id, Map<String, Object> map) {
        map.put("permission", permissionService.queryPermissionById(id));
        return "permission/permissionOper";
    }

    /**
     * 新增 permission
     */
    @RequestMapping("/addPermission")
    @ResponseBody
    public Object addPermission(Permission permission) {
        permission.setOpen(true);
        AjaxResult ajaxResult = new AjaxResult();

        if (permissionService.addPermission(permission) > 0) {
            ajaxResult.setSuccess(true);
        } else {
            ajaxResult.setSuccess(false);
        }
        return ajaxResult;
    }

    /**
     * 去 分配permission 页面
     */
    @RequestMapping("/assignPermissionPage")
    public String assignPermissionPage() {
        return "permission/assignPermission";
    }


    /**
     * 去 新增permission 页面
     */
    @RequestMapping("/permissionAddPage/{pid}")
    public String permissionAddPage(@PathVariable("pid") Integer pid, Map<String, Object> map) {
        map.put("pid", pid);
        return "permission/permissionOper";
    }


    @RequestMapping("/permissionPage")
    public String permissionPage() {

        return "permission/permission";
    }

    /**
     * 这个方法的问题：当我有更多的 下级菜单的时候，不好扩展，需要修改代码
     * 解决：换成递归方法
     *
     * @return
     */
    @RequestMapping("/queryPermissionById")
    @ResponseBody
    public Object queryPermissionById() {

        List<Permission> permissions = new ArrayList<>();
        //获取到 root 的权限
        Permission root = permissionService.queryRootPermission();
        List<Permission> childPermissions = permissionService.queryPermissionByPId(root.getId());
        for (Permission childPermission : childPermissions) {
            List<Permission> childChildPermission = permissionService.queryPermissionByPId(childPermission.getId());
            childPermission.setChildren(childChildPermission);
        }
        root.setChildren(childPermissions);
        permissions.add(root);
        return permissions;
    }


    /**
     * 将其父节点的 pid设置为0 即可使用相同的方法
     * 问题：重复连接数据库查询，效率低下
     *
     * @return
     */
    @RequestMapping("/queryPermission")
    @ResponseBody
    public Object queryPermission() {
        Permission root = new Permission();
        root.setId(0);
        queryChildPermission(root);
        return root.getChildren();
    }

    /**
     * 递归 查询 权限
     *
     * @param parent
     */
    private void queryChildPermission(Permission parent) {
        List<Permission> permissionList = permissionService.queryPermissionByPId(parent.getId());
        for (Permission permission : permissionList) {
            queryChildPermission(permission);
        }
        parent.setChildren(permissionList);
    }


    /**
     * 这个方法效率比上面的递归要高点，因为不用去反复查询数据库了
     * 问题：list 没有用到索引
     *
     * @return
     */
    @RequestMapping("/queryAllPermission2")
    @ResponseBody
    public Object queryAllPermission2() {

        Date date = new Date();
        List<Permission> permissions = new ArrayList<>();
        List<Permission> permissionList = permissionService.queryAllPermission();
        //遍历所有的
        for (Permission permission : permissionList) {
            Permission child = permission;
            //如果是顶级节点 直接添加进去
            if (permission.getPid() == 0) {
                permissions.add(permission);
            } else {
                //找 父节点
                for (Permission parent : permissionList) {
                    if (child.getPid() == parent.getId()) {
                        parent.getChildren().add(child);
                    }
                }
            }
        }
        Date date1 = new Date();
        System.out.println(date1.getTime() - date.getTime());
        return permissions;
    }


    @RequestMapping("/queryAllPermission")
    @ResponseBody
    public Object queryAllPermission(@RequestParam(value = "roleId", required = false) Integer roleId) {
        Date date = new Date();
        List<Permission> permissionList = permissionService.queryAllPermission();
        Map<Integer, Permission> permissionMap = new HashMap<>();
        List<Permission> permissions = new ArrayList<>();

        if (roleId != null) {
            List<Integer> rolePermissions = permissionService.queryPermissionByRoleId(roleId);
            for (Permission permission : permissionList) {
                if (rolePermissions.contains(permission.getId())) {
                    permission.setChecked(true);
                }

            }
        }
        for (Permission permission : permissionList) {
            permissionMap.put(permission.getId(), permission);
        }
        for (Permission child : permissionList) {
            if (child.getPid() == 0) {
                permissions.add(child);
            } else {
                Permission parent = permissionMap.get(child.getPid());
                parent.getChildren().add(child);
            }
        }
        Date date1 = new Date();
        System.out.println(date1.getTime() - date.getTime());

        return permissions;
    }
}

