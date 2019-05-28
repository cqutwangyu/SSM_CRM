package com.cqut.wangyu.crm.dao;

import com.cqut.wangyu.crm.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "userDao")
public interface UserDao {
    Integer register(User user);

    User findUserById(Integer id);

    User findUserByName(@Param("name")String name);

    Boolean delete(int id);

    Boolean update(int id, User user);

    List<User> findAll();

}

