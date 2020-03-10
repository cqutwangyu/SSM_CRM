package com.cqut.wangyu.crm.utils;

import com.cqut.wangyu.crm.system.customer.entity.Point;
import net.sf.json.JSONObject;
import org.checkerframework.checker.units.qual.K;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;

/**
 * @ClassName BmapUtil
 * @Description 百度地图工具类
 * @Author ChongqingWangYu
 * @DateTime 2020/3/10 10:46
 * @GitHub https://github.com/ChongqingWangYu
 */
public class BmapUtil {
    //服务端
    private static final String AK ="qtUd61sMyFodrafw3VEjHLAL5W3CTCNa";
    //浏览器端
    private static final String AK1 ="yn2v847biv4XMGFvwuv6hpejVngqPpH7";

    public static void main(String[] args) {
        String dom = "北京王府井";
        Point coordinate = getCoordinate(dom);
        System.out.println(coordinate.toString());
    }
    // 调用百度地图API根据地址，获取坐标
    public static Point getCoordinate(String address) {
        if (address != null && !"".equals(address)) {
            address = address.replaceAll("\\s*", "").replace("#", "栋");
            String url = "http://api.map.baidu.com/geocoding/v3/?address=" + address + "&output=json&ak=" + AK;
            String json = loadJSON(url);
            if (json != null && !"".equals(json)) {
                JSONObject obj = JSONObject.fromObject(json);
                if ("0".equals(obj.getString("status"))) {
                    double lng = obj.getJSONObject("result").getJSONObject("location").getDouble("lng"); // 经度
                    double lat = obj.getJSONObject("result").getJSONObject("location").getDouble("lat"); // 纬度
                    DecimalFormat df = new DecimalFormat("#.######");
                    Point point = new Point(address,lng,lat);
//                    return df.format(lng) + "," + df.format(lat);
                    return point;
                }
            }
        }
        return null;
    }

    private static String loadJSON(String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {} catch (IOException e) {}
        return json.toString();
    }
}
