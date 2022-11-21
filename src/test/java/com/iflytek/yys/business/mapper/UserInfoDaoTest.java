package com.iflytek.yys.business.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iflytek.yys.YysPaasServerTemplateApplicationTests;
import com.iflytek.yys.business.model.dto.GroupUserDTO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 *
 * @author createdBy huizhang43.
 * @date createdAt 2022/11/18 17:35
 **/
@Rollback
public class UserInfoDaoTest extends YysPaasServerTemplateApplicationTests {

    @Resource
    private UserInfoDao userInfoDao;

    @Test
//    @Sql(statements = "delete from user_test")
    public void testListAllUser() {
        Page<GroupUserDTO> pageParam = new Page<>(0, 10);
        String hosCode = "1002";
        String deptCode = "244";
        String docName = "";
        List<GroupUserDTO> pageResult = userInfoDao.listAllUser(hosCode, deptCode, docName);

        Assert.assertNotNull(pageResult);
        Assert.assertTrue(pageResult.size() > 0);
    }
}
