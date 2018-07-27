package com.johnny.atcrowdfunding.dao;

import com.johnny.atcrowdfunding.model.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionDao {

    List<Permission> queryPermissionByPId(Integer pid);

    Permission queryRootPermission();

    List<Permission> queryAllPermission();

    int addPermission(Permission permission);

    Permission queryPermissionById(Integer id);

    void editPermission(Permission permission);

    void deletePermission(Integer id);

    List<Permission> queryPermissionsByUserId(Integer userId);

    @Select("select permissionId from role_permission where roleId = #{roleId}")
    List<Integer> queryPermissionByRoleId(Integer roleId);
}
