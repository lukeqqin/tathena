package com.tencent.athena.core;

import com.tencent.athena.core.exception.BaseException;
import com.tencent.athena.core.exception.BizRuntimeException;
import org.apache.commons.lang3.StringUtils;

/**
 * @author qinzhongjian
 */
public interface ResultAssertAdapter extends ResultCodeInterface, Assert {

    /***
     * 构建具有替换占位符的异常信息功能的异常
     * @param message 异常信息
     * @return com.xuandui.intra.b.company.exception.BaseException
     */
    @Override
    default BaseException newException(String message) {
        if (StringUtils.isBlank(message)) {
            return new BizRuntimeException(this, this.getMessage());
        }
        return new BizRuntimeException(this, message);
    }

    /***
     * 构建具有替换占位符的异常信息功能和外部异常作为参数的异常
     * @param t 异常
     * @param message 异常信息
     * @return com.xuandui.intra.b.company.exception.BaseException
     */
    @Override
    default BaseException newException(Throwable t, String message) {
        if (StringUtils.isBlank(message)) {
            return new BizRuntimeException(this, this.getMessage(), t);
        }
        return new BizRuntimeException(this, message, t);
    }

}
