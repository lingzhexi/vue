package com.lzx.vue.controller;

import com.lzx.vue.entity.User;
import com.lzx.vue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Author: lzx
 * @Data: 2021/05/30/2:14
 * @Description：
 */
@CrossOrigin
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("findAll")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("findLike")
    public List<User> findLike(String name, String code) {
        return userService.findLike(name,code);
    }

    @PostMapping("save")
    public Map<String, Object> save(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (StringUtils.isEmpty(user.getId())) {
                userService.save(user);
                map.put("success", true);
                map.put("message", "用户保存成功");
            } else {
                userService.update(user);
                map.put("success", true);
                map.put("message", "用户更新成功");
            }
        } catch (Exception e) {
            map.put("success", false);
            map.put("message", "用户保存或更新失败");
        }
        return map;
    }

    @GetMapping("findOne")
    public User findOne(String id) {
        return userService.findOne(id);
    }

    @DeleteMapping("del")
    public Map<String, Object> del(String id) {
        Map<String, Object> map = new HashMap<>();
        try {
            userService.delete(id);
            map.put("success", true);
            map.put("message", "用户删除成功");
        } catch (Exception e) {
            map.put("success", false);
            map.put("message", "用户删除失败");
        }
        return map;

    }

    public List<User> userList() {
        List<User> users = new ArrayList<>();
        users.add(new User("1", "张三", 23, 100.0, "17878098576"));
        users.add(new User("2", "小陈", 24, 101.0, "17878292975"));
        users.add(new User("3", "小黄", 25, 102.0, "17878733473"));
        users.add(new User("4", "小黑", 20, 104.0, "17878797971"));
        return users;
    }
}
