package com.lzx.vue;

import cn.hutool.core.util.IdUtil;
import com.lzx.vue.dao.UserDao;
import com.lzx.vue.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VueApplicationTests {

    @Autowired
    private UserDao userDao;
    @Test
    void contextLoads() {
        userDao.findAll().forEach(user -> System.out.println(user));
        for (User user : userDao.findAll()) {
            System.out.println(userDao.findByNameOrCode(user.getUsername(), user.getPhoneCode()));
        }
        userDao.delete("1");
        userDao.save(new User("5", "张三", 23, 100.0, "17878567576"));
    }

    @Test
    void test() {
        System.out.println(IdUtil.fastUUID());
    }

    public static void main(String[] args) {
        System.out.println(IdUtil.simpleUUID());
    }
}
