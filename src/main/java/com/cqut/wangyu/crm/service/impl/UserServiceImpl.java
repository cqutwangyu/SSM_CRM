package com.cqut.wangyu.crm.service.impl;

import com.cqut.wangyu.crm.dao.UserDao;
import com.cqut.wangyu.crm.entity.User;
import com.cqut.wangyu.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description 用户服务实例
 * @Author ChongqingWangYu
 * @DateTime 2019/5/28 14:15
 * @GitHub https://github.com/ChongqingWangYu
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Boolean register(User user) {
        Integer row = userDao.register(user);
        Boolean result = row == 1 ? true : false;
        return result;
    }

    @Override
    public User findUserById(Integer id) {
        User user = userDao.findUserById(id);
        return user;
    }

    @Override
    public User findUserByName(String name) {
        User user = userDao.findUserByName(name);
        return user;
    }

    @Override
    public Boolean delete(int id) {
        return userDao.delete(id);
    }

    @Override
    public Boolean update(int id, User user) {
        return userDao.update(id, user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
