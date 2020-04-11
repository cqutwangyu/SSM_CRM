package com.cqut.wangyu.crm.framework;

import com.cqut.wangyu.crm.system.user.dao.UserDao;
import com.cqut.wangyu.crm.system.user.entity.User;
import com.cqut.wangyu.crm.utils.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangyu4017@sefonsoft.com
 * @title: AbstractController
 * @projectName SSM_CRM
 * @description: TODO
 * @date 2020/4/1 21:42
 */
public class AbstractBase {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDao userDao;

    @Autowired
    protected HttpServletRequest request;

    protected User currentUser() {
        return getUser(getUserName());
    }

    protected String getToken() {
        return request.getHeader("X-Token");
    }

    protected User getUser(String userName) {
        return userDao.selectUserByName(userName);
    }

    protected String getUserName() {
        return TokenUtil.getUserName(getToken());
    }
}
