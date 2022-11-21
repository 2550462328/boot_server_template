package com.iflytek.yys.business.model.req;

import com.iflytek.yys.business.model.common.CommonPageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description:
 *
 * @author createdBy huizhang43.
 * @date createdAt 2022/4/29 11:43
 **/
@ApiModel(value = "UserQueryPageRequest", description = "用户分页列表查询请求入参")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserQueryPageRequest extends CommonPageRequest {
    
    @ApiModelProperty("医生姓名（模糊查询）")
    private String name;
}
