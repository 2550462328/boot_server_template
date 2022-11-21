package com.iflytek.yys.base.config.mybatisplus;

import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 数据库字段自动填充处理
 *
 * @author huizhang43
 */
@Data
@Component("mybatisPlusAutoFillProperties")
@ConfigurationProperties(prefix = "database.field.auto-fill")
public class MybatisPlusAutoFillProperties {
    
    /**
     * 是否开启自动填充字段
     */
    private Boolean enabled = true;
    
    /**
     * 是否开启了插入填充
     */
    private Boolean enableInsertFill = true;
    
    /**
     * 是否开启了更新填充
     */
    private Boolean enableUpdateFill = true;
    
    /**
     * 创建时间字段名
     */
    private String createTimeField = "createTime";
    
    /**
     * 更新时间字段名
     */
    private String updateTimeField = "updateTime";
    
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
