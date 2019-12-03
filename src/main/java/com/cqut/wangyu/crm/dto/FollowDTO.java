package com.cqut.wangyu.crm.dto;

/**
 * @ClassName Follow
 * @Description 客户跟进信息实体类
 * @Author ChongqingWangYu
 * @DateTime 2019/11/22 14:00
 * @GitHub https://github.com/ChongqingWangYu
 */
public class FollowDTO {
    private Integer followID;
    private Integer customerID;
    private String customerName;
    private String followContent;
    private String followDate;
    //1、电话 2、短信 3、QQ 4、微信 5、邮箱
    private Integer followType;
    private Integer contactID;
    private String contactName;
    private String contactPhone;
    private String contactPosition;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactPosition() {
        return contactPosition;
    }

    public void setContactPosition(String contactPosition) {
        this.contactPosition = contactPosition;
    }

    public Integer getContactID() {
        return contactID;
    }

    public void setContactID(Integer contactID) {
        this.contactID = contactID;
    }

    public Integer getFollowID() {
        return followID;
    }

    public void setFollowID(Integer followID) {
        this.followID = followID;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public String getFollowContent() {
        return followContent;
    }

    public void setFollowContent(String followContent) {
        this.followContent = followContent;
    }

    public String getFollowDate() {
        return followDate;
    }

    public void setFollowDate(String followDate) {
        this.followDate = followDate;
    }

    public Integer getFollowType() {
        return followType;
    }

    public void setFollowType(Integer followType) {
        this.followType = followType;
    }
}
