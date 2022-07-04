package com.laisd.vuebackend.service.impl;

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

}
