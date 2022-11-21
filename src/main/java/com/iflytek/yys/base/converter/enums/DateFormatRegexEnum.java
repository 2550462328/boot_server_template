package com.iflytek.yys.base.converter.enums;

/**
 * Description:
 *
 * @author createdBy huizhang43.
 * @date createdAt 2022/11/17 17:20
 **/
public enum DateFormatRegexEnum {
    DATE_FORMAT_REGEX_DATETIMESTAMP("^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}.\\d{1,3}"),
    DATE_FORMAT_REGEX_DATETIME("^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}"),
    DATE_FORMAT_REGEX_DATE("^\\d{4}-\\d{1,2}-\\d{1,2}"),
    DATE_FORMAT_REGEX_TIMESTAMP("^\\d{1,2}:\\d{1,2}:\\d{1,2}.\\d{1,3}"),
    DATE_FORMAT_REGEX_TIME("^\\d{1,2}:\\d{1,2}:\\d{1,2}");

    private String value;

    private DateFormatRegexEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

