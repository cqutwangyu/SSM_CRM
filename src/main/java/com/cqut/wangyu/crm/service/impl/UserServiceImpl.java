package com.cqut.wangyu.crm.service.impl;

import com.cqut.wangyu.crm.dao.UserDao;
import com.cqut.wangyu.crm.dto.ResponseDTO;
import com.cqut.wangyu.crm.entity.User;
import com.cqut.wangyu.crm.service.UserService;
import com.cqut.wangyu.crm.utils.MD5Util;
import com.cqut.wangyu.crm.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description 用户服务实例（负责业务逻辑）
 * @Author ChongqingWangYu
 * @DateTime 2019/5/28 14:15
 * @GitHub https://github.com/ChongqingWangYu
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public ResponseDTO register(User user) {
        ResponseDTO responseDTO = new ResponseDTO();
        User dbUser = userDao.selectUserByName(user.getUserName());
        Integer rows = 0;
        if (dbUser == null) {
            String md5Password = MD5Util.encode(user.getPassword());
            user.setPassword(md5Password);
            rows = userDao.insertUser(user);
        } else {
            responseDTO.setMessage("用户名已存在");
            return responseDTO;
        }
        responseDTO.setMessage(rows == 1 ? "注册成功" : "注册失败");
        return responseDTO;
    }

    @Override
    public ResponseDTO findUserById(Integer id) {
        User user = userDao.selectUserByID(id);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(user != null ? "获取成功" : "获取失败");
        responseDTO.setData(user);
        return responseDTO;
    }

    @Override
    public ResponseDTO findUserByName(String userName) {
        ResponseDTO dto = new ResponseDTO();
        User user = userDao.selectUserByName(userName);
        dto.setMessage(user != null ? "获取成功" : "获取失败");
        dto.setData(user);
        return dto;
    }

    @Override
    public ResponseDTO deleteUserByID(Integer id) {
        ResponseDTO responseDTO = new ResponseDTO();
        Integer rows = userDao.deleteUserByID(id);
        responseDTO.setMessage(rows == 1 ? "注册成功" : "注册失败");
        return responseDTO;
    }

    @Override
    public ResponseDTO updateUserByID(User user) {
        Integer rows = userDao.updateUserByID(user);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(rows == 1 ? "修改成功" : "修改失败");
        return responseDTO;
    }

    @Override
    public ResponseDTO findAllUser() {
        List<User> list = userDao.selectAll();
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(list);
        return responseDTO;
    }

    @Override
    public ResponseDTO login(User user) {
        String md5Password = MD5Util.encode(user.getPassword());
        user.setPassword(md5Password);
        User dbUser = userDao.selectUserByName(user.getUserName());
        ResponseDTO responseDTO = new ResponseDTO();
        //验证用户是否存在、密码是否正确
        if (dbUser != null && dbUser.getPassword().equals(user.getPassword())) {
            responseDTO.setData(dbUser);
            responseDTO.setToken(TokenUtil.sign(dbUser.getUserName(), dbUser.getPassword()));
            responseDTO.setMessage("登录成功");
        } else {
            responseDTO.setMessage("登录失败");
        }
        return responseDTO;
    }
}
