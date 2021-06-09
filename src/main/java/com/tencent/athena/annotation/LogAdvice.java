package com.tencent.athena.annotation;

import com.tencent.athena.core.ResultVO;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * API的GlobalExceptionHandler标记
 *
 * @author qinzhongjian
 * @since JDK 1.8.0_212-b10
 */

@Documented
@Inherited
@LogForParams
@LogForResult
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAdvice {
    /**
     * 默认返回封装对象
     *
     * @return ResultVO
     */
    Class<?> value() default ResultVO.class;

    /**
     * 打印如参
     *
     * @return false
     */
    @AliasFor(annotation = LogForParams.class)
    boolean logForParams() default false;

    /**
     * 打印出参
     *
     * @return false
     */
    @AliasFor(annotation = LogForResult.class)
    boolean logForResult() default false;
}
