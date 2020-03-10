package com.cqut.wangyu.crm.utils;

import com.itextpdf.text.Image;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类名 : Tools 描述: 工具类 创建人: ZhengQiu 创建日期: 2016年3月18日
 * <p>
 * 修改历史 修改日期: <修改日期，格式：YYYY-MM-DD> 修改人: <姓名/工号> 修改原因/修改内容: <修改原因描述> <问题单号： DTSXXXXXXXX>
 */
public abstract class Tools {

    private static final int NUM_F_2 = -2;

    private static final int NUM_F_1 = -1;

    private static final int NUM_0 = 0;

    private static final int NUM_1 = 1;

    private static final int NUM_2 = 2;

    private static final int NUM_3 = 3;

    private static final int NUM_4 = 4;

    private static final int NUM_5 = 5;

    private static final int NUM_6 = 6;

    private static final int NUM_7 = 7;

    private static final int NUM_8 = 8;

    private static final int NUM_9 = 9;

    private static final int NUM_10 = 10;

    private static final int NUM_11 = 11;

    private static final int NUM_12 = 12;

    private static final int NUM_13 = 13;

    private static final int NUM_14 = 14;

    private static final int NUM_15 = 15;

    private static final int NUM_16 = 16;

    /**
     * 方法名: isNull 描述：<方法的功能和实现思路>
     *
     * @param obj 参数
     * @return boolean 是否
     * <p>
     * 创建人: gaoxiong 创建日期: 2016年4月11日
     * <p>
     * 修改历史 修改日期：<创建日期，格式：YYYY-MM-DD> 修改人： <姓名/工号> 修改原因/修改内容: <修改原因描述> <问题单DTSXXXXXXXX>
     */
    public static boolean isNull(Object obj) {
        return obj == null || "null".equals(obj);
    }

    /**
     * 方法名: isNotNull 描述：<方法的功能和实现思路>
     *
     * @param obj 参数
     * @return boolean 是否
     * <p>
     * 创建人: gaoxiong 创建日期: 2016年4月11日
     * <p>
     * 修改历史 修改日期：<创建日期，格式：YYYY-MM-DD> 修改人： <姓名/工号> 修改原因/修改内容: <修改原因描述> <问题单DTSXXXXXXXX>
     */
    public static boolean isNotNull(Object obj) {
        return obj != null && !"null".equals(obj) && !"".equals(obj);
    }

    /**
     * 方法名: isNull 描述：<方法的功能和实现思路>
     *
     * @param str 参数
     * @return boolean 是否
     * <p>
     * 创建人: gaoxiong 创建日期: 2016年4月11日
     * <p>
     * 修改历史 修改日期：<创建日期，格式：YYYY-MM-DD> 修改人： <姓名/工号> 修改原因/修改内容: <修改原因描述> <问题单DTSXXXXXXXX>
     */
    public static boolean isNull(String str) {
        return str == null || "".equals(str.trim());
    }

    /**
     * 方法名: isNotNull 描述：<方法的功能和实现思路>
     *
     * @param str 参数
     * @return boolean 是否
     * <p>
     * 创建人: gaoxiong 创建日期: 2016年4月11日
     * <p>
     * 修改历史 修改日期：<创建日期，格式：YYYY-MM-DD> 修改人： <姓名/工号> 修改原因/修改内容: <修改原因描述> <问题单DTSXXXXXXXX>
     */
    public static boolean isNotNull(String str) {
        return str != null && !"".equals(str.trim());
    }

    /**
     * 方法名: isNullHtml 描述：<方法的功能和实现思路>
     *
     * @param obj 参数
     * @return boolean 是否
     * <p>
     * 创建人: gaoxiong 创建日期: 2016年4月11日
     * <p>
     * 修改历史 修改日期：<创建日期，格式：YYYY-MM-DD> 修改人： <姓名/工号> 修改原因/修改内容: <修改原因描述> <问题单DTSXXXXXXXX>
     */
    public static boolean isNullHtml(Object obj) {
        return obj == null || "null".equals(obj) || "undefined".equals(obj);
    }

    /**
     * 方法名: isNotNullHtml 描述：<方法的功能和实现思路>
     *
     * @param obj 参数
     * @return boolean 是否
     * <p>
     * 创建人: gaoxiong 创建日期: 2016年4月11日
     * <p>
     * 修改历史 修改日期：<创建日期，格式：YYYY-MM-DD> 修改人： <姓名/工号> 修改原因/修改内容: <修改原因描述> <问题单DTSXXXXXXXX>
     */
    public static boolean isNotNullHtml(Object obj) {
        return obj != null && !"null".equals(obj) && !"".equals(obj) && !"undefined".equals(obj);
    }

    /**
     * 方法名: isNullHtml 描述：<方法的功能和实现思路>
     *
     * @param str 参数
     * @return boolean 是否
     * <p>
     * 创建人: gaoxiong 创建日期: 2016年4月11日
     * <p>
     * 修改历史 修改日期：<创建日期，格式：YYYY-MM-DD> 修改人： <姓名/工号> 修改原因/修改内容: <修改原因描述> <问题单DTSXXXXXXXX>
     */
    public static boolean isNullHtml(String str) {
        return str == null || "".equals(str.trim()) || "null".equals(str.trim()) || "undefined".equals(str.trim());
    }

    /**
     * 方法名: isNotNullHtml 描述：<方法的功能和实现思路>
     *
     * @param str 参数
     * @return boolean 是否
     * <p>
     * 创建人: gaoxiong 创建日期: 2016年4月11日
     * <p>
     * 修改历史 修改日期：<创建日期，格式：YYYY-MM-DD> 修改人： <姓名/工号> 修改原因/修改内容: <修改原因描述> <问题单DTSXXXXXXXX>
     */
    public static boolean isNotNullHtml(String str) {
        return str != null && !"".equals(str.trim()) && !"null".equals(str.trim()) && !"undefined".equals(str.trim());
    }

    /**
     * 方法名: isNotNullAndZero 描述：<方法的功能和实现思路>
     *
     * @param doub 参数
     * @return boolean 是否
     * <p>
     * 创建人: gaoxiong 创建日期: 2016年4月11日
     * <p>
     * 修改历史 修改日期：<创建日期，格式：YYYY-MM-DD> 修改人： <姓名/工号> 修改原因/修改内容: <修改原因描述> <问题单DTSXXXXXXXX>
     */
    public static boolean isNotNullAndZero(Double doub) {
        return doub != null && doub.floatValue() != 0f;
    }

    /**
     * 方法名: isNotNullAndZero 描述：<方法的功能和实现思路>
     *
     * @param doub 参数
     * @return boolean 是否
     * <p>
     * 创建人: gaoxiong 创建日期: 2016年4月11日
     * <p>
     * 修改历史 修改日期：<创建日期，格式：YYYY-MM-DD> 修改人： <姓名/工号> 修改原因/修改内容: <修改原因描述> <问题单DTSXXXXXXXX>
     */
    public static boolean isNotNullAndZero(BigDecimal doub) {
        return doub != null && doub.compareTo(BigDecimal.ZERO) != 0;
    }

    /**
     * 方法名: isEmpty 描述：<方法的功能和实现思路>
     *
     * @param array 参数
     * @return boolean 是否
     * <p>
     * 创建人: gaoxiong 创建日期: 2016年4月11日
     * <p>
     * 修改历史 修改日期：<创建日期，格式：YYYY-MM-DD> 修改人： <姓名/工号> 修改原因/修改内容: <修改原因描述> <问题单DTSXXXXXXXX>
     */
    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 方法名: isNotEmpty 描述：<方法的功能和实现思路>
     *
     * @param array 参数
     * @return boolean 是否
     * <p>
     * 创建人: gaoxiong 创建日期: 2016年4月11日
     * <p>
     * 修改历史 修改日期：<创建日期，格式：YYYY-MM-DD> 修改人： <姓名/工号> 修改原因/修改内容: <修改原因描述> <问题单DTSXXXXXXXX>
     */
    public static boolean isNotEmpty(Object[] array) {
        return array != null && array.length > 0;
    }

    /**
     * 方法名: isEmpty 描述：<方法的功能和实现思路>
     *
     * @param list 参数
     * @return boolean 是否
     * <p>
     * 创建人: gaoxiong 创建日期: 2016年4月11日
     * <p>
     * 修改历史 修改日期：<创建日期，格式：YYYY-MM-DD> 修改人： <姓名/工号> 修改原因/修改内容: <修改原因描述> <问题单DTSXXXXXXXX>
     */
    public static boolean isEmpty(List<?> list) {
        if (list == null || list.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * 方法名: isNotEmpty 描述：<方法的功能和实现思路>
     *
     * @param list 参数
     * @return boolean 是否
     * <p>
     * 创建人: gaoxiong 创建日期: 2016年4月11日
     * <p>
     * 修改历史 修改日期：<创建日期，格式：YYYY-MM-DD> 修改人： <姓名/工号> 修改原因/修改内容: <修改原因描述> <问题单DTSXXXXXXXX>
     */
    public static boolean isNotEmpty(List<?> list) {
        if (list != null && !list.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * 方法名: formatDateToStr 描述：<方法的功能和实现思路>
     *
     * @param type 类型
     * @param time 时间
     * @return String 结果
     * <p>
     * 创建人: gaoxiong 创建日期: 2016年4月11日
     * <p>
     * 修改历史 修改日期：<创建日期，格式：YYYY-MM-DD> 修改人： <姓名/工号> 修改原因/修改内容: <修改原因描述> <问题单DTSXXXXXXXX>
     */
    public static String formatDateToStr(int type, Date time) {
        if (time == null) {
            return "";
        }
        String format = getPattern(type);
        if (Tools.isNotNull(format)) {
            return new SimpleDateFormat(format).format(Long.valueOf(time.getTime()));
        } else {
            return "";
        }
    }

    /**
     * 方法名: formateStrToDate 描述：<方法的功能和实现思路>
     *
     * @param type 类型
     * @param time 时间
     * @return 结果
     * @throws ParseException 异常
     *                        <p>
     *                        创建人: Administrator 创建日期: 2016年4月11日
     *                        <p>
     *                        修改历史 修改日期：<创建日期，格式：YYYY-MM-DD> 修改人： <姓名/工号> 修改原因/修改内容: <修改原因描述> <问题单DTSXXXXXXXX>
     */
    public static Date formateStrToDate(int type, String time) throws ParseException {
        if (Tools.isNull(time)) {
            return null;
        }
        String format = getPattern(type);
        if (Tools.isNotNull(format)) {
            return new SimpleDateFormat(format).parse(time);
        } else {
            return null;
        }
    }

    /**
     * 方法名: getPattern 描述：<方法的功能和实现思路>
     *
     * @param formatIndex 参数
     * @return String 结果
     * <p>
     * 创建人: 高雄 创建日期: 2016年4月11日
     * <p>
     * 修改历史 修改日期：<创建日期，格式：YYYY-MM-DD> 修改人： <姓名/工号> 修改原因/修改内容: <修改原因描述> <问题单DTSXXXXXXXX>
     */
    public static String getPattern(int formatIndex) {
        String format = null;
        switch (formatIndex) {
            case NUM_F_2:
                format = "HH:mm:ss.S";
                break;
            case NUM_F_1:
                format = "yyyy-MM-dd HH:mm:ss.S";
                break;
            case NUM_0:
                format = "yyyy-MM-dd HH:mm:ss";
                break;
            case NUM_1:
                format = "yyyy-MM-dd 00:00:00";
                break;
            case NUM_2:
                format = "yyyy-MM-dd 23:59:59";
                break;
            case NUM_3:
                format = "yyyy-MM-dd";
                break;
            case NUM_4:
                format = "yyyy-MM";
                break;
            case NUM_5:
                format = "yyyy-MM-dd HH:mm:ss";
                break;
            case NUM_6:
                format = "yyyy-01-01 00:00:00";
                break;
            case NUM_7:
                format = "yyyy-12-31 23:59:59";
                break;
            case NUM_8:
                format = "yyyy-MM-01 00:00:00";
                break;
            case NUM_9:
                format = "yyyyMMddHHmmss";
                break;
            case NUM_10:
                format = "yyyyMMddHHmmssS";
                break;
            case NUM_11:
                // format = "yyyy年MM月dd日";
                break;
            case NUM_12:
                format = "yyyyMMdd-HHmmssS";
                break;
            case NUM_13:
                format = "yyyy";
                break;
            case NUM_14:
                format = "yyyy-MM-dd HH:mm";
                break;
            case NUM_15:
                format = "yyyy-MM-01";
                break;
            case NUM_16:
                // format = "yyyy年MM月";
                break;
            case 17:
                format = "yyyy/MM/dd";
                break;
            default:
                format = "yyyy-MM-dd HH:mm:ss";
        }
        return format;
    }

    /**
     * 方法名: createUUID 描述：<方法的功能和实现思路>
     *
     * @return String
     * <p>
     * 创建人: Administrator 创建日期: 2016年4月11日
     * <p>
     * 修改历史 修改日期：<创建日期，格式：YYYY-MM-DD> 修改人： <姓名/工号> 修改原因/修改内容: <修改原因描述> <问题单DTSXXXXXXXX>
     */
    public static String createUUID() {
        // return UUID.randomUUID().toString().replace("-", "");
        return UUID.randomUUID().toString();
    }

    /**
     * 方法名: fixZero 描述：数字前面补0
     *
     * @param number 待补0的数字
     * @param size   总共多少位
     * @return String 补0后的数字字符串
     * <p>
     * 创建人: ZhengQiu 创建日期: 2016年3月18日
     * <p>
     * 修改历史 修改日期：<创建日期，格式：YYYY-MM-DD> 修改人： <姓名/工号> 修改原因/修改内容: <修改原因描述> <问题单DTSXXXXXXXX>
     */
    public static String fixZero(int number, int size) {
        if (number < 0) {
            number = 0;
        }
        String str = String.valueOf(number);
        int times = size - str.length();
        for (int i = 0; i < times; i++) {
            str = "0" + str;
        }
        return str;
    }

    /**
     * 方法名: findStrFormStrByRegex 描述：根据正则，从一个字符串里面取出正则匹配的所有字符串
     *
     * @param form  参数
     * @param regex 匹配字符
     * @return List<String> 结果
     * <p>
     * 创建人: ZhengQiu 创建日期: 2016年3月18日
     * <p>
     * 修改历史 修改日期：<创建日期，格式：YYYY-MM-DD> 修改人： <姓名/工号> 修改原因/修改内容: <修改原因描述> <问题单DTSXXXXXXXX>
     */
    public static List<String> findStrFormStrByRegex(String form, String regex) {
        List<String> list = new ArrayList<String>();
        String data = null;
        if (isNotNull(form) && regex != null) {
            Matcher m = Pattern.compile(regex).matcher(form);
            while (m.find()) {
                data = m.group();
                list.add(data);
            }
        }
        return list;
    }

    /**
     * 方法名: splitAndFilterHtmlStr 描述：去掉所有html元素
     *
     * @param input 参数
     * @return String 结果
     * <p>
     * 创建人: ZhengQiu 创建日期: 2016年3月18日
     * <p>
     * 修改历史 修改日期：<创建日期，格式：YYYY-MM-DD> 修改人： <姓名/工号> 修改原因/修改内容: <修改原因描述> <问题单DTSXXXXXXXX>
     */
    public static String splitAndFilterHtmlStr(String input) {
        if (Tools.isNull(input)) {
            return "";
        }
        // 去掉所有html元素,
        String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");
        str = str.replaceAll("[(/>)<]", "");
        return str;
    }

    /**
     * 方法名: delHTMLTag 描述：删除字符串的html标签
     *
     * @param htmlStr 输入字符串
     * @return String 没有HTML标签的字符串
     * <p>
     * 创建人: ZhengQiu 创建日期: 2016年3月18日
     * <p>
     * 修改历史 修改日期：<创建日期，格式：YYYY-MM-DD> 修改人： <姓名/工号> 修改原因/修改内容: <修改原因描述> <问题单DTSXXXXXXXX>
     */
    public static String delHTMLTag(String htmlStr) {
        if (isNull(htmlStr)) {
            return "";
        }
        String regExScript = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
        String regExStyle = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
        String regExHtml = "<[^>]+>"; // 定义HTML标签的正则表达式

        Pattern pScript = Pattern.compile(regExScript, Pattern.CASE_INSENSITIVE);
        Matcher mScript = pScript.matcher(htmlStr);
        htmlStr = mScript.replaceAll(""); // 过滤script标签

        Pattern pStyle = Pattern.compile(regExStyle, Pattern.CASE_INSENSITIVE);
        Matcher mStyle = pStyle.matcher(htmlStr);
        htmlStr = mStyle.replaceAll(""); // 过滤style标签

        Pattern pHtml = Pattern.compile(regExHtml, Pattern.CASE_INSENSITIVE);
        Matcher mHtml = pHtml.matcher(htmlStr);
        htmlStr = mHtml.replaceAll(""); // 过滤html标签

        return htmlStr.trim(); // 返回文本字符串
    }

    /**
     * 方法名: getCellValue 描述：获取excel单元格内容
     *
     * @param c 参数
     * @return String
     * <p>
     * 创建人: ZhengQiu 创建日期: 2016年3月18日
     * <p>
     * 修改历史 修改日期：<创建日期，格式：YYYY-MM-DD> 修改人： <姓名/工号> 修改原因/修改内容: <修改原因描述> <问题单DTSXXXXXXXX>
     */
    public static String getCellValue(Cell c) {
        String value = "";
        if (Tools.isNotNull(c)) {
            int cellType = c.getCellType();
            if (cellType == 0) {
                value = String.valueOf(c.getNumericCellValue());
            } else if (cellType == 1) {
                value = c.getStringCellValue();
            }
        }
        return value;
    }

    /**
     * 方法名: list2Str 描述：<方法的功能和实现思路>
     *
     * @param list 参数
     * @return String
     * <p>
     * 创建人: gaoxiong 创建日期: 2016年4月11日
     * <p>
     * 修改历史 修改日期：<创建日期，格式：YYYY-MM-DD> 修改人： <姓名/工号> 修改原因/修改内容: <修改原因描述> <问题单DTSXXXXXXXX>
     */
    public static String list2Str(List<String> list) {
        if (isNotEmpty(list)) {
            StringBuffer sb = new StringBuffer();
            for (String str : list) {
                sb.append(str).append(",");
            }
            sb.setLength(sb.length() - 1);
            return sb.toString();
        }
        return "";
    }

    /**
     * 方法名: objToString 描述：<方法的功能和实现思路>
     *
     * @param obj 对象
     * @return String
     * <p>
     * 创建人: gaoxiong 创建日期: 2016年4月11日
     * <p>
     * 修改历史 修改日期：<创建日期，格式：YYYY-MM-DD> 修改人： <姓名/工号> 修改原因/修改内容: <修改原因描述> <问题单DTSXXXXXXXX>
     */
    public static String objToString(Object obj) {
        if (Tools.isNull(obj)) {
            return null;
        } else {
            return String.valueOf(obj).trim();
        }
    }

    public static Boolean objectToBoolean(Object obj) {
        if (Tools.isNull(obj)) {
            return false;
        } else {
            return Boolean.parseBoolean(String.valueOf(obj).trim());
        }

    }

    /**
     * 方法名: objToString 描述：<方法的功能和实现思路>
     *
     * @param obj 对象
     * @return String
     * <p>
     * 创建人: gaoxiong 创建日期: 2016年4月11日
     * <p>
     * 修改历史 修改日期：<创建日期，格式：YYYY-MM-DD> 修改人： <姓名/工号> 修改原因/修改内容: <修改原因描述> <问题单DTSXXXXXXXX>
     */
    public static String objToStringNonTrim(Object obj) {
        if (Tools.isNull(obj)) {
            return null;
        } else {
            return String.valueOf(obj);
        }
    }

    /**
     * 方法名: objToInt 描述：<object转Int>
     *
     * @param obj 待转参数
     * @return int 转后数字
     * <p>
     * 创建人: gaoxiong 创建日期: 2016年4月21日
     * <p>
     * 修改历史 修改日期：<创建日期，格式：YYYY-MM-DD> 修改人： <姓名/工号> 修改原因/修改内容: <修改原因描述> <问题单DTSXXXXXXXX>
     */
    public static int objToInt(Object obj) {
        int result = 0;
        if (isNull(obj)) {
            return result;
        }
        try {
            result = Integer.parseInt(obj.toString());
            return result;
        } catch (Exception e) {
            return result;
        }
    }

    public static Integer objToInteger(Object obj) {
        Integer result = null;
        if (isNull(obj)) {
            return null;
        }
        try {
            result = Integer.parseInt(obj.toString());
            return result;
        } catch (Exception e) {
            return result;
        }
    }

    /**
     * 方法名: decodeStr 描述：<加密>
     *
     * @param str 加密字符串
     * @return String
     * <p>
     * 创建人: gaoxiong 创建日期: 2016年6月6日
     * <p>
     * 修改历史 修改日期：<创建日期，格式：YYYY-MM-DD> 修改人： <姓名/工号> 修改原因/修改内容: <修改原因描述> <问题单DTSXXXXXXXX>
     */
    public static String decodeStr(String str) {
        if (isNull(str)) {
            return null;
        }
        byte[] debytes;
        try {
            debytes = Base64.decodeBase64(str.getBytes("UTF-8"));
            return new String(debytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 方法名: encodeStr 描述：<解码>
     *
     * @param str 解码字符串
     * @return String
     * <p>
     * 创建人: gaoxiong 创建日期: 2016年6月6日
     * <p>
     * 修改历史 修改日期：<创建日期，格式：YYYY-MM-DD> 修改人： <姓名/工号> 修改原因/修改内容: <修改原因描述> <问题单DTSXXXXXXXX>
     */
    public static String encodeStr(String str) {
        if (isNull(str)) {
            return "";
        }
        byte[] enbytes;
        try {
            enbytes = Base64.encodeBase64Chunked(str.getBytes("UTF-8"));
            return new String(enbytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * 类名 : Tools 描述: <uuid格式验证>
     *
     * @param uuid 待验证的uuid
     * @return 合法true，不合法false
     * <p>
     * 创建人: wangwei 创建日期: 2016年07月08日
     * <p>
     * 修改历史 修改日期: <修改日期，格式：YYYY-MM-DD> 修改人: <wangwei> 修改原因/修改内容: <修改原因描述> <问题单号： DTSXXXXXXXX>
     */
    public static boolean matchesUUID(String uuid) {
        if (Tools.isNull(uuid)) {
            return false;
        }
        String reg = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}";
        return uuid.matches(reg);
    }

    /**
     * 方法名: isStrInArr 描述：<方法的功能和实现思路>
     *
     * @param str 字符串
     * @param arr 字符串数组
     * @return boolean 字符串是否在数组中
     * <p>
     * 创建人: ZhengQiu 创建日期: 2016年7月11日
     * <p>
     * 修改历史 修改日期：<创建日期，格式：YYYY-MM-DD> 修改人： <姓名/工号> 修改原因/修改内容: <修改原因描述> <问题单DTSXXXXXXXX>
     */
    public static boolean isStrInArr(String str, String[] arr) {
        boolean flag = false;

        if (isNull(str) || isEmpty(arr)) {
            return flag;
        }
        for (String string : arr) {
            if (str.trim().equals(string)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public static String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;

        if (size >= gb) {
            return String.format("%.1fG", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0fM" : "%.1fM", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0fK" : "%.1fK", f);
        } else
            return String.format("%dB", size);
    }

    /**
     * 获取本地IP地址
     *
     * @throws SocketException
     */
    public static String getLocalIP() throws UnknownHostException, SocketException {
        if (isWindowsOS()) {
            return InetAddress.getLocalHost().getHostAddress();
        } else {
            return getLinuxLocalIp();
        }
    }

    /**
     * 判断操作系统是否是Windows
     *
     * @return
     */
    public static boolean isWindowsOS() {
        boolean isWindowsOS = false;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().indexOf("windows") > -1) {
            isWindowsOS = true;
        }
        return isWindowsOS;
    }

    /**
     * 获取Linux下的IP地址
     *
     * @return IP地址
     * @throws SocketException
     */
    private static String getLinuxLocalIp() throws SocketException {
        String ip = "";
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                String name = intf.getName();
                if (!name.contains("docker") && !name.contains("lo")) {
                    for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            String ipaddress = inetAddress.getHostAddress().toString();
                            if (!ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80")) {
                                ip = ipaddress;
                            }
                        }
                    }
                }
            }
        } catch (SocketException ex) {
            ip = "127.0.0.1";
        }
        return ip;
    }

    public static Properties loadProperty(String path) throws Exception {
        Properties properties = new Properties();
        InputStream input = null;
        try {
            ClassPathResource resource = new ClassPathResource(path);
            input = resource.getInputStream();
            properties.load(input);
        } catch (IOException e) {
            throw new Exception(e.getMessage());
        } finally {
            IOUtils.closeQuietly(input);
        }
        return properties;
    }

    public static boolean isMessyCode(String strName) {
        Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
        Matcher m = p.matcher(strName);
        String after = m.replaceAll("");
        String temp = after.replaceAll("\\p{P}", "");
        char[] ch = temp.trim().toCharArray();
        float chLength = 0;
        float count = 0;
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (!Character.isLetterOrDigit(c)) {
                if (!isChinese(c)) {
                    count = count + 1;
                }
                chLength++;
            }
        }
        float result = count / chLength;
        if (result > 0.4) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * 方法名: isNumeric 描述：<判断是否为数字>
     *
     * @param str
     * @return boolean
     * <p>
     * 创建人: yangwufu 创建日期: 2017年11月20日
     * <p>
     * 修改历史 修改日期：<创建日期，格式：YYYY-MM-DD> 修改人： <姓名/工号> 修改原因/修改内容: <修改原因描述> <问题单DTSXXXXXXXX>
     */
    public static boolean isNumeric(String str) {
        // 该正则表达式可以匹配所有的数字 包括负数
        Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            return false;// 异常 说明包含非数字。
        }

        Matcher isNum = pattern.matcher(bigStr); // matcher是全匹配
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }


    public static Map<String, Object> handlerRequestParmasAsMap(HttpServletRequest request) {
        Map<String, String[]> paramMap = request.getParameterMap();
        if (null == paramMap) {
            return null;
        }
        String locale = "en_US";
        Map<String, Object> resultValue = new HashMap<String, Object>();
        Cookie cookies[] = request.getCookies();
        if (Tools.isNotEmpty(cookies)) {

            for (Cookie cookie : cookies) {
                if ("LOCALE".equals(cookie.getName().toUpperCase(Locale.getDefault()))) {
                    locale = cookie.getValue();
                    break;
                }
            }
        }
        resultValue.put("locale", locale);
        for (Entry<String, String[]> entry : paramMap.entrySet()) {
            resultValue.put(entry.getKey(), entry.getValue()[0]);
        }
        return resultValue;
    }

    /**
     * 方法名: getPinYinFirstChar 描述：<提取每个汉字的首字母>
     *
     * @param str
     * @return String
     * <p>
     * 创建人: lxb 创建日期: 2018年4月11日
     * <p>
     * 修改历史 修改日期：<创建日期，格式：YYYY-MM-DD> 修改人： <姓名/工号> 修改原因/修改内容: <修改原因描述> <问题单DTSXXXXXXXX>
     */
    public static String getPinYinFirstChar(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c);
            if (pinyinArray != null) {
                sb.append(pinyinArray[0].charAt(0));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 方法名: getPinYin 描述：<提取汉字的全拼字母>
     *
     * @param str
     * @return String
     * <p>
     * 创建人: lxb 创建日期: 2018年4月11日
     * <p>
     * 修改历史 修改日期：<创建日期，格式：YYYY-MM-DD> 修改人： <姓名/工号> 修改原因/修改内容: <修改原因描述> <问题单DTSXXXXXXXX>
     */
    public static String getPinYin(String str) {
        HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
        // 默认小写
        outputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        // 不显示拼音的声调
        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        // outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

        StringBuilder sb = new StringBuilder();
        try {
            for (char c : str.toCharArray()) {
                // 如果包含有中文标点除号，需要使用正则表达式
                if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
                    // if (c > 128) {
                    sb.append(PinyinHelper.toHanyuPinyinStringArray(c, outputFormat)[0]);
                } else {
                    sb.append(Character.toString(c));
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
        }
        return sb.toString();
    }

    /**
     * 方法名: getPercent 描述：<图片按比例缩放>
     *
     * @param image    图片文件
     * @param w        宽度
     * @param h        高度
     * @param isRotate 是否横版
     * @return Image 图片
     * <p>
     * 创建人: lxb 创建日期: 2018年5月4日
     * <p>
     * 修改历史 修改日期：<创建日期，格式：YYYY-MM-DD> 修改人： <姓名/工号> 修改原因/修改内容: <修改原因描述> <问题单DTSXXXXXXXX>
     */
    public static Image getPercent(Image image, float w, float h, int isRotate) {
        float width = image.getWidth();
        float height = image.getHeight();
        if (isRotate == 1) {
            if (w == 0) {
                w = 580;
            }
            if (h == 0) {
                h = 760;
            }
        } else if (isRotate == 2) {
            if (w == 0) {
                w = 820;
            }
            if (h == 0) {
                h = 510;
            }
        }
        int p = 0;
        float p2 = 0.0f;
        if (isRotate == 1) {
            if (height > width) {
                p2 = h / height * 100;
            } else {
                p2 = w / width * 100;
            }
        } else if (isRotate == 2) {
            if (height > width) {
                p2 = h / height * 100;
            } else {
                p2 = w / width * 100;
            }
        }
        p = Math.round(p2);
        image.setAlignment(Image.MIDDLE);

        // 按百分比显示图片的比例
        image.scalePercent(p);// 表示是原来图像的比例;
        return image;
    }

    public static Map<String, Object> getDateBetween(Date start, Date end) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            long between = end.getTime() - start.getTime();
            long day = between / (24 * 60 * 60 * 1000);
            long hour = (between / (60 * 60 * 1000) - day * 24);
            long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long second = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            long millisecond = (between - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - second * 1000);
            map.put("day", day);
            map.put("hour", hour);
            map.put("min", min);
            map.put("second", second);
            map.put("millisecond", millisecond);
            map.put("allMillisecond", between);
        } catch (Exception e) {
        }

        return map;
    }

    /**
     * @param str
     * @param num
     * @return
     * @Title: getSubStr
     * @Description: 获取根据第几个
     */
    public static String getNumLastIndexStr(String str, String lastIndexStr, int num) {
        String result = "";
        int i = 0;
        while (i < num) {
            int lastFirst = str.lastIndexOf(lastIndexStr);
            result = str.substring(lastFirst) + result;
            str = str.substring(0, lastFirst);
            i++;
        }
        return result;
    }

    /**
     * @param jsContent
     * @return
     * @Title: getJsFileImagPath
     * @Description: 获取JS文件图片路径
     */
    public static List<String> getJsFileImagPath(String jsContent) {
        // 其他图片
        List<String> resultList = new ArrayList<>();
        String[] fistSplits = jsContent.split(":");
        for (String fistStr : fistSplits) {
            String secedStrs[] = fistStr.split("\"");
            for (String secedStr : secedStrs) {
                if (secedStr.contains("/bgimgs/") || secedStr.contains("/imgs/") || secedStr.contains("/swipperimgs/")) {
                    resultList.add(isEndWithSlash(secedStr));
                }
            }
        }
        return resultList;
    }

    /**
     * @param jsContent
     * @return
     * @Title: getModelJsFilePath
     * @Description: 获取模板jsfile内容
     */
    public static List<String> getModelJsFilePath(String jsContent) {
        // 其他图片
        List<String> resultList = new ArrayList<>();
        String[] fistSplits = jsContent.split(":");
        for (String fistStr : fistSplits) {
            String secedStrs[] = fistStr.split("\"");
            for (String secedStr : secedStrs) {
                if (secedStr.contains("/templateBgimgs/") || secedStr.contains("/templateImgs/") || secedStr.contains("/templateSwipperimgs/")) {
                    resultList.add(isEndWithSlash(secedStr));
                }
            }
        }
        return resultList;
    }

    /**
     * @param str
     * @return
     * @Title: isEndWithSlash
     * @Description: 删除最后一个斜杠字符
     */
    public static String isEndWithSlash(String str) {
        if (isNotNull(str)) {
            if (str.endsWith("\\")) {
                str = str.substring(0, str.length() - 1);
                return str;
            }
        }
        return str;
    }

    public static String getSystemStr() {
        String osName = System.getProperty("os.name");
        if (Tools.isNull(osName)) {
            osName = "linux";
        }
        if (osName.toLowerCase().indexOf("windows") != -1) {
            return "windows";
        }
        return "linux";
    }

    public static String objToStringNoTrim(Object obj) {
        if (Tools.isNull(obj)) {
            return null;
        } else {
            return String.valueOf(obj);
        }
    }

    public static long getFreeMemory() {
        Runtime run = Runtime.getRuntime();

        long max = run.maxMemory();

        long total = run.totalMemory();

        long free = run.freeMemory();

        long usable = max - total + free;
        return usable;
    }

}
