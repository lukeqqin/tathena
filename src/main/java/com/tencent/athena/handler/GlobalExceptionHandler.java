package com.tencent.athena.handler;

import com.tencent.athena.config.LogConfig;
import com.tencent.athena.core.ResultVO;
import com.tencent.athena.core.enums.ResultCodeEnum;
import com.tencent.athena.core.exception.BizRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理器
 *
 * @author qinzhongjian
 * @since JDK 1.8.0_212-b10
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * 参数不合法异常处理
     *
     * @param e 参数不合法异常
     * @return ResultVO
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResultVO handleIllegalArgumentException(IllegalArgumentException e) {
        logging(e);
        return ResultVO.ofError(ResultCodeEnum.ILLEGAL_ARGUMENT.getCode(), e.getMessage());
    }

    /**
     * 业务异常处理
     *
     * @param e 业务异常
     * @return ResultVO
     */
    @ExceptionHandler(BizRuntimeException.class)
    public ResultVO handleBizRuntimeException(BizRuntimeException e) {
        logging(e);
        return ResultVO.builder()
                .success(Boolean.FALSE)
                .traceId(LogConfig.traceUtil.get())
                .code(e.getResultCodeInterface().getCode())
                .message(e.getMessage())
                .build();
    }

    // other handle logic...

    /**
     * 异常处理
     *
     * @param e 未知异常
     * @return ResultVO
     */
    @ExceptionHandler(Exception.class)
    public ResultVO handleException(Exception e) {
        logging(e);
        return ResultVO.ofError(ResultCodeEnum.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
    }

    private void logging(Exception e) {
        log.error("the request invoke occur an error!", e);
    }

}
