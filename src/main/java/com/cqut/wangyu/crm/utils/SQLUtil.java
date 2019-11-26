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
    private static final String likeAll = "1=1";

    public static String assembleSql(String[] columnsName, String[] columnsValue) {
        if (null == columnsName || null == columnsValue || columnsName.length != columnsValue.length || columnsValue.length == 0 || columnsValue.length == 0) {
            return likeAll;
        }
        String sql = "";
        int count = 0;
        for (int i = 0; i < columnsName.length; i++) {
            String columnName = columnsName[i];
            String value = columnsValue[i];
            if (columnName == null || value == null || columnName == "" || value == "") {
                continue;
            }
            if (count > 0) {
                sql += and;
            }
            if(columnName.endsWith("ID")){
                sql += columnName + like + strValue(value);
            }else {
                sql += columnName + like + strDimValue(value);
            }
            count++;
        }
        if (count == 0) {
            return likeAll;
        }
        return sql;
    }

    public static String strDimValue(String value) {
        return "'%" + value + "%'";
    }

    public static String strValue(String value) {
        return "'" + value + "'";
    }
}
