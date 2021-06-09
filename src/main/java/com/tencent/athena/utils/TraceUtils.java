package com.tencent.athena.utils;

import com.tencent.athena.config.LogConfig;
import org.slf4j.MDC;

/**
 * trace id 获取工具
 *
 * @author qinzhongjian
 * @since JDK 1.8.0_212-b10
 */
public abstract class TraceUtils {
    /**
     * 获取 traceId
     *
     * @return traceId
     */
    public static String getTraceId() {
        return MDC.get(LogConfig.TRACE_KEY);
    }
}
