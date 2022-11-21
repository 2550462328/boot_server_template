package com.iflytek.yys.business.service;

import cn.hutool.core.collection.CollUtil;
import com.iflytek.yys.YysPaasServerTemplateApplicationTests;
import com.iflytek.yys.business.mapper.UserInfoDao;
import com.iflytek.yys.business.model.dto.GroupUserDTO;
import com.iflytek.yys.business.model.req.UserQueryPageRequest;
import com.iflytek.yys.business.model.resp.UserQueryPageResponse;
import com.iflytek.yys.business.response.ResponseModel;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

/**
 * Description:
 * 在写对 Service 进行单元测试时，会将 Mapper 层进行 Mock
 * <p>
 * 核心 @MockBean + Mockito.when 测试 Service层时模拟Mapper层返回的结果
 *
 * @author createdBy huizhang43.
 * @date createdAt 2022/11/20 10:44
 **/
public class UserServiceTest extends YysPaasServerTemplateApplicationTests {

    @MockBean
    private UserInfoDao userInfoDao;

    @Autowired
    private UserService userService;

    @Test
    public void testSelectUserList() {

        List<GroupUserDTO> userInfosPage = Lists.newArrayList();
        GroupUserDTO user1 = new GroupUserDTO();
        user1.setDocName("张辉");
        user1.setDocCode("huizhang43");
        userInfosPage.add(user1);
        // Mockito.when 希望在执行特定方法的时候 执行指定的逻辑 常用于第三方 调用情况模拟
        // 屏蔽Mapper层调用细节
        String hosCode = "1002";
        String deptCode = "244";
        String docName = "";
        // 测试分页列表 的时候 拦截不到，可能传入 的 Page 对象 内存地址不一样导致的，要看下底层代码 MockitoInterceptor
        Mockito.when(userInfoDao.listAllUser(hosCode, deptCode, docName)).thenReturn(userInfosPage);

        // 测试service层
        UserQueryPageRequest pageRequest = new UserQueryPageRequest();
        pageRequest.setHosCode("1002");
        pageRequest.setDeptCode("244");
        pageRequest.setName("");

        ResponseModel<UserQueryPageResponse> response = userService.selectUserList(pageRequest);
        Assert.assertTrue(CollUtil.isNotEmpty(response.getR().getResult()));
    }
}
