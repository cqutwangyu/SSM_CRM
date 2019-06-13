package com.cqut.wangyu.crm.service.impl;

import com.cqut.wangyu.crm.dao.UserDao;
import com.cqut.wangyu.crm.dto.ResultDTO;
import com.cqut.wangyu.crm.entity.User;
import com.cqut.wangyu.crm.service.UserService;
import com.cqut.wangyu.crm.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description 用户服务实例
 * @Author ChongqingWangYu
 * @DateTime 2019/5/28 14:15
 * @GitHub https://github.com/ChongqingWangYu
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public ResultDTO register(ResultDTO dto) {
        User u = (User) dto.getData();
        User user = userDao.findUserByName(u.getUserName());
        Integer rows = 0;
        if (user == null) {
            rows = userDao.registerUser(u);
        }
        dto.setCode(rows == 1 ? 200 : 500);
        dto.setMessage(rows == 1 ? "注册成功" : "注册失败");
        return dto;
    }

    @Override
    public User findUserById(Integer id) {
        User user = userDao.findUserByID(id);
        return user;
    }

    @Override
    public ResultDTO findUserByName(ResultDTO dto) {
        User u = (User) dto.getData();
        User user = userDao.findUserByName(u.getUserName());
        dto.setCode(user != null ? 200 : 500);
        dto.setMessage(user != null ? "获取成功" : "获取失败");
        dto.setData(user);
        return dto;
    }

    @Override
    public Boolean deleteUserByID(Integer id) {
        Integer rows = userDao.deleteUserByID(id);
        Boolean result = rows == 1 ? true : false;
        return result;
    }

    @Override
    public Boolean updateUserByID(User user) {
        Integer rows = userDao.updateUserByID(user);
        Boolean result = rows == 1 ? true : false;
        return result;
    }

    @Override
    public List<User> findAllUser() {
        List<User> list = userDao.findAllUser();
        return list;
    }

    @Override
    public ResultDTO login(ResultDTO dto) {
        User u = (User) dto.getData();
        User user = userDao.findUserByName(u.getUserName());
        //验证用户是否存在、密码是否正确
        if (user != null && user.getPassword().equals(u.getPassword())) {
            dto.setData(user);
            dto.setToken(TokenUtil.sign(user.getUserName(), user.getPassword()));
            dto.setCode(200);
            dto.setMessage("登录成功");
        }else {
            dto.setMessage("登录失败");
        }
        return dto;
    }
}
