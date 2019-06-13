package com.cqut.wangyu.crm.controller;

import com.cqut.wangyu.crm.dto.ResultDTO;
import com.cqut.wangyu.crm.entity.User;
import com.cqut.wangyu.crm.service.UserService;
import com.cqut.wangyu.crm.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
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
        String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Password);
        dto.setData(user);
        return userService.login(dto);
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
        User u=new User();
        u.setUserName(userName);
        dto.setData(u);
        return userService.findUserByName(dto);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO register(User user) {
        ResultDTO dto = new ResultDTO();
        String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Password);
        dto.setData(user);
        return userService.register(dto);
    }

    @RequestMapping(value = "/findUserByID", method = RequestMethod.POST)
    public User findUserById(Integer id) {
        return userService.findUserById(id);
    }

    @RequestMapping(value = "/findUserByName", method = RequestMethod.POST)
    public ResultDTO findUserByName(String name) {
        ResultDTO dto=new ResultDTO();
        User u=new User();
        u.setUserName(name);
        return userService.findUserByName(dto);
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
