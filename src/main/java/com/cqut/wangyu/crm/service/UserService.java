package com.cqut.wangyu.crm.service;

import com.cqut.wangyu.crm.dto.ResponseDTO;
import com.cqut.wangyu.crm.entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


/**
 * @ClassName UserServiceImpl
 * @Description 用户服务接口
 * @Author ChongqingWangYu
 * @DateTime 2019/5/28 14:10
 * @GitHub https://github.com/ChongqingWangYu
 */
public interface UserService {

    ResponseDTO register(User user);

    ResponseDTO findUserById(Integer id);

    ResponseDTO findUserByName(String userName);

    ResponseDTO deleteUserByID(Integer id);

    ResponseDTO updateUserByID(User user);

    ResponseDTO findAllUser();

    ResponseDTO login(User user);

    /**
     * 上传图片并存入avatar到数据库
     * @param avatar
     * @param request
     * @return
     */
    ResponseDTO uploadImage(MultipartFile avatar, HttpServletRequest request);
}
