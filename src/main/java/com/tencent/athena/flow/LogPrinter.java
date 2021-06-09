package com.tencent.athena.flow;

import com.alibaba.fastjson.JSON;
import com.tencent.athena.core.InvokeMethod;
import com.tencent.athena.annotation.LogAdvice;
import com.tencent.athena.annotation.LogForParams;
import com.tencent.athena.annotation.LogForResult;
import com.tencent.athena.utils.DefaultParameterProvider;
import com.tencent.athena.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ParameterNameProvider;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 打印入参出参日志
 *
 * @author qinzhongjian
 * @since JDK 1.8.0_212-b10
 */
@Slf4j
public class LogPrinter {

    private static ParameterNameProvider discoverer = new DefaultParameterProvider();

    private static boolean checkLogConfigForInputParams(Method method) {
        LogForParams logForParams = AnnotationUtils.getAnnotation(method, LogForParams.class);
        if (logForParams != null && logForParams.logForParams()) {
            return true;
        }
        LogAdvice logAdvice = AnnotationUtils.getAnnotation(method, LogAdvice.class);
        if (logAdvice != null && logAdvice.logForParams()) {
            return true;
        }
        logForParams = AnnotationUtils.getAnnotation(method.getDeclaringClass(), LogForParams.class);
        if (logForParams != null && logForParams.logForParams()) {
            return true;
        }
        logAdvice = AnnotationUtils.getAnnotation(method.getDeclaringClass(), LogAdvice.class);
        if (logAdvice != null && logAdvice.logForParams()) {
            return true;
        }
        return false;
    }

    private static boolean checkLogConfigForResult(Method method) {
        LogForResult logForResult = AnnotationUtils.getAnnotation(method, LogForResult.class);
        if (logForResult != null && logForResult.logForResult()) {
            return true;
        }
        LogAdvice logAdvice = AnnotationUtils.getAnnotation(method, LogAdvice.class);
        if (logAdvice != null && logAdvice.logForResult()) {
            return true;
        }
        logForResult = AnnotationUtils.getAnnotation(method.getDeclaringClass(), LogForResult.class);
        if (logForResult != null && logForResult.logForResult()) {
            return true;
        }
        logAdvice = AnnotationUtils.getAnnotation(method.getDeclaringClass(), LogAdvice.class);
        if (logAdvice != null && logAdvice.logForResult()) {
            return true;
        }
        return false;
    }

    /**
     * 打印入参
     *
     * @param invokeMethod 调用信息
     */
    public static void printLog4InputParams(InvokeMethod invokeMethod) {
        Method classMethod = invokeMethod.getMethod();
        boolean needPrintLog = checkLogConfigForInputParams(classMethod);
        if (!needPrintLog) {
            return;
        }
        Map<String, Object> objectMap = getInnerInputParams(invokeMethod);
        String methodFullName = ReflectionUtils.getMethodFullName(classMethod);
        log.info(methodFullName + " printLog4InputParams: there are parameters = " + JSON.toJSONString(objectMap));
    }

    /**
     * 打印出参
     *
     * @param invokeMethod 调用信息
     */
    public static void printLog4ReturnValues(InvokeMethod invokeMethod) {
        Method classMethod = invokeMethod.getMethod();
        boolean needPrintLog = LogPrinter.checkLogConfigForResult(classMethod);
        if (!needPrintLog) {
            return;
        }
        String methodFullName = ReflectionUtils.getMethodFullName(classMethod);
        log.info(methodFullName + " printLog4ReturnValues: there is result = {}", JSON.toJSONString(invokeMethod.getResult()));

    }

    /**
     * 打印异常时候的如参
     *
     * @param invokeMethod 调用信息
     */
    public static void printLog4Exceptions(InvokeMethod invokeMethod) {
        Method classMethod = invokeMethod.getMethod();
        Map<String, Object> objectMap = getInnerInputParams(invokeMethod);
        String methodFullName = ReflectionUtils.getMethodFullName(classMethod);
        log.info(methodFullName + " printLog4Exceptions: there are parameters = " + JSON.toJSONString(objectMap));
    }

    /**
     * 获取入参
     *
     * @param invokeMethod 调用信息
     * @return 参数
     */
    public static Map<String, Object> getInnerInputParams(InvokeMethod invokeMethod) {
        Method classMethod = invokeMethod.getMethod();
        List<String> parameterNames = discoverer.getParameterNames(classMethod);
        Object[] args = invokeMethod.getArgs();
        Map<String, Object> objectMap = new HashMap<>(args.length);
        for (int i = 0; i < args.length; i++) {
            String parameterName = parameterNames.get(i);
            if (null == args[i] || ReflectionUtils.isPrimitive(args[i])) {
                objectMap.put(parameterName, args[i]);
            } else if (args[i] instanceof HttpServletRequest || args[i] instanceof HttpServletResponse) {

            } else {
                objectMap.put(parameterName, JSON.toJSONString(args[i]));
            }
        }
        return objectMap;
    }
}
