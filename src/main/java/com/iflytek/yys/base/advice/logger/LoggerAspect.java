package com.iflytek.yys.base.advice.logger;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.iflytek.yys.base.util.InetUtils;
import com.iflytek.yys.base.util.json.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @description: 访问日志日志切面
 * @author: huizhang43
 * @create: 2021-04-20 09:12:56
 * @blame YYS Team
 */
@Slf4j
@Aspect
@Component
public class LoggerAspect {
    
    /**
     * 日志切面
     */
    @Pointcut("execution(* com.iflytek.doc.*.controller.*Controller.*(..))")
    public void printSysLog() {
    }
    
    @Around("printSysLog()")
    public Object saveSysLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        //获取请求的类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        //获取用户ip地址
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String reqMethod = request.getMethod();
        String queryString = request.getQueryString();
        String requestUri = request.getRequestURI();
        StopWatch sw = new StopWatch(className + "." + methodName);
        //请求的参数
        Object[] args = proceedingJoinPoint.getArgs();
        String params = "";
        if (!StrUtil.containsIgnoreCase(requestUri, "upload") && args.length > 0) {
            if (RequestMethod.POST.name().equals(reqMethod)) {
                params = JacksonUtils.objToStr(args);
            } else if (RequestMethod.GET.name().equals(reqMethod)) {
                params = queryString;
            }
        }
        
        log.info("|---------- Start to invoke method:[{}]-[{}]", request.getRequestURI(), sw.getId());
        log.info("|请求参数:\n{}", params);
        log.info("|请求ip=[{}]", InetUtils.getIpAddress(request));
        sw.start();
        Object result = proceedingJoinPoint.proceed();
        sw.stop();
        log.info("|当前执行方法为：[{}]-共耗时：[{}] ms", sw.getId(), sw.getTotalTimeMillis());
        String data = JSONUtil.toJsonStr(result);
        if (StrUtil.isNotEmpty(data)) {
            //解决批量查询结果记录过多日志打印消耗性能问题
            if (data.length() > 200) {
                log.info("请求响应:\n{}", data.substring(0, 200));
            } else {
                log.info("请求响应:\n{}", data);
            }
        }
        
        // 保存操作日志
        this.saveOpLog(method, args);
        
        log.info("|---------- End to invoke method:[{}]-[{}]", request.getRequestURI(), sw.getId());
        return result;
        
    }
    
    /**
     * 保存操作日志
     *
     * @param method
     *         请求方法
     * @param args
     *         请求参数
     */
    private void saveOpLog(Method method, Object[] args) {
    }
}