package com.lzx.vue.service;

import com.lzx.vue.entity.User;

import java.util.List;

/**
 * @Author: lzx
 * @Data: 2021/05/30/3:31
 * @Description：
 */
public interface UserService {
    void update(User user);

    List<User> findAll();

    void save(User user) throws Exception;

    void delete(String id);

    List<User> findLike(String name, String code);

    User findOne(String id);
}
