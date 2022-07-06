package com.laisd.vuebackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.laisd.vuebackend.controller.dto.UserDTO;
import com.laisd.vuebackend.entity.User;
import com.laisd.vuebackend.mapper.UserMapper;
import com.laisd.vuebackend.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Shudong
 * @since 2022-07-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    public boolean login(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
        User one = getOne(queryWrapper);
        return one != null;
    }
}
