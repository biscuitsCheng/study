package com.biscuits.doublecache.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.GuavaCodec;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author biscuits
 * @date 2019/10/1
 */
@Aspect
@Component
@Slf4j
public class CacheHandel implements InitializingBean {
    private RedisTemplate<String, String> redisTemplate;
    private Cache<String, String> localCache;


    @Pointcut("@annotation(CacheHelper)")
    public void cacheHandel() {
    }

    @Around("cacheHandel()")
    public Object addCacheHandel(ProceedingJoinPoint point) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        Class<?> clz = method.getReturnType();
        CacheHelper annotation = method.getAnnotation(CacheHelper.class);
        long timeOut = annotation.timeOut();
        TimeUnit timeUnit = annotation.timeUnit();
        String key = annotation.key();
        String trueKey = createCacheKey(point, key);
        String localCacheVal = localCache.getIfPresent(trueKey);
        if (!StringUtils.isEmpty(localCacheVal)) {
            log.info("缓存本地缓存命中");
            return JSON.parseObject(localCacheVal, clz);
        }
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String value = ops.get(trueKey);
        if (!StringUtils.isEmpty(value)) {
            log.info("缓存命中&新增本地缓存");
            localCache.put(trueKey, value);
            return JSON.parseObject(value, clz);
        }
        Object result = point.proceed();
        if (result == null) {
            log.info("防止缓存雪崩");
            ops.setIfAbsent(trueKey, "", 1, TimeUnit.MINUTES);
            return null;
        }

        log.info("新增缓存");
        String jsonResult = JSON.toJSONString(result);
        localCache.put(trueKey, jsonResult);
        ops.setIfAbsent(trueKey, jsonResult, timeOut, timeUnit);
        return result;
    }

    @AfterReturning(pointcut = "@annotation(CacheHelper)", returning = "result")
    public void saveCache(JoinPoint point, Object result) {

    }

    private String createCacheKey(JoinPoint point, String key) {
        StringBuilder sb = new StringBuilder(key);
        for (Object arg : point.getArgs()) {
            sb.append(":").append(arg);
        }
        return sb.toString();
    }

    @Autowired
    private void setRedisTemplate(RedisTemplate<String, String> template) {
        this.redisTemplate = template;
    }

    @Override
    public void afterPropertiesSet() {
        localCache = CacheBuilder.newBuilder().maximumSize(1000).expireAfterWrite(1,
                TimeUnit.HOURS).build();
        localCache.cleanUp();
    }
}
