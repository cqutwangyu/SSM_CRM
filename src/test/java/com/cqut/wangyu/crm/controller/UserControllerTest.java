package com.cqut.wangyu.crm.controller;

import com.cqut.wangyu.crm.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName UserControllerTest
 * @Description
 * @Author ChongqingWangYu
 * @DateTime 2019/6/4 12:36
 * @GitHub https://github.com/ChongqingWangYu
 */
public class UserControllerTest extends BaseTest {
    @Autowired
    private UserController userController;

    @Test
    public void registerTest() {
        User user = new User();
        user.setUserName("admin");
        user.setPassword("ddd");
        userController.register(user);
    }

    @Test
    public void findUserByIdTest() {
        userController.findUserById(1);
    }

    @Test
    public void findUserByNameTest() {
        userController.findUserByName("admin");
    }

    @Test
    public void deleteTest() {
        userController.deleteUserByID(1);
    }

    @Test
    public void updateTest() {
        User user = new User();
        user.setId(1);
        user.setUserName("admin");
        user.setPassword("ddd");
        userController.updateUserByID(user);
    }

    @Test
    public void findAllTest() {
        userController.findAll();
    }
}
