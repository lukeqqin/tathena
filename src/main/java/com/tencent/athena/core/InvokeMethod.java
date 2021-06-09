package com.tencent.athena.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;

/**
 * 方法调用内部包装
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvokeMethod {
    private Object proxy;
    private Object target;
    private Method method;
    private Object[] args;
    private Object result;
    @Deprecated
    private Throwable throwable;
}
