package com.iflytek.yys.base.util.encrypt.enums;

/**
 * Description:
 *
 * @author createdBy huizhang43.
 * @date createdAt 2022/11/17 16:33
 **/
public enum CharsetNameEnum {
    UTF_8("utf-8"),
    GBK("GBK"),
    GB2312("gb2312"),
    GB18030("gb18030"),
    BIG5("Big5"),
    ISO_8859_1("ISO-8859-1");

    private String value;

    private CharsetNameEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}