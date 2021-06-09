package com.tencent.athena.config;

import com.tencent.athena.core.ResultVO;
import com.tencent.athena.utils.TraceUtils;
import org.slf4j.MDC;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 统一返回对象配置类
 *
 * @author qinzhongjian
 */
@ControllerAdvice
public class ResultBodyAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 针对以下情况 swagger的资源放过封装
     * 其余所有返回均封装到ResultVO类中
     *
     * @param methodParameter 方法参数
     * @param aClass 消息转化类
     * @return true代表会进行body重写
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {

        return true;

    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType
            , Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ResultVO) {
            ResultVO resultVO = ResultVO.class.cast(body);
            resultVO.setTraceId(TraceUtils.getTraceId());
            return body;
        }
        RestController restController = returnType.getDeclaringClass().getAnnotation(RestController.class);
        if (restController == null) {
            return body;
        }
        ResultVO resultVO = ResultVO.ofSuccess(body);
        resultVO.setTraceId(TraceUtils.getTraceId());
        MDC.clear();
        return resultVO;
    }
}
