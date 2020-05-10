package com.cqut.wangyu.crm.system.user.service;

import com.alibaba.fastjson.JSONObject;
import com.cqut.wangyu.crm.exception.CRMException;
import com.cqut.wangyu.crm.framework.AbstractService;
import com.cqut.wangyu.crm.system.dto.GrantedUser;
import com.cqut.wangyu.crm.system.user.dao.UserDao;
import com.cqut.wangyu.crm.system.user.entity.User;
import com.cqut.wangyu.crm.utils.*;
import com.wy.sso.user.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Description 用户服务实例（负责业务逻辑）
 * @Author ChongqingWangYu
 * @DateTime 2019/5/28 14:15
 * @GitHub https://github.com/ChongqingWangYu
 */
@Service
public class UserServiceImpl extends AbstractService implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public String register(User user) throws Exception {
        User dbUser = userDao.selectUserByName(user.getUserName());
        if (dbUser == null) {
            String md5Password = MD5Util.encode(user.getPassword());
            user.setPassword(md5Password);
            return userDao.insertUser(user) == 1 ? Constant.REGISTER_SUCCEED : Constant.REGISTER_FAILURE;
        } else {
            throw new Exception(CRMException.INPUT_USER_NAME_ALREADY_EXISTS);
        }
    }

    @Override
    public User findUserById(Integer id) {
        return userDao.selectUserByID(id);
    }

    @Override
    public User findUserByName(String userName) {
        return userDao.selectUserByName(userName);
    }

    @Override
    public String deleteUserByID(Integer id) {
        return userDao.deleteUserByID(id) == 1 ? Constant.DELETE_SUCCEED : Constant.DELETE_FAILURE;
    }

    @Override
    public String updateUserByID(User user) {
        return userDao.updateUserByID(user) == 1 ? Constant.UPDATE_SUCCEED : Constant.UPDATE_FAILURE;
    }

    @Override
    public List<User> findAllUser() {
        return userDao.selectAll();
    }

    @Override
    public GrantedUser login(User user) {
        String md5Password = MD5Util.encode(user.getPassword());
        user.setPassword(md5Password);
        User dbUser = userDao.selectUserByName(user.getUserName());
        GrantedUser grantedUser = new GrantedUser();
        //验证用户是否存在、密码是否正确
        if (dbUser != null && dbUser.getPassword().equals(user.getPassword())) {
            grantedUser.setUserInfo(dbUser);
            grantedUser.setToken(TokenUtil.sign(dbUser.getUserName(), dbUser.getPassword()));
        }
        return grantedUser;
    }

    /**
     * 上传图片并存入avatar到数据库
     *
     * @param file
     * @param request
     * @return
     */
    @Override
    public String uploadImage(MultipartFile file, HttpServletRequest request) throws Exception {
        boolean succeed = false;
        if (!file.isEmpty()) {
            String md5FileName = MD5Util.encodeFile(file);
            String filePath = file.getOriginalFilename();
            filePath = filePath.replace(file.getName(), md5FileName);
            //windows
            String savePath = request.getSession().getServletContext().getRealPath(MyFileUtil.IMG_PATH + filePath);
            File targetFile = new File(savePath);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            file.transferTo(targetFile);
            UserInfo userInfo = currentUser();
            if (userInfo != null) {
                userInfo.setAvatar(MyFileUtil.LOCAL_HOST_SERVER_ADDRESS + MyFileUtil.IMG_REQUEST + MyFileUtil.IMG_PATH + filePath);
                String url = Constants.SSO_IP_PORT + "/user/update";
                try {
                    Map<String, Object> param=new HashMap<>();
                    param.put("flowId",userInfo.getFlowId());
                    param.put("avatar",userInfo.getAvatar());
                    String s = ClientUtil.doPutParam(url, getToken(), param);
//                    String s = ClientUtil.doPutObject(url, getToken(), userInfo);
                    JSONObject obj = JSONObject.parseObject(s);
                    String message = obj.getString("message");
                    succeed = message.equals("succeed") ? true : false;
                } catch (Exception e) {
                    throw new Exception(url + "请求错误" + e.getMessage());
                }
            }
        }
        return succeed ? Constant.UPLOAD_SUCCEED : Constant.UPLOAD_FAILURE;
    }
}
