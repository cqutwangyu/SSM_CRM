package com.cqut.wangyu.crm.system.charts;

import com.alibaba.fastjson.JSONObject;
import com.cqut.wangyu.crm.system.user.entity.User;
import com.cqut.wangyu.crm.system.user.UserController;
import com.cqut.wangyu.crm.utils.ClientUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserControllerTest
 * @Description
 * @Author ChongqingWangYu
 * @DateTime 2019/6/4 12:36
 * @GitHub https://github.com/ChongqingWangYu
 */
public class UserControllerTest extends BaseTest {
    @Autowired
    private UserController userController;

    @Test
    public void getAllUser() throws Exception {
        Map<String, String> paramMap=new HashMap<>();
        String url="http://localhost:8087/user/";
        paramMap.put("userName","admin001");
        paramMap.put("password","admin001");
        String s = ClientUtil.doPost(url+"login", paramMap);
        System.out.println(s);
        JSONObject obj = JSONObject.parseObject(s);
        String token = obj.getString("data");

        paramMap.clear();
        paramMap.put("token",token);
        System.out.println("token:"+token);
        s = ClientUtil.doGet(url+"getAllUser", paramMap);
        System.out.println(s);


    }

    @Test
    public void registerTest() {
        User user = new User();
        user.setUserName("admin");
        user.setPassword("ddd");
        userController.register(user);
    }

    @Test
    public void findUserByIdTest() {
        userController.findUserById(1);
    }

    @Test
    public void findUserByNameTest() {
        userController.findUserByName("admin");
    }

    @Test
    public void deleteTest() {
        userController.deleteUserByID(1);
    }

    @Test
    public void updateTest() {
        User user = new User();
        user.setId(1);
        user.setUserName("admin");
        user.setPassword("ddd");
        userController.updateUserByID(user);
    }

    @Test
    public void findAllTest() {
        userController.findAllUser();
    }
}
