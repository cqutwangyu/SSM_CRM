package com.cqut.wangyu.crm.dao;

import com.cqut.wangyu.crm.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "userDao")
public interface UserDao {
    Integer registerUser(User user);

    User findUserByID(Integer id);

    User findUserByName(String name);

    Integer deleteUserByID(Integer id);

    Integer updateUserByID(User user);

    List<User> findAllUser();

}

