package com.cqut.wangyu.crm.service;

import com.cqut.wangyu.crm.entity.User;

import java.util.List;


/**
 * @ClassName UserServiceImpl
 * @Description 用户服务接口
 * @Author ChongqingWangYu
 * @DateTime 2019/5/28 14:10
 * @GitHub https://github.com/ChongqingWangYu
 */
public interface UserService {

    Boolean register(User user);

    User findUserById(Integer id);

    User findUserByName(String name);

    Boolean delete(int id);

    Boolean update(int id, User user);

    List<User> findAll();

}
