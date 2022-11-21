package com.iflytek.yys.business.controller;

import cn.hutool.json.JSONUtil;
import com.iflytek.yys.YysPaasServerTemplateApplicationTests;
import com.iflytek.yys.business.model.dto.GroupUserDTO;
import com.iflytek.yys.business.model.req.UserQueryPageRequest;
import com.iflytek.yys.business.model.resp.UserQueryPageResponse;
import com.iflytek.yys.business.response.ResponseModel;
import com.iflytek.yys.business.service.UserService;
import org.assertj.core.util.Lists;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Description:
 * 在写对 Controller 进行单元测试时，会将 Service 层进行 Mock
 * <p>
 * 核心 @MockBean + Mockito.when 测试 Controller层时 模拟 Service层返回的结果
 *
 * @author createdBy huizhang43.
 * @date createdAt 2022/11/18 17:31
 **/
@RunWith(SpringRunner.class)
// 测试controller层可以不需要加载整个spring环境
@WebMvcTest(value = {UserController.class})
@ActiveProfiles("dev")
public class UserControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testSelectUserList() throws Exception{
        // Mockito.when 希望在执行特定方法的时候 执行指定的逻辑 常用于第三方 调用情况模拟
        // 屏蔽service层调用细节
        UserQueryPageRequest pageRequest = new UserQueryPageRequest();
        pageRequest.setHosCode("1002");
        pageRequest.setDeptCode("244");
        pageRequest.setName("");
        pageRequest.setPageNum(0);
        pageRequest.setPageSize(10);
        pageRequest.setDocCode("huizhang43");

        ResponseModel<UserQueryPageResponse> responseResponseModel = ResponseModel.success();
        UserQueryPageResponse pageResponse = new UserQueryPageResponse();
        pageResponse.setResult(Lists.newArrayList());
        responseResponseModel.setR(pageResponse);

        Mockito.when(userService.selectUserList(pageRequest)).thenReturn(responseResponseModel);

        UserQueryPageRequest userQueryPageRequest = new UserQueryPageRequest();
        userQueryPageRequest.setHosCode("1002");
        userQueryPageRequest.setDeptCode("244");
        userQueryPageRequest.setName("");
        userQueryPageRequest.setPageNum(0);
        userQueryPageRequest.setPageSize(10);
        userQueryPageRequest.setDocCode("huizhang43");

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/user/list/page")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONUtil.toJsonStr(userQueryPageRequest))
                .accept(MediaType.APPLICATION_JSON);

        // 测试controller层
        // 校验结果有两种思路 一种基于MockMvcResultMatcher对结果 进行判断
        // 另一种 是 获取 MvcResult 然后 转换成对象，对响应报文进行判断
        this.mockMvc.perform(mockHttpServletRequestBuilder)
                .andDo(print())
                //断言返回值的第二个对象的`id`是否为`101`
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.s").value("1"))
                .andExpect(jsonPath("$.r.result").value(Matchers.hasSize(0)))
                .andReturn()
                .getResponse()
                .getContentAsString();
    }
}
