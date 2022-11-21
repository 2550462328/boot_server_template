package com.iflytek.yys.business.model.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Description:
 *
 * @author createdBy huizhang43.
 * @date createdAt 2022/4/28 19:57
 **/
@ApiModel(value = "PageDTO", description = "分页请求入参")
@Data
public class PageDTO {
    
    @ApiModelProperty("每页显示条数")
    @NotNull(message = "分页参数不能为空")
    private Integer pageSize;
    
    @ApiModelProperty("页码")
    @NotNull(message = "分页参数不能为空")
    private Integer pageNum;
}
