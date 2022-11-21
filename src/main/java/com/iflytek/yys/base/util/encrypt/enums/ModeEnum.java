package com.iflytek.yys.base.util.encrypt.enums;

/**
 * Description:
 *
 * @author createdBy huizhang43.
 * @date createdAt 2022/11/17 16:33
 **/
public enum ModeEnum {
    CBC("CBC"),
    ECB("ECB"),
    CTR("CTR"),
    OFB("OFB"),
    CFB("CFB"),
    CTS("CTS"),
    PCBC("PCBC");

    private String value;

    private ModeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}