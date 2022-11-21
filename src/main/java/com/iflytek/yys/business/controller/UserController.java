package com.iflytek.yys.business.controller;

import com.iflytek.yys.business.model.req.UserQueryPageRequest;
import com.iflytek.yys.business.model.resp.UserQueryPageResponse;
import com.iflytek.yys.business.response.ResponseModel;
import com.iflytek.yys.business.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Description:
 *
 * @author createdBy huizhang43.
 * @date createdAt 2022/4/28 8:45
 **/
@Api(value = "UserController", tags = "医生用户配置相关接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation("用户分页列表查询")
    @PostMapping("/list/page")
    public ResponseModel<UserQueryPageResponse> selectUserList(@RequestBody @Valid UserQueryPageRequest userQueryPageRequestDTO) {
        return userService.selectUserList(userQueryPageRequestDTO);
    }
}
