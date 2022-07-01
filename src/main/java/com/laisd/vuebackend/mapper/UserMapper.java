package com.laisd.vuebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laisd.vuebackend.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT * FROM sys_user")
    List<User> findAll();

    @Select("select * from sys_user where username like concat('%', #{username}, '%') limit #{pageNum}, #{pageSize}")
    List<User> selectPage(Integer pageNum, Integer pageSize, String username);

    @Select("SELECT count(*) from sys_user where username like concat('%', #{username}, '%')")
    Integer selectTotal(String username);
}
