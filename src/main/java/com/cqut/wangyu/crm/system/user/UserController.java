package com.cqut.wangyu.crm.system.user;

import com.cqut.wangyu.crm.framework.AbstractController;
import com.cqut.wangyu.crm.system.user.entity.User;
import com.cqut.wangyu.crm.system.user.service.UserService;
import com.cqut.wangyu.crm.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
public class UserController extends AbstractController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(User user) {
        try {
            return succeed(userService.login(user));
        } catch (Exception e) {
            return failure(e);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public Object logout() {
        return succeed(Constant.LOGOUT_SUCCEED);
    }

    @RequestMapping(value = "/getInfo", method = RequestMethod.GET)
    @ResponseBody
    public Object getUserInfo() {
        try {
//            return succeed(userService.findUserByName(getUserName()));
            return succeed(currentUser());
        } catch (Exception e) {
            return failure(e);
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Object register(User user) {
        try {
            return succeed(userService.register(user));
        } catch (Exception e) {
            return failure(e);
        }
    }

    @RequestMapping(value = "/findUserByID", method = RequestMethod.POST)
    @ResponseBody
    public Object findUserById(Integer id) {
        return succeed(userService.findUserById(id));
    }

    @RequestMapping(value = "/findUserByName", method = RequestMethod.POST)
    @ResponseBody
    public Object findUserByName(String name) {
        return succeed(userService.findUserByName(name));
    }

    @RequestMapping(value = "/deleteUserByID", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteUserByID(Integer id) {
        return succeed(userService.deleteUserByID(id));
    }

    @RequestMapping(value = "/updateUserByID", method = RequestMethod.POST)
    @ResponseBody
    public Object updateUserByID(User user) {
        return succeed(userService.updateUserByID(user));
    }

    @RequestMapping(value = "/findAllUser", method = RequestMethod.GET)
    @ResponseBody
    public Object findAllUser() {
        return succeed(userService.findAllUser());
    }

    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadImage(MultipartFile avatar, HttpServletResponse response) {
        try {
            return succeed(userService.uploadImage(avatar, request));
        } catch (Exception e) {
            return failure(e);
        }
    }
}
