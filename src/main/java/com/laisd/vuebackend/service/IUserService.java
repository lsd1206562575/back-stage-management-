package com.laisd.vuebackend.service;

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

    boolean login(UserDTO userDTO);
}
