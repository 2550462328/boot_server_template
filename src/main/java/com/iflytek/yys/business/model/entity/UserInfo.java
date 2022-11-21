package com.iflytek.yys.business.model.entity;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * user_info
 *
 * @author
 */
@Data
@TableName("ud_doctor")
@KeySequence("seq_ud_doctor_id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 医院编码
     */
    @TableField("org_id")
    private String hosCode;
    
    /**
     * 医院名称
     */
    @TableField("org_name")
    private String hosName;
    
    /**
     * 医生工号
     */
    @TableField("user_name")
    private String docCode;
    
    /**
     * 医生姓名
     */
    @TableField("rel_name")
    private String docName;
    
    /**
     * 启用状态 0 启用 1 禁用
     */
    @TableField(exist = false)
    private Integer enable;
    
    /**
     * 手机号
     */
    private String userPhone;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 修改时间
     */
    private Date updateTime;
    
    private String userState;
}