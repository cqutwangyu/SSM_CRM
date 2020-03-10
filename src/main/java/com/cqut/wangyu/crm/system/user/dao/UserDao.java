package com.cqut.wangyu.crm.system.user.dao;

import com.cqut.wangyu.crm.system.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
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

