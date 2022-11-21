package com.iflytek.yys.base.filter;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iflytek.yys.base.config.uap.UAPConfig;
import com.iflytek.yys.base.exception.enums.ArgumentResponseEnum;
import com.iflytek.yys.base.exception.enums.SystemResponseEnum;
import com.iflytek.yys.base.util.encrypt.enums.CharsetNameEnum;
import com.iflytek.yys.business.mapper.UserInfoDao;
import com.iflytek.yys.business.model.entity.UserInfo;
import com.iflytek.yys.business.response.ResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 权限校验
 */
@Slf4j
@RefreshScope
@WebFilter(filterName = "tokenValidateFilter", urlPatterns = "/*")
public class TokenValidateFilter implements Filter {

    @Value("${yys.openCheck}")
    private boolean openCheck;

    @Resource
    private UserInfoDao userInfoDao;

    @Resource
    private UAPConfig uapConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        // get请求不拦截
        if (!openCheck || unNecessaryFilter(httpServletRequest)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        String docId = httpServletRequest.getHeader("docId");
        String token = httpServletRequest.getHeader("token");

        String hosCode = docId.split("-")[0];
        docId = docId.split("-")[1];

        QueryWrapper<UserInfo> userInfoQueryWrapper = new QueryWrapper<>();
        userInfoQueryWrapper.lambda().eq(UserInfo::getHosCode, hosCode).eq(UserInfo::getDocCode, docId);
        UserInfo userInfo = userInfoDao.selectOne(userInfoQueryWrapper);
        if (ObjectUtil.isNull(userInfo)) {
            printResponse(httpServletResponse,ResponseModel.failure(ArgumentResponseEnum.VALID_ERROR.getCode(),docId + "不存在该医生"));
            return;
        }

        boolean tokenCheck = false;
        try {
            tokenCheck = uapConfig.tokenCheck(token, 1, userInfo.getUserPhone());
        } catch (Exception e) {
            log.error("invalid token：{} :{}: {}", hosCode, docId, token);
        }
        if (!tokenCheck) {
            printResponse(httpServletResponse,ResponseModel.failure(SystemResponseEnum.SESSION_TIME_OUT.getCode(),"账号已过期，请重新登录"));
            return;
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    public void destroy() {

    }

    /**
     * 是否拦截
     *
     * @param request
     * @return
     */
    private boolean unNecessaryFilter(HttpServletRequest request) {
        return StrUtil.equalsIgnoreCase("GET", request.getMethod());
    }

    /**
     * 直接 输出返回报文
     *
     * @param httpServletResponse
     * @param responseModel
     */
    private void printResponse(HttpServletResponse httpServletResponse, ResponseModel<Object> responseModel) {
        PrintWriter printWriter = null;

        try {
            httpServletResponse.setContentType("application/json");
            httpServletResponse.setCharacterEncoding(CharsetNameEnum.UTF_8.getValue());
            printWriter = httpServletResponse.getWriter();
            printWriter.print(JSONUtil.toJsonStr(responseModel));
        } catch (Exception e) {
            throw new RuntimeException("Print Data Cause Exception.", e);
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
}