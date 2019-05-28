package com.cqut.wangyu.crm.controller;

import com.cqut.wangyu.crm.entity.User;
import com.cqut.wangyu.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @ClassName UserController
 * @Description 用户控制
 * @Author ChongqingWangYu
 * @DateTime 2019/5/28 14:13
 * @GitHub https://github.com/ChongqingWangYu
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    private Boolean register(User user) {
        return userService.register(user);
    }

    @RequestMapping(value = "/findUserById", method = RequestMethod.GET)
    private User findUserById(Integer id) {
        return userService.findUserById(id);
    }

    @RequestMapping(value = "/findUserByName", method = RequestMethod.GET)
    private User findUserByName(String name) {
        return userService.findUserByName(name);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    private Boolean delete(int id) {
        return userService.delete(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    private Boolean update(int id, User user) {
        return userService.update(id, user);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    private List<User> findAll() {
        return userService.findAll();
    }
}
