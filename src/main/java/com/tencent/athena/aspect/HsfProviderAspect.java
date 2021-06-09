package com.tencent.athena.aspect;

import com.tencent.athena.Unify;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author qinzhongjian
 * @since JDK 1.8.0_212-b10
 */
@Aspect
@Component
public class HsfProviderAspect {

    /**
     * 切点
     */
    @Pointcut("@annotation(com.tencent.athena.annotation.LogAdvice) || @within(com.tencent.athena.annotation.LogAdvice)")
    public void pointcut() {
    }

    /**
     * 织入
     *
     * @param pjp 切点
     * @return object
     * @throws Throwable 异常
     */
    @Around("pointcut()")
    public Object pointcutAround(ProceedingJoinPoint pjp) throws Throwable {
        return Unify.process(pjp);
    }
}
