package com.iflytek.yys.business.service;

import com.iflytek.yys.business.model.req.UserQueryPageRequest;
import com.iflytek.yys.business.model.resp.UserQueryPageResponse;
import com.iflytek.yys.business.response.ResponseModel;

/**
 * Description:
 *
 * @author createdBy huizhang43.
 * @date createdAt 2022/4/28 8:46
 **/
public interface UserService {

    ResponseModel<UserQueryPageResponse> selectUserList(UserQueryPageRequest userQueryPageRequestDTO);
}
