package com.johnny.atcrowdfunding.service.impl;

import com.johnny.atcrowdfunding.dao.RoleDao;
import com.johnny.atcrowdfunding.model.Role;
import com.johnny.atcrowdfunding.model.User;
import com.johnny.atcrowdfunding.service.RoleService;
import com.johnny.atcrowdfunding.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author johnny
 * @create 2018-07-20 下午3:38
 **/
@Repository
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Page<Role> queryAllRole(Map<String, Object> map) {
        Page<Role> pageInfo = new Page<>();
        return pageInfo;

    }

    @Override
    public int queryAllRoleCount(Map<String, Object> map) {
        return 0;
    }

    @Override
    public int insertRole(Role role) {
        return 0;
    }

    @Override
    public int updateRole(Role role) {
        return 0;
    }

    @Override
    public int deleteRole(Integer id) {
        return 0;
    }

    @Override
    public List<Role> queryAllRoles() {
        return roleDao.queryAllRoles();
    }

    @Override
    public int addUserRoleMiddle(Map<String, Object> map) {
        return roleDao.addUserRoleMiddle(map);
    }

    @Override
    public int removeUserRoleMiddle(Map<String, Object> map) {
        return roleDao.removeUserRoleMiddle(map);
    }

    @Override
    public List<Role> queryRoleByUserId(Integer id) {
        return roleDao.queryRoleByUserId(id);
    }

    @Override
    public List<Role> queryNoRoleUserId(Integer id) {
        return roleDao.queryNoRoleUserId(id);
    }

    @Override
    public void addRolePermission(Map<String, Object> map) {
        roleDao.addRolePermission(map);
    }

    @Override
    public void deleteRolePermission(Integer roleId) {
        roleDao.deleteRolePermission(roleId);
    }

    @Override
    public User queryUserByRoleId(Integer roleId) {
        return roleDao.queryUserByRoleId(roleId);
    }

    @Override
    public List<Role> queryRoleForMap(Map<String, Object> map) {
        return roleDao.queryRoleForMap(map);
    }

    @Override
    public int queryRoleCount(Map<String, Object> map) {
        return roleDao.queryRoleCount(map);
    }
}