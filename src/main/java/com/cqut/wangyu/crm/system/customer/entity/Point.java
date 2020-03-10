package com.cqut.wangyu.crm.system.customer.entity;

/**
 * @ClassName Point
 * @Description 坐标点
 * @Author ChongqingWangYu
 * @DateTime 2020/3/10 10:59
 * @GitHub https://github.com/ChongqingWangYu
 */
public class Point {
    private String address;
    private double lng;
    private double lat;

    public Point(String address, double lng, double lat) {
        this.address = address;
        this.lng = lng;
        this.lat = lat;
    }
    public Point() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "Point{" +
                "address='" + address + '\'' +
                ", lng=" + lng +
                ", lat=" + lat +
                '}';
    }
}
