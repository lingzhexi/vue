package com.lzx.vue.dao;

import com.lzx.vue.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: lzx
 * @Data: 2021/05/30/3:05
 * @Description：
 */
@Mapper
public interface UserDao {
    void update(User user);

    List<User> findAll();

    void save(User user);

    void delete(String id);

    List<User> findByNameOrCode(@Param("name") String name,@Param("code") String code);

    User findOne(String id);

}
