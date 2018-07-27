package com.johnny.atcrowdfunding.dao;

import com.johnny.atcrowdfunding.model.Role;
import com.johnny.atcrowdfunding.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
@Mapper
public interface RoleDao {


//
//    int insertRole(Role role);
//
//    int updateRole(Role role);
//
//    int deleteRole(Integer id);

    List<Role> queryAllRoles();

    int addUserRoleMiddle(Map<String, Object> map);

    int removeUserRoleMiddle(Map<String, Object> map);

    List<Role> queryRoleByUserId(Integer id);

    List<Role> queryNoRoleUserId(Integer id);

    void addRolePermission(Map<String, Object> map);

    void deleteRolePermission(Integer roleId);

    @Select("select u.* from user u left join user_role ur on u.id = ur.userId where ur.roleId = #{roleId}")
    User queryUserByRoleId(Integer roleId);

    List<Role> queryRoleForMap(Map<String,Object> map);

    int queryRoleCount(Map<String,Object> map);
}
