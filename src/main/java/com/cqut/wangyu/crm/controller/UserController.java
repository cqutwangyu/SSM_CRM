package com.cqut.wangyu.crm.controller;

import com.cqut.wangyu.crm.dto.ResultDTO;
import com.cqut.wangyu.crm.entity.User;
import com.cqut.wangyu.crm.service.UserService;
import com.cqut.wangyu.crm.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO login(User user) {
        ResultDTO dto = new ResultDTO();
        Boolean result = userService.login(user);
        dto.setCode(result ? 200 : 500);
        dto.setMessage(result ? "登录成功" : "登录失败");
        if (result) {
            String sign = TokenUtil.sign(user.getUserName(), user.getPassword());
            dto.setToken(sign);
        }
        return dto;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO logout() {
        ResultDTO dto = new ResultDTO();
        dto.setCode(200);
        dto.setMessage("退出成功");
        return dto;
    }

    @RequestMapping(value = "/getInfo", method = RequestMethod.GET)
    @ResponseBody
    public ResultDTO getUserInfo() {
        ResultDTO dto = new ResultDTO();
        String token = request.getHeader("X-Token");
        String userName = TokenUtil.getUserName(token);
        User userByName = findUserByName(userName);
        dto.setCode(userByName != null ? 200 : 500);
        dto.setMessage(userByName != null ? "获取成功" : "获取失败");
        dto.setData(userByName);
        return dto;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO register(User user) {
        ResultDTO dto = new ResultDTO();
        Boolean result = userService.register(user);
        dto.setCode(result ? 200 : 500);
        dto.setMessage(result ? "注册成功" : "注册失败");
        return dto;
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
