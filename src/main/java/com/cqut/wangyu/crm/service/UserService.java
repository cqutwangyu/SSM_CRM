package com.cqut.wangyu.crm.service;

import com.cqut.wangyu.crm.dto.ResultDTO;
import com.cqut.wangyu.crm.entity.User;

import java.util.List;


/**
 * @ClassName UserServiceImpl
 * @Description 用户服务接口
 * @Author ChongqingWangYu
 * @DateTime 2019/5/28 14:10
 * @GitHub https://github.com/ChongqingWangYu
 */
public interface UserService {

    ResultDTO register(ResultDTO dto);

    User findUserById(Integer id);

    ResultDTO findUserByName(ResultDTO resultDTO);

    Boolean deleteUserByID(Integer id);

    Boolean updateUserByID(User user);

    List<User> findAllUser();

    ResultDTO login(ResultDTO resultDTO);
}
