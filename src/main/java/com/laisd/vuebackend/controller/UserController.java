package com.laisd.vuebackend.controller;

import com.laisd.vuebackend.Service.UserService;
import com.laisd.vuebackend.entity.User;
import com.laisd.vuebackend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @PostMapping
    public Integer save(@RequestBody User user){
        return userService.save(user);
    }

    @GetMapping
    public List<User> index(){
        return userMapper.findAll();
    }

    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable Integer id) {
        return userMapper.deleteById(id);
    }

    @GetMapping("/page")
    public List<User> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        pageNum = (pageNum - 1) * pageSize;
        return userMapper.selectPage(pageNum, pageSize);
    }

}
