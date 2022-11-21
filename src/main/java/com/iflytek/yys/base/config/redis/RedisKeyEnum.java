package com.iflytek.yys.base.config.redis;

public enum RedisKeyEnum {
    
    // **************************************************缓存键名开始**************************************************//
    
    YYS_TOKEN("yys_token"),
    
    TOKEN_CHECK("token_check:"),
    
    SYNC_USER_LAST_UPDATE_TIME("schedule_sync_user_last_time:"),

    SYNC_PATIENT_LAST_UPDATE_TIME("schedule_sync_patient_last_time:"),

    SYNC_USER_BED_LAST_UPDATE_TIME("schedule_sync_user_bed_last_time:"),

    EXCHANGE_ATOMIC_INDEX_KEY("exchange_atomic_index_key"),

    SHIFT_LABEL_TEMPLATE_KEY("shift_label_template_key:");
    // **************************************************缓存键名结束**************************************************//
    
    // 键名
    private String value;
    
    RedisKeyEnum(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
}