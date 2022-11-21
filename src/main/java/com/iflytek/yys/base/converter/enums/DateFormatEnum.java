package com.iflytek.yys.base.converter.enums;

/**
 * Description:
 *
 * @author createdBy huizhang43.
 * @date createdAt 2022/11/17 17:20
 **/
public enum DateFormatEnum {
    DATE_FORMAT_DATETIMESTAMP("yyyy-MM-dd HH:mm:ss.SSS"),
    DATE_FORMAT_DATETIME("yyyy-MM-dd HH:mm:ss"),
    DATE_FORMAT_DATE("yyyy-MM-dd"),
    DATE_FORMAT_TIMESTAMP("HH:mm:ss.SSS"),
    DATE_FORMAT_TIME("HH:mm:ss");

    private String value;

    private DateFormatEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
