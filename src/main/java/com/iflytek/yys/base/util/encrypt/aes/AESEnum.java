package com.iflytek.yys.base.util.encrypt.aes;

/**
 * Description:
 *
 * @author createdBy huizhang43.
 * @date createdAt 2022/11/17 16:34
 **/
public enum AESEnum {
    AES("AES"),
    AES_128("AES-128"),
    AES_192("AES-192"),
    AES_256("AES-256");

    private String value;

    private AESEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}