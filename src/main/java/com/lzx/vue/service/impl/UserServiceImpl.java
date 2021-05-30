package com.lzx.vue.service.impl;

import cn.hutool.core.util.IdUtil;
import com.lzx.vue.dao.UserDao;
import com.lzx.vue.entity.User;
import com.lzx.vue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: lzx
 * @Data: 2021/05/30/3:32
 * @Description：
 */
@Service
@Transactional//控制事务
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void save(User user) throws Exception {
        user.setId(IdUtil.simpleUUID());
        userDao.save(user);
    }

    @Override
    public void delete(String id) {
        userDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> findLike(String name, String code) {
        return  userDao.findByNameOrCode(name,code);
    }

    @Override
    public User findOne(String id) {
        return userDao.findOne(id);
    }

}
