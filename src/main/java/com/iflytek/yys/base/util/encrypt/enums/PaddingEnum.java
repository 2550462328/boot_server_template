package com.iflytek.yys.base.util.encrypt.enums;

/**
 * Description:
 *
 * @author createdBy huizhang43.
 * @date createdAt 2022/11/17 16:34
 **/
public enum PaddingEnum {
    PKCS5PADDING("PKCS5PADDING"),
    PKCS7PADDING("PKCS7PADDING"),
    NOPADDING("NOPADDING"),
    ISO10126PADDING("ISO10126PADDING");

    private String value;

    private PaddingEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}