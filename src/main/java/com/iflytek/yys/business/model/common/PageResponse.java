package com.iflytek.yys.business.model.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description:
 *
 * @author createdBy huizhang43.
 * @date createdAt 2022/4/28 20:15
 **/
@ApiModel(value = "PageResponse", description = "分页查询返回公共出参")
@Data
public class PageResponse {
    
    @ApiModelProperty("总数")
    private long total;

    @ApiModelProperty("总页数")
    private long totalPages;
}
