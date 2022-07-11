package com.laisd.vuebackend.controller;

import com.laisd.vuebackend.common.Result;
import com.laisd.vuebackend.entity.Role;
import com.laisd.vuebackend.entity.User;
import com.laisd.vuebackend.mapper.RoleMapper;
import com.laisd.vuebackend.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Shudong
 * @since 2022-07-11
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private IRoleService iRoleService;

    @PostMapping
    public Result save(@RequestBody Role role){
        return Result.success(iRoleService.saveOrUpdate(role));
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return iRoleService.removeById(id);
    }

    @PostMapping("/del/batch")
    public Boolean deleteBatch(@RequestBody List<Integer> ids) {
        return iRoleService.removeBatchByIds(ids);
    }

    @GetMapping
    public List<Role> findAll() {return iRoleService.list();}

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {return Result.success(iRoleService.getById(id));}

    @GetMapping("/page")
    public Map<String, Object> findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam String name){
        pageNum = (pageNum - 1) * pageSize;
        List<Role> data = roleMapper.selectPage(pageNum, pageSize, name);
        Integer total = roleMapper.selectTotal(name);
        Map<String, Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }
}
