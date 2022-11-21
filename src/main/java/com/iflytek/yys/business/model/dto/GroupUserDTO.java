package com.iflytek.yys.business.model.dto;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description:
 *
 * @author createdBy huizhang43.
 * @date createdAt 2022/4/28 16:59
 **/
@ApiModel(value = "GroupUserDTO", description = "用户信息")
@Data
public class GroupUserDTO {
    
    @ApiModelProperty("医院编码")
    private String hosCode;
    
    @ApiModelProperty("科室编码")
    private String deptName;
    
    @ApiModelProperty("医生工号")
    private String docCode;
    
    @ApiModelProperty("医生姓名")
    private String docName;

    @ApiModelProperty(hidden = true)
    private transient String userState;

    @ApiModelProperty("启用状态 1 启用 0 禁用")
    private Integer enable;

    public Integer getEnable() {
        if(StrUtil.equals("A",userState)){
            return 1;
        }
        return 0;
    }
}
