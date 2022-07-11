package com.laisd.vuebackend.service.impl;

import com.laisd.vuebackend.entity.Role;
import com.laisd.vuebackend.mapper.RoleMapper;
import com.laisd.vuebackend.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Shudong
 * @since 2022-07-11
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
