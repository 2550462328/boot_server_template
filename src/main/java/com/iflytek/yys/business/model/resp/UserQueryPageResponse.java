package com.iflytek.yys.business.model.resp;

import com.iflytek.yys.business.model.common.PageResponse;
import com.iflytek.yys.business.model.dto.GroupUserDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Description:
 *
 * @author createdBy huizhang43.
 * @date createdAt 2022/4/29 11:43
 **/
@ApiModel(value = "UserQueryPageResponse", description = "用户列表查询返回参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserQueryPageResponse extends PageResponse {
    
    private List<GroupUserDTO> result;
}
