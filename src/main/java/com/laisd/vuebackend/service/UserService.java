package com.laisd.vuebackend.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laisd.vuebackend.entity.User;
import com.laisd.vuebackend.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    public boolean saveUser(User user){
        return saveOrUpdate(user);
    }
}
