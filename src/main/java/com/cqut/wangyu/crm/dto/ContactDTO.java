package com.cqut.wangyu.crm.dto;

/**
 * @ClassName Contact
 * @Description 客户联系人实体类
 * @Author ChongqingWangYu
 * @DateTime 2019/11/22 13:58
 * @GitHub https://github.com/ChongqingWangYu
 */
public class ContactDTO {
    private Integer contactID;
    private Integer customerID;
    private String contactPosition;
    private String contactName;
    private String customerName;
    //1、男 2、女
    private Integer contactSex;
    private String contactPhone;
    private String contactQQ;
    private String contactEmail;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getContactID() {
        return contactID;
    }

    public void setContactID(Integer contactID) {
        this.contactID = contactID;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public String getContactPosition() {
        return contactPosition;
    }

    public void setContactPosition(String contactPosition) {
        this.contactPosition = contactPosition;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public Integer getContactSex() {
        return contactSex;
    }

    public void setContactSex(Integer contactSex) {
        this.contactSex = contactSex;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactQQ() {
        return contactQQ;
    }

    public void setContactQQ(String contactQQ) {
        this.contactQQ = contactQQ;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
