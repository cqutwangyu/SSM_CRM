package com.cqut.wangyu.crm.dto;

import com.cqut.wangyu.crm.utils.SQLUtil;

/**
 * @ClassName PageQueryDTO
 * @Description 查询请求
 * @Author ChongqingWangYu
 * @DateTime 2019/6/19 14:26
 * @GitHub https://github.com/ChongqingWangYu
 */
public class PageQueryDTO {
    Integer page;
    Integer limit;
    String[] columnsName;
    String[] columnsValue;
    String sql = "1=1";

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String[] getColumnsName() {
        return columnsName;
    }

    public void setColumnsName(String[] columnsName) {
        this.columnsName = columnsName;
    }

    public String[] getColumnsValue() {
        return columnsValue;
    }

    public void setColumnsValue(String[] columnsValue) {
        this.columnsValue = columnsValue;
    }

    public String getSql() {
        assembleSql();
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public void assembleSql() {
        if (columnsName == null || columnsValue == null || columnsName.length != columnsValue.length) {
            return;
        }
        this.sql = SQLUtil.assembleSql(columnsName, columnsValue);
    }
}
