package com.cqut.wangyu.crm.dto;

/**
 * @ClassName ResponseDTO
 * @Description
 * @Author ChongqingWangYu
 * @DateTime 2019/6/4 14:57
 * @GitHub https://github.com/ChongqingWangYu
 */
public class ResponseDTO {
    private Integer code = 20000;
    private String message;
    private String token;
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

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

    @Override
    public String toString() {
        return "{" +
                "\"code\":" + code + "," +
                "\"message\":\"" + message + "\"" + "," +
                "\"token\" :\"" + token + "\" " + ", " +
                "\"data\" :" + data +
                '}';
    }
}
