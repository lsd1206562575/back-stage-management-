package com.laisd.vuebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laisd.vuebackend.entity.Files;
import com.laisd.vuebackend.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FileMapper extends BaseMapper<Files> {
    @Select("select * from sys_file where name like concat('%', #{name}, '%') limit #{pageNum}, #{pageSize}")
    List<Files> selectPage(Integer pageNum, Integer pageSize, String name);

    @Select("SELECT count(*) from sys_file where name like concat('%', #{name}, '%')")
    Integer selectTotal(String name);
}
