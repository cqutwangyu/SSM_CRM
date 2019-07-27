package com.cqut.wangyu.crm.entity;

import java.util.Objects;

/**
 * @ClassName Customer
 * @Description 客户实体类
 * @Author ChongqingWangYu
 * @DateTime 2019/6/17 8:39
 * @GitHub https://github.com/ChongqingWangYu
 */
public class Customer {

    private Integer cusId;
    private String cusNo;
    private String cusName;
    private String cusPhone;
    private String cusAddr;
    private String cusUrl;
    private String cusLevel;
    private String cusCredit;

    public Integer getCusId() {
        return cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }

    public String getCusNo() {
        return cusNo;
    }

    public void setCusNo(String cusNo) {
        this.cusNo = cusNo;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    public String getCusAddr() {
        return cusAddr;
    }

    public void setCusAddr(String cusAddr) {
        this.cusAddr = cusAddr;
    }

    public String getCusUrl() {
        return cusUrl;
    }

    public void setCusUrl(String cusUrl) {
        this.cusUrl = cusUrl;
    }

    public String getCusLevel() {
        return cusLevel;
    }

    public void setCusLevel(String cusLevel) {
        this.cusLevel = cusLevel;
    }

    public String getCusCredit() {
        return cusCredit;
    }

    public void setCusCredit(String cusCredit) {
        this.cusCredit = cusCredit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        return Objects.equals(cusId, customer.cusId) &&
                Objects.equals(cusNo, customer.cusNo) &&
                Objects.equals(cusName, customer.cusName) &&
                Objects.equals(cusPhone, customer.cusPhone) &&
                Objects.equals(cusAddr, customer.cusAddr) &&
                Objects.equals(cusUrl, customer.cusUrl) &&
                Objects.equals(cusLevel, customer.cusLevel) &&
                Objects.equals(cusCredit, customer.cusCredit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cusId, cusNo, cusName, cusPhone, cusAddr, cusUrl, cusLevel, cusCredit);
    }
}
