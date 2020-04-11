package com.cqut.wangyu.crm.system.user.service;

import com.cqut.wangyu.crm.system.dto.GrantedUser;
import com.cqut.wangyu.crm.system.dto.ResponseDTO;
import com.cqut.wangyu.crm.system.user.entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;


/**
 * @ClassName UserServiceImpl
 * @Description 用户服务接口
 * @Author ChongqingWangYu
 * @DateTime 2019/5/28 14:10
 * @GitHub https://github.com/ChongqingWangYu
 */
public interface UserService {

    Object register(User user) throws Exception;

    Object findUserById(Integer id);

    Object findUserByName(String userName);

    Object deleteUserByID(Integer id);

    Object updateUserByID(User user);

    Object findAllUser();

    Object login(User user);

    /**
     * 上传图片并存入avatar到数据库
     * @param avatar
     * @param request
     * @return
     */
    Object uploadImage(MultipartFile avatar, HttpServletRequest request) throws IOException;
}
