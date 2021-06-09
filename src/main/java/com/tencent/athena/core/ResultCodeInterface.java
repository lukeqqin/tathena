package com.tencent.athena.core;

/**
 * 返回码接口
 *
 * @author qinzhongjian
 * @since JDK 1.8.0_212-b10
 */
public interface ResultCodeInterface {

    /**
     * 错误码
     *
     * @return 错误码
     */
    int getCode();

    /**
     * 错误具体信息
     *
     * @return 错误具体信息
     */
    String getMessage();

    /***
     * 参数占位符功能
     * @param message 信息
     */
    void setMessage(String message);
}
