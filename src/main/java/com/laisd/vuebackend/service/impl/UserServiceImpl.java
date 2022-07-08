package com.laisd.vuebackend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laisd.vuebackend.common.Constants;
import com.laisd.vuebackend.controller.dto.UserDTO;
import com.laisd.vuebackend.entity.User;
import com.laisd.vuebackend.exception.ServiceException;
import com.laisd.vuebackend.mapper.UserMapper;
import com.laisd.vuebackend.service.IUserService;
import com.laisd.vuebackend.utils.TokenUtils;
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

    private static final Log LOG = Log.get();

    public UserDTO login(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one != null) {
            BeanUtil.copyProperties(one, userDTO, true);
            String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());
            userDTO.setToken(token);
            return userDTO;
        } else {
            throw new ServiceException(Constants.CODE_600, "username or password is wrong");
        }
    }

    @Override
    public User register(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one == null) {
            one = new User();
            BeanUtil.copyProperties(userDTO, one, true);
            save(one);
        } else {
            throw new ServiceException(Constants.CODE_600, "user has been created");
        }
        return one;
    }

    private User getUserInfo(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
        User one;
        try {
            one = getOne(queryWrapper); // 从数据库查询用户信息
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "system error");
        }
        return one;
    }
}
