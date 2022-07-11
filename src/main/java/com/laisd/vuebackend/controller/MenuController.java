package com.laisd.vuebackend.controller;

import com.laisd.vuebackend.common.Result;
import com.laisd.vuebackend.entity.Menu;
import com.laisd.vuebackend.entity.Role;
import com.laisd.vuebackend.mapper.MenuMapper;
import com.laisd.vuebackend.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private IMenuService iMenuService;

    @PostMapping
    public Result save(@RequestBody Menu menu){
        return Result.success(iMenuService.saveOrUpdate(menu));
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return iMenuService.removeById(id);
    }

    @PostMapping("/del/batch")
    public Boolean deleteBatch(@RequestBody List<Integer> ids) {
        return iMenuService.removeBatchByIds(ids);
    }

    @GetMapping
    public List<Menu> findAll() {return iMenuService.list();}

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {return Result.success(iMenuService.getById(id));}

    @GetMapping("/page")
    public Map<String, Object> findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam String name){
        pageNum = (pageNum - 1) * pageSize;
        List<Role> data = menuMapper.selectPage(pageNum, pageSize, name);
        Integer total = menuMapper.selectTotal(name);
        Map<String, Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }

}
