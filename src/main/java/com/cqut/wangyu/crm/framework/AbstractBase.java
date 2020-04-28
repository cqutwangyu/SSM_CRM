package com.cqut.wangyu.crm.framework;

import com.cqut.wangyu.crm.redis.RedisCache;
import com.wy.sso.user.domain.UserInfo;
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
    private RedisCache redisCache;

    @Autowired
    protected HttpServletRequest request;

    protected UserInfo currentUser() {
        return redisCache.getCacheObject(getToken());
    }

    protected String getToken() {
        return request.getHeader("X-Token");
    }


    protected String getUserName() {
        return currentUser().getUserName();
    }
}
