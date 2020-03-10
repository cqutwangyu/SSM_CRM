package com.cqut.wangyu.crm.system.user.service;

import com.cqut.wangyu.crm.system.user.dao.UserDao;
import com.cqut.wangyu.crm.system.dto.ResponseDTO;
import com.cqut.wangyu.crm.system.user.entity.User;
import com.cqut.wangyu.crm.utils.Constant;
import com.cqut.wangyu.crm.utils.MD5Util;
import com.cqut.wangyu.crm.utils.MyFileUtil;
import com.cqut.wangyu.crm.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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
            responseDTO.setMessage(Constant.REGISTER_USERNAME_REPETITION);
            return responseDTO;
        }
        responseDTO.setMessage(rows == 1 ? Constant.REGISTER_SUCCEED : Constant.REGISTER_FAILURE);
        return responseDTO;
    }

    @Override
    public ResponseDTO findUserById(Integer id) {
        User user = userDao.selectUserByID(id);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(user != null ? Constant.FIND_SUCCEED : Constant.FIND_FAILURE);
        responseDTO.setData(user);
        return responseDTO;
    }

    @Override
    public ResponseDTO findUserByName(String userName) {
        ResponseDTO dto = new ResponseDTO();
        User user = userDao.selectUserByName(userName);
        dto.setMessage(user != null ? Constant.FIND_SUCCEED : Constant.FIND_FAILURE);
        dto.setData(user);
        return dto;
    }

    @Override
    public ResponseDTO deleteUserByID(Integer id) {
        ResponseDTO responseDTO = new ResponseDTO();
        Integer rows = userDao.deleteUserByID(id);
        responseDTO.setMessage(rows == 1 ? Constant.DELETE_SUCCEED : Constant.DELETE_FAILURE);
        return responseDTO;
    }

    @Override
    public ResponseDTO updateUserByID(User user) {
        Integer rows = userDao.updateUserByID(user);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(rows == 1 ? Constant.UPDATE_SUCCEED : Constant.UPDATE_FAILURE);
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
    public ResponseDTO login(User user) throws Exception{
        String md5Password = MD5Util.encode(user.getPassword());
        user.setPassword(md5Password);
        User dbUser = userDao.selectUserByName(user.getUserName());
        ResponseDTO responseDTO = new ResponseDTO();
        //验证用户是否存在、密码是否正确
        if (dbUser != null && dbUser.getPassword().equals(user.getPassword())) {
            responseDTO.setData(dbUser);
            responseDTO.setToken(TokenUtil.sign(dbUser.getUserName(), dbUser.getPassword()));
            responseDTO.setMessage(Constant.LOGIN_SUCCEED);
        } else {
            responseDTO.setMessage(Constant.LOGIN_FAILURE);
        }
        return responseDTO;
    }

    /**
     * 上传图片并存入avatar到数据库
     *
     * @param file
     * @param request
     * @return
     */
    @Override
    public ResponseDTO uploadImage(MultipartFile file, HttpServletRequest request) {
        ResponseDTO responseDTO = new ResponseDTO();
        boolean succeed = false;
        if (!file.isEmpty()) {
            try {
                String md5FileName = MD5Util.encodeFile(file);
                String filePath = file.getOriginalFilename();
                filePath = filePath.replace(file.getName(), md5FileName);
                //windows
                String savePath = request.getSession().getServletContext().getRealPath(MyFileUtil.imgPath + filePath);
                File targetFile = new File(savePath);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                file.transferTo(targetFile);
                String token = request.getHeader("X-Token");
                String userName = TokenUtil.getUserName(token);
                User user = userDao.selectUserByName(userName);
                if (user != null) {
                    user.setAvatar(MyFileUtil.ServerAddress + MyFileUtil.imgRequest + MyFileUtil.imgPath + filePath);
                    userDao.updateUserByID(user);
                    succeed = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        responseDTO.setMessage(succeed ? Constant.UPLOAD_SUCCEED:Constant.UPLOAD_FAILURE);
        return responseDTO;
    }
}
