package com.tencent.athena.core;

import com.tencent.athena.config.LogConfig;
import com.tencent.athena.core.enums.ResultCodeEnum;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一返回对象
 *
 * @author qinzhongjian
 * @since JDK 1.8.0_212-b10
 */

@Data
@Builder
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = -4448696578582628909L;
    /**
     * 链路追踪id
     */
    private String traceId;
    /**
     * 调用是否成功
     */
    private Boolean success;
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 错误信息
     */
    private String message;
    /**
     * 数据
     */
    private T data;

    /**
     * 响应成功封装
     *
     * @param data 数据对象
     * @param <T>  数据类型
     * @return ResultVO
     */
    public static <T> ResultVO<T> ofSuccess(T data) {
        return ResultVO.<T>builder()
                .code(ResultCodeEnum.OK.getCode())
                .message(ResultCodeEnum.OK.getMessage())
                .traceId(LogConfig.traceUtil.get())
                .success(Boolean.TRUE)
                .data(data)
                .build();
    }

    /**
     * 响应错误封装
     *
     * @param resultCode 错误码
     * @return ResultVO
     */
    public static ResultVO ofError(ResultCodeInterface resultCode) {
        return ofError(resultCode.getCode(), resultCode.getMessage());
    }

    /**
     * 响应错误封装
     *
     * @param code    错误码
     * @param message 错误信息
     * @return ResultVO
     */
    public static ResultVO ofError(int code, String message) {
        return ResultVO.builder()
                .code(code)
                .message(message)
                .traceId(LogConfig.traceUtil.get())
                .success(Boolean.FALSE)
                .build();
    }
}