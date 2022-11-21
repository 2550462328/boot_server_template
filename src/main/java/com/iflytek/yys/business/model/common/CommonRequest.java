package com.iflytek.yys.business.model.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Description:
 *
 * @author createdBy huizhang43.
 * @date createdAt 2022/5/5 10:45
 **/
@ApiModel(value = "CommonRequest", description = "公共请求入参")
@Data
public class CommonRequest {
    
    /**
     * 医院编码
     */
    @ApiModelProperty("医院编号")
    @NotBlank(message = "医院编号不能为空")
    private String hosCode;
    
    /**
     * 科室编码
     */
    @ApiModelProperty("科室编码")
    @NotBlank(message = "科室编码不能为空")
    private String deptCode;
    
    /**
     * 操作用户工号
     */
    @ApiModelProperty("登录用户工号")
    @NotBlank(message = "操作医生工号不能为空")
    private String docCode;
    
    @ApiModelProperty("登录用户姓名")
    private String docName;
}
