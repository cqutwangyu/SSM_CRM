package com.cqut.wangyu.crm.utils;

import org.springframework.util.DigestUtils;

/**
 * @ClassName MD5Util
 * @Description MD5加密工具
 * @Author ChongqingWangYu
 * @DateTime 2019/6/14 18:18
 * @GitHub https://github.com/ChongqingWangYu
 */
public class MD5Util {

    public static String encode(String input) {
        return DigestUtils.md5DigestAsHex(input.getBytes());
    }
}
