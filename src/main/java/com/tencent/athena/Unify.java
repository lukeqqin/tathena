package com.tencent.athena;

import com.tencent.athena.config.LogConfig;
import com.tencent.athena.core.InvokeMethod;
import com.tencent.athena.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 1. 扩展机制
 * 2. 日志打印格式确定
 *
 * @author qinzhongjian
 * @since JDK 1.8.0_212-b10
 */
@Slf4j
public class Unify {

    /**
     * 统一异常处理入口
     *
     * @param pjp 切点
     * @return 执行结果，有可能是异常结果
     * @throws Throwable 不能处理的异常
     */
    public static Object process(ProceedingJoinPoint pjp) throws Throwable {
        InvokeMethod invokeMethod = ReflectionUtils.getInvokeMethod(pjp);
        Object result;
        try {
            LogConfig.log4InputParams.accept(invokeMethod);
            result = pjp.proceed();
            invokeMethod.setResult(result);
            LogConfig.log4ReturnValues.accept(invokeMethod);
        } catch (Throwable throwable) {
            //不建议在内部打印堆栈异常 应该统一在全局异常处理地方进行打印
            //invokeMethod.setThrowable(throwable);
            LogConfig.log4Exceptions.accept(invokeMethod);
            throw throwable;
        }
        return result;

    }
}