package com.cqut.wangyu.crm.entity;

import com.cqut.wangyu.crm.utils.MyFileUtil;

/**
 * @ClassName User
 * @Description 用户实体
 * @Author ChongqingWangYu
 * @DateTime 2019/5/28 14:23
 * @GitHub https://github.com/ChongqingWangYu
 */
public class User {

    private Integer id;
    private String userName;
    private String password;
    private String petName;
    private String avatar;


    public User() {
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
                ", petName='" + petName + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
