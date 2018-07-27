package com.johnny.atcrowdfunding.service.impl;

import com.johnny.atcrowdfunding.dao.PermissionDao;
import com.johnny.atcrowdfunding.model.Permission;
import com.johnny.atcrowdfunding.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author johnny
 * @create 2018-07-21 下午3:04
 **/
@Repository
public class PermissionServiceImpl implements PermissionService {


    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<Permission> queryPermissionByPId(Integer pid) {
        return permissionDao.queryPermissionByPId(pid);
    }

    @Override
    public Permission queryRootPermission() {
        return permissionDao.queryRootPermission();
    }

    @Override
    public List<Permission> queryAllPermission() {
        return permissionDao.queryAllPermission();
    }

    @Override
    public int addPermission(Permission permission) {
        return permissionDao.addPermission(permission);
    }

    @Override
    public Permission queryPermissionById(Integer id) {
        return permissionDao.queryPermissionById(id);
    }

    @Override
    public void editPermission(Permission permission) {
        permissionDao.editPermission(permission);
    }

    @Override
    public void deletePermission(Integer id) {
        permissionDao.deletePermission(id);
    }

    @Override
    public List<Permission> queryPermissionsByUserId(Integer userId) {
        return permissionDao.queryPermissionsByUserId(userId);
    }

    @Override
    public List<Integer> queryPermissionByRoleId(Integer roleId) {
        return permissionDao.queryPermissionByRoleId(roleId);
    }
}