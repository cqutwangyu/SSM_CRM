package com.cqut.wangyu.crm.entity;

/**
 * @ClassName User
 * @Description 用户实体
 * @Author ChongqingWangYu
 * @DateTime 2019/5/28 14:23
 * @GitHub https://github.com/ChongqingWangYu
 */
public class User {
    private int id;
    private String userName;
    private String password;

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
