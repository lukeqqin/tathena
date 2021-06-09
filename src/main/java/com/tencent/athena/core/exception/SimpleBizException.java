package com.tencent.athena.core.exception;

import com.tencent.athena.core.ResultCodeInterface;

/**
 * 简单业务异常，无堆栈信息
 * 另，JDK默认对异常有优化，参见OmitStackTraceInFastThrow
 *
 * @author qinzhongjian
 * @since JDK 1.8.0_212-b10
 */
public class SimpleBizException extends BizRuntimeException {
    /**
     * 构造方法
     *
     * @param error 错误码
     */
    public SimpleBizException(ResultCodeInterface error) {
        super(error);
    }

    /**
     * 构造方法
     *
     * @param error   错误码
     * @param message 错误信息
     */
    public SimpleBizException(ResultCodeInterface error, String message) {
        super(error, message);
    }

    /**
     * 构造方法
     *
     * @param resultCodeInterface 错误码
     * @param message             错误信息
     * @param cause               异常
     */
    public SimpleBizException(ResultCodeInterface resultCodeInterface, String message, Throwable cause) {
        super(resultCodeInterface, message, cause);
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
