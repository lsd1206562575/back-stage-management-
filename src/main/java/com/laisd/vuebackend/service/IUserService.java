package com.laisd.vuebackend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laisd.vuebackend.common.Result;
import com.laisd.vuebackend.controller.dto.UserDTO;
import com.laisd.vuebackend.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Shudong
 * @since 2022-07-03
 */
public interface IUserService extends IService<User> {

    UserDTO login(UserDTO userDTO);

    User register(UserDTO userDTO);
}
