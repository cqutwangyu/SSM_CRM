package com.cqut.wangyu.crm.controller;

import com.cqut.wangyu.crm.entity.User;
import com.cqut.wangyu.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName UserController
 * @Description 用户控制
 * @Author ChongqingWangYu
 * @DateTime 2019/5/28 14:13
 * @GitHub https://github.com/ChongqingWangYu
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String register(User user) {
        Boolean resutl = userService.register(user);
        return resutl ? "注册成功" : "注册失败";
    }

    @RequestMapping(value = "/findUserByID", method = RequestMethod.POST)
    public User findUserById(Integer id) {
        return userService.findUserById(id);
    }

    @RequestMapping(value = "/findUserByName", method = RequestMethod.POST)
    public User findUserByName(String name) {
        return userService.findUserByName(name);
    }

    @RequestMapping(value = "/deleteUserByID", method = RequestMethod.POST)
    public Boolean deleteUserByID(Integer id) {
        return userService.deleteUserByID(id);
    }

    @RequestMapping(value = "/updateUserByID", method = RequestMethod.POST)
    public Boolean updateUserByID(User user) {
        return userService.updateUserByID(user);
    }

    @RequestMapping(value = "/findAllUser", method = RequestMethod.GET)
    public List<User> findAll() {
        return userService.findAllUser();
    }
}
