package com.iflytek.yys.business.combine;

import cn.hutool.json.JSONUtil;
import com.iflytek.yys.YysPaasServerTemplateApplicationTests;
import com.iflytek.yys.business.model.req.UserQueryPageRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Description:
 * 用户业务的 集成测试
 *
 * @author createdBy huizhang43.
 * @date createdAt 2022/11/21 15:35
 **/
@Rollback
@AutoConfigureMockMvc(addFilters = false)
public class UserBusinessCombineTest extends YysPaasServerTemplateApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    /**
     * 准备前置资源
     */
    @Before
    public void setUp() {

    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, statements = {"insert into ud_doctor(org_id,org_name,dpt_code,user_name,rel_name,user_state) values('1002','新疆自治区人民医院','244','yyzhang','张言一','A')"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, statements = {"delete from ud_doctor where org_id = '1002' and user_name = 'yyzhang'"})
    public void testSelectUserList() throws Exception {

        UserQueryPageRequest userQueryPageRequest = new UserQueryPageRequest();
        userQueryPageRequest.setHosCode("1002");
        userQueryPageRequest.setDeptCode("244");
        userQueryPageRequest.setName("言一");
        userQueryPageRequest.setPageNum(0);
        userQueryPageRequest.setPageSize(10);
        userQueryPageRequest.setDocCode("xzwu6");

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/user/list/page")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONUtil.toJsonStr(userQueryPageRequest))
                .accept(MediaType.APPLICATION_JSON);

        // 测试controller层
        this.mockMvc.perform(mockHttpServletRequestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.s").value("1"))
                .andExpect(jsonPath("$.r.result").isNotEmpty())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }
}
