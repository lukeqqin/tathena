package com.tencent.athena.config;


import com.tencent.athena.core.InvokeMethod;
import com.tencent.athena.flow.LogPrinter;
import com.tencent.athena.utils.TraceUtils;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 日志配置
 *
 * @author qinzhongjian
 * @since JDK 1.8.0_212-b10
 */
public class LogConfig {
    /**
     * 获取 traceId
     */
    public static Supplier<String> traceUtil = TraceUtils::getTraceId;
    /**
     * traceId 标识
     */
    public static String TRACE_KEY = "traceId";
    /**
     * 打印参数
     */
    public static Consumer<InvokeMethod> log4InputParams = LogPrinter::printLog4InputParams;
    /**
     * 打印返回值
     */
    public static Consumer<InvokeMethod> log4ReturnValues = LogPrinter::printLog4ReturnValues;
    /**
     * 打印异常
     */
    public static Consumer<InvokeMethod> log4Exceptions = LogPrinter::printLog4Exceptions;

}
