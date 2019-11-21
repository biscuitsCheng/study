package com.biscuits.doublecache.cache;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author biscuits
 * @date 2019/10/1
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface CacheHelper {

    String key();

    TimeUnit timeUnit() default TimeUnit.DAYS;

    long timeOut() default 1L;
}
