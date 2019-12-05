package com.cqut.wangyu.crm.entity;

import java.util.Objects;

/**
 * @ClassName Customer
 * @Description 客户实体类
 * @Author ChongqingWangYu
 * @DateTime 2019/11/22 13:56
 * @GitHub https://github.com/ChongqingWangYu
 */
public class Customer {
    private Integer customerID;
    private String customerName;
    private String customerPhone;
    private String customerAddress;
    private String customerUrl;
    //1、有意向 2、无意向 3、已成交
    private Integer customerType;
    //1、待商谈 2、已商谈 3、待签约 4、已签约
    private Integer customerStatus;
    private String customerDate;

    public String getCustomerDate() {
        return customerDate;
    }

    public void setCustomerDate(String customerDate) {
        this.customerDate = customerDate;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerUrl() {
        return customerUrl;
    }

    public void setCustomerUrl(String customerUrl) {
        this.customerUrl = customerUrl;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public Integer getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(Integer customerStatus) {
        this.customerStatus = customerStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerID, customer.customerID) &&
                Objects.equals(customerName, customer.customerName) &&
                Objects.equals(customerPhone, customer.customerPhone) &&
                Objects.equals(customerAddress, customer.customerAddress) &&
                Objects.equals(customerUrl, customer.customerUrl) &&
                Objects.equals(customerType, customer.customerType) &&
                Objects.equals(customerStatus, customer.customerStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerID, customerName, customerPhone, customerAddress, customerUrl, customerType, customerStatus);
    }
}
