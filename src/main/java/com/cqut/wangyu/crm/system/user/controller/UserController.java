package com.cqut.wangyu.crm.system.user.controller;

import com.cqut.wangyu.crm.system.dto.ResponseDTO;
import com.cqut.wangyu.crm.system.user.entity.User;
import com.cqut.wangyu.crm.system.user.service.UserService;
import com.cqut.wangyu.crm.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName UserController
 * @Description 用户控制（负责数据格式）
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
    public Object login(User user) {
        try {
            return userService.login(user);
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO logout() {
        ResponseDTO dto = new ResponseDTO();
        dto.setMessage("退出成功");
        return dto;
    }

    @RequestMapping(value = "/getInfo", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDTO getUserInfo() {
        String token = request.getHeader("X-Token");
        String userName = TokenUtil.getUserName(token);
        return userService.findUserByName(userName);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO register(User user) {
        return userService.register(user);
    }

    @RequestMapping(value = "/findUserByID", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO findUserById(Integer id) {
        return userService.findUserById(id);
    }

    @RequestMapping(value = "/findUserByName", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO findUserByName(String name) {
        return userService.findUserByName(name);
    }

    @RequestMapping(value = "/deleteUserByID", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO deleteUserByID(Integer id) {
        return userService.deleteUserByID(id);
    }

    @RequestMapping(value = "/updateUserByID", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO updateUserByID(User user) {
        return userService.updateUserByID(user);
    }

    @RequestMapping(value = "/findAllUser", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDTO findAllUser() {
        return userService.findAllUser();
    }

    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO uploadImage(MultipartFile avatar, HttpServletResponse response) {
        return userService.uploadImage(avatar, request);
    }
}
