package com.johnny.atcrowdfunding.service;

import com.johnny.atcrowdfunding.model.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> queryPermissionByPId(Integer pid);

    Permission queryRootPermission();

    List<Permission> queryAllPermission();

    int addPermission(Permission permission);

    Permission queryPermissionById(Integer id);

    void editPermission(Permission permission);

    void deletePermission(Integer id);

    List<Permission> queryPermissionsByUserId(Integer userId);

    List<Integer> queryPermissionByRoleId(Integer roleId);
}

