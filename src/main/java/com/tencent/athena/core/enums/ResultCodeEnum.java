package com.tencent.athena.core.enums;

import com.tencent.athena.core.ResultAssertAdapter;
import lombok.Getter;

/**
 * 状态码
 *
 * @author qinzhongjian
 * @since JDK 1.8.0_212-b10
 */
@Getter
public enum ResultCodeEnum implements ResultAssertAdapter {
    /**
     * 响应成功
     */
    OK(200, "OK"),
    /**
     * 参数不合法
     */
    ILLEGAL_ARGUMENT(301, "ILLEGAL ARGUMENT"),
    /**
     * 请求错误
     */
    BAD_REQUEST(400, "Bad Request"),
    /**
     * 未认证
     */
    UNAUTHORIZED(401, "Unauthorized"),
    /**
     * 拒绝访问
     */
    FORBIDDEN(403, "Forbidden"),
    /**
     * 没有找到
     */
    NOT_FOUND(404, "Not Found.{}"),
    /**
     * 请求超时
     */
    REQUEST_TIMEOUT(408, "Request Timeout"),
    /**
     * 服务内部错误
     */
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private int code;
    private String message;

    ResultCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }
}
