package com.tencent.athena.core.exception;

import com.tencent.athena.core.ResultCodeInterface;

/**
 * <p>业务异常</p>
 * <p>业务处理时，出现异常，可以抛出该异常</p>
 *
 * @author qinzhongjian
 * @since JDK 1.8.0_212-b10
 */
public class BizRuntimeException extends BaseException {
    /**
     * 构造方法
     *
     * @param resultCodeInterface 错误码
     */
    public BizRuntimeException(ResultCodeInterface resultCodeInterface) {
        super(resultCodeInterface);
    }

    /**
     * 构造方法
     *
     * @param resultCodeInterface 错误码
     * @param message             错误信息
     */
    public BizRuntimeException(ResultCodeInterface resultCodeInterface, String message) {
        super(resultCodeInterface, message);
    }

    /**
     * 构造方法
     *
     * @param resultCodeInterface 错误码
     * @param message             错误信息
     * @param cause               异常
     */
    public BizRuntimeException(ResultCodeInterface resultCodeInterface, String message, Throwable cause) {
        super(resultCodeInterface, message, cause);
    }
}
