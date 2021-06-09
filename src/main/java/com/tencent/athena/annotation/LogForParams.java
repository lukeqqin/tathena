package com.tencent.athena.annotation;

import java.lang.annotation.*;

/**
 * 入参参数打印
 *
 * @author qinzhongjian
 * @since JDK 1.8.0_212-b10
 */

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogForParams {
    /**
     * 入参参数打印
     *
     * @return false
     */
    boolean logForParams() default false;
}
