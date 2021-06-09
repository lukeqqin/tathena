package com.tencent.athena.core.exception;


import com.tencent.athena.core.ResultCodeInterface;

/**
 * 基础异常类
 *
 * @author qinzhongjian
 * @since azul-1.8
 */
public class BaseException extends RuntimeException {
    /**
     * 错误码
     */
    private ResultCodeInterface resultCodeInterface;

    /**
     * 构造方法
     *
     * @param resultCodeInterface 错误码
     */
    public BaseException(ResultCodeInterface resultCodeInterface) {
        super(resultCodeInterface.getMessage());
        this.resultCodeInterface = resultCodeInterface;
    }

    /**
     * 构造方法
     *
     * @param resultCodeInterface 错误码
     * @param message             错误信息
     */
    public BaseException(ResultCodeInterface resultCodeInterface, String message) {
        super(message);
        resultCodeInterface.setMessage(message);
        this.resultCodeInterface = resultCodeInterface;
    }

    /**
     * 构造方法
     *
     * @param resultCodeInterface 错误码
     * @param message             错误信息
     * @param cause               异常
     */
    public BaseException(ResultCodeInterface resultCodeInterface, String message, Throwable cause) {
        super(message, cause);
        resultCodeInterface.setMessage(message);
        this.resultCodeInterface = resultCodeInterface;
    }

    /**
     * 返回错误码
     *
     * @return ResultCodeInterface
     */
    public ResultCodeInterface getResultCodeInterface() {
        return resultCodeInterface;
    }
}
