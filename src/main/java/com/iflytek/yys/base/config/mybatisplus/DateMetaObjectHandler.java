package com.iflytek.yys.base.config.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * 数据库字段自动填充处理
 *
 * @author huizhang43
 */
@Slf4j
public class DateMetaObjectHandler implements MetaObjectHandler {
    
    private final MybatisPlusAutoFillProperties autoFillProperties;
    
    public DateMetaObjectHandler(MybatisPlusAutoFillProperties autoFillProperties) {
        this.autoFillProperties = autoFillProperties;
    }
    
    /**
     * 是否开启了插入填充
     */
    @Override
    public boolean openInsertFill() {
        return autoFillProperties.getEnableInsertFill();
    }
    
    /**
     * 是否开启了更新填充
     */
    @Override
    public boolean openUpdateFill() {
        return autoFillProperties.getEnableUpdateFill();
    }
    
    /**
     * 插入填充，字段为空自动填充
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insertFill-----------------");
        Date localDateTime = new Date();
        // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, autoFillProperties.getCreateTimeField(), Date.class, localDateTime);
        this.strictInsertFill(metaObject, autoFillProperties.getUpdateTimeField(), Date.class, localDateTime);
        
    }
    
    /**
     * 更新填充
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start updateFill-----------------");
        Date localDateTime = new Date();
        this.strictUpdateFill(metaObject, autoFillProperties.getUpdateTimeField(), Date.class, localDateTime);
    }
}