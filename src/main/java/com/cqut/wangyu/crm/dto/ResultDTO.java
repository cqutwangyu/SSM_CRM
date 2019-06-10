package com.cqut.wangyu.crm.dto;

import com.cqut.wangyu.crm.entity.User;

/**
 * @ClassName ResultDTO
 * @Description
 * @Author ChongqingWangYu
 * @DateTime 2019/6/4 14:57
 * @GitHub https://github.com/ChongqingWangYu
 */
public class ResultDTO {
    private Integer code;
    private String message;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
