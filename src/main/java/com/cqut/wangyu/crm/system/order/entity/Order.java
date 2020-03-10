package com.cqut.wangyu.crm.system.order.entity;

/**
 * @ClassName Order
 * @Description 客户跟进信息实体类
 * @Author ChongqingWangYu
 * @DateTime 2019/11/22 14:00
 * @GitHub https://github.com/ChongqingWangYu
 */
public class Order {
    private Integer orderID;
    private Integer customerID;
    private Integer contactID;
    private String orderName;
    private String orderDate;
    private Float orderAmount;
    private String orderNote;

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public Integer getContactID() {
        return contactID;
    }

    public void setContactID(Integer contactID) {
        this.contactID = contactID;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Float getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Float orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderNote() {
        return orderNote;
    }

    public void setOrderNote(String orderNote) {
        this.orderNote = orderNote;
    }
}
