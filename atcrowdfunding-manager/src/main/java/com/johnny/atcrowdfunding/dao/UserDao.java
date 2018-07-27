package com.johnny.atcrowdfunding.dao;

import com.johnny.atcrowdfunding.model.Role;
import com.johnny.atcrowdfunding.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * @author johnny
 * @create 2018-07-18 上午10:13
 **/
@Mapper
public interface UserDao {

    @Select("select * from user")
    List<User> queryAllUser();

    @Select("select * from user where account = #{account} and password = #{password} ")
    User queryforUser(User user);

    List<User> queryUserForMap(Map<String, Object> map);

    @Select("select * from user where id = #{id}")
    User queryUserById(Integer id);

    int queryUserCount(Map<String, Object> map);


    @Select("insert into user(account,name,password,email,createtime) values(#{account},#{name},#{password},#{email},#{createTime})")
    void addUser(User user);

    @Update("update user set account = #{account} , name = #{name} , email = #{email} where id = #{id}")
    void updateUser(User user);

    @Delete("delete from user where id = #{id}")
    int deleteUser(Integer id);

}