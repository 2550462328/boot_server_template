package com.iflytek.yys.business.service.impl;

import com.iflytek.yys.base.exception.enums.SystemResponseEnum;
import com.iflytek.yys.business.mapper.UserInfoDao;
import com.iflytek.yys.business.model.dto.GroupUserDTO;
import com.iflytek.yys.business.model.req.UserQueryPageRequest;
import com.iflytek.yys.business.model.resp.UserQueryPageResponse;
import com.iflytek.yys.business.response.ResponseModel;
import com.iflytek.yys.business.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 *
 * @author createdBy huizhang43.
 * @date createdAt 2022/4/29 11:39
 **/
@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserInfoDao userInfoDao;

    @Override
    @SuppressWarnings("unchecked")
    public ResponseModel<UserQueryPageResponse> selectUserList(UserQueryPageRequest userQueryPageRequestDTO) {

        if(userQueryPageRequestDTO.getDocCode().equals("huizhang43")){
            SystemResponseEnum.SERVER_ERROR.assertFail();
        }
        List<GroupUserDTO> userInfosPage = userInfoDao.listAllUser(userQueryPageRequestDTO.getHosCode(), userQueryPageRequestDTO.getDeptCode(), userQueryPageRequestDTO.getName());

        UserQueryPageResponse queryPageResponse = new UserQueryPageResponse();
        queryPageResponse.setResult(userInfosPage);
        return ResponseModel.success(queryPageResponse);
    }
}
