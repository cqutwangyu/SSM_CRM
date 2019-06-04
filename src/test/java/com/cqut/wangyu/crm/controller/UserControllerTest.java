package com.cqut.wangyu.crm.controller;

import com.cqut.wangyu.crm.entity.User;
import com.cqut.wangyu.crm.logger.LogAspect;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        userController.register(new User(1, "admin", "ddd"));
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
        userController.updateUserByID(new User(1, "kkk", "xxx"));
    }

    @Test
    public void findAllTest() {
        userController.findAll();
    }
}
