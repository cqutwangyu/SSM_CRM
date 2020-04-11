package com.cqut.wangyu.crm.system.dto;

import com.cqut.wangyu.crm.system.user.entity.User;

/**
 * @author wangyu4017@sefonsoft.com
 * @title: GrantUser
 * @projectName SSM_CRM
 * @description: TODO
 * @date 2020/4/11 15:28
 */
public class GrantedUser {
    private String Token;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUserInfo(User user) {
        this.user = user;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
