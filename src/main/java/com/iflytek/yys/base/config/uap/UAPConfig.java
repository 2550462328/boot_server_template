package com.iflytek.yys.base.config.uap;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.iflytek.yys.base.config.redis.RedisKeyEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RefreshScope
public class UAPConfig implements Serializable {
    
    @Value("${yys.uap.url}")
    private String url;
    
    @Value("${yys.uap.tokenCheck}")
    private String tokenCheck;
    
    @Resource
    private RestTemplate restTemplate;
    
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    
    public boolean tokenCheck(String token) {
        return tokenCheck(token, 1, "");
    }
    
    public boolean tokenCheck(String token, int hosType) {
        return tokenCheck(token, hosType, "");
    }
    
    public boolean tokenCheck(String token, int hosType, String phone) {
        
        String redisKey = RedisKeyEnum.TOKEN_CHECK.getValue() + token;
        
        if (ObjectUtil.isNotNull(stringRedisTemplate.opsForValue().get(redisKey))) {
            return true;
        } else {
            
            Map<String, Object> param = new HashMap<String, Object>() {
                {
                    put("token", token);
                    put("hosType", hosType);
                    put("phone", phone);
                }
            };
            
            log.info("SSO&UAP令牌验证参数：" + JSONUtil.toJsonStr(param));
            
            JSONObject result = restTemplate.postForObject(url + tokenCheck, new HttpEntity<>(param, new LinkedMultiValueMap<String, String>() {
                {
                    add("Accept", "application/json");
                    add("Content-Encoding", "UTF-8");
                    add("Content-Type", "application/json;charset=UTF-8");
                    add("x-appcode", "user-center");
                }
            }), JSONObject.class);
            
            log.info("SSO&UAP令牌验证结果：" + result.toJSONString());
            
            if (!StrUtil.equals(result.getString("s"), "1")) {
                return false;
            } else {
                long expireTime = result.getLongValue("r");
                if (expireTime <= 0L) {
                    return false;
                } else {
                    stringRedisTemplate.opsForValue().set(redisKey, DateUtil.formatDateTime(new Date()), expireTime, TimeUnit.MILLISECONDS);
                    return true;
                }
            }
        }
    }
}