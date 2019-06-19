package com.cqut.wangyu.crm.utils;

/**
 * @ClassName SQLUtil
 * @Description 多条件查询语句工具类
 * @Author ChongqingWangYu
 * @DateTime 2019/6/19 14:31
 * @GitHub https://github.com/ChongqingWangYu
 */
public class SQLUtil {
    private static final String like = " like ";
    private static final String and = " and ";

    public static String assembleSql(String[] columnsName, String[] columnsValue) {
        String sql = "";
        if (null == columnsName || null == columnsValue || columnsName.length != columnsValue.length || columnsValue.length == 0 || columnsValue.length == 0) {
            return " like '%'";
        }
        for (int i = 0; i < columnsName.length; i++) {
            String name = columnsName[i];
            String value = columnsValue[i];
            if (name == null || value == null || name == "" || value == "") {
                continue;
            }
            if (i > 0) {
                sql += and;
            }
            sql += name + like + strValue(value);
        }
        return sql;
    }

    public static String strValue(String value) {
        return "'" + value + "'";
    }
}
