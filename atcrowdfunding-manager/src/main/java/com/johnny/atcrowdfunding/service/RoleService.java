package com.johnny.atcrowdfunding.service;

import com.johnny.atcrowdfunding.model.Role;
import com.johnny.atcrowdfunding.model.User;
import com.johnny.atcrowdfunding.util.Page;

import java.util.List;
import java.util.Map;

public interface RoleService {

    Page<Role> queryAllRole(Map<String, Object> map);

    int queryAllRoleCount(Map<String, Object> map);

    int insertRole(Role role);

    int updateRole(Role role);

    int deleteRole(Integer id);

    List<Role> queryAllRoles();

    int addUserRoleMiddle(Map<String,Object> map);

    int removeUserRoleMiddle(Map<String,Object> map);

    List<Role> queryRoleByUserId(Integer id);

    List<Role> queryNoRoleUserId(Integer id);

    void addRolePermission(Map<String,Object> map);

    void deleteRolePermission(Integer roleId);

    User queryUserByRoleId(Integer roleId);

    List<Role> queryRoleForMap(Map<String,Object> map);

    int queryRoleCount(Map<String,Object> map);
}
