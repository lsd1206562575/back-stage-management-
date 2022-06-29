package com.laisd.vuebackend.mapper;

import com.laisd.vuebackend.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM sys_user")
    List<User> findAll();

    @Insert("INSERT into sys_user(username, password, nickname, email, phone, address) VALUES(#{username}, #{password}, #{nickname}, #{email}, #{phone}, #{address}")
    int insert(User user);

    int update(User user);

    @Delete("DELETE from sys_user where id = #{id}")
    Integer deleteById(@Param("id") Integer id);

    @Select("select * from sys_user limit #{pageNum}, #{pageSize}")
    List<User> selectPage(Integer pageNum, Integer pageSize);
}
