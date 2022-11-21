package com.iflytek.yys.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iflytek.yys.business.model.dto.GroupUserDTO;
import com.iflytek.yys.business.model.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInfoDao extends BaseMapper<UserInfo> {

    List<GroupUserDTO> listAllUser(@Param("hosCode") String hosCode, @Param("deptCode") String deptCode, @Param("docName") String docName);

}