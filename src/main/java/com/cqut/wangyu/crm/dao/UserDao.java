package com.cqut.wangyu.crm.dao;

import com.cqut.wangyu.crm.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "userDao")
public interface UserDao {
    Integer insertUser(User user);

    User selectUserByID(Integer id);

    User selectUserByName(String name);

    Integer deleteUserByID(Integer id);

    Integer updateUserByID(User user);

    List<User> selectAll();

}

