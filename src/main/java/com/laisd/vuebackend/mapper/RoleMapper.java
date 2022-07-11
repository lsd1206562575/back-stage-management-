package com.laisd.vuebackend.mapper;

import com.laisd.vuebackend.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laisd.vuebackend.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Shudong
 * @since 2022-07-11
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    @Select("select * from sys_role where name like concat('%', #{username}, '%') limit #{pageNum}, #{pageSize}")
    List<Role> selectPage(Integer pageNum, Integer pageSize, String username);

    @Select("SELECT count(*) from sys_role where name like concat('%', #{username}, '%')")
    Integer selectTotal(String name);

}
