package com.tencent.athena.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.Field;

/**
 * aop工具
 *
 * @author qinzhongjian
 */
public class AopTargetUtils {
    private final static Logger logger = LoggerFactory.getLogger(AopTargetUtils.class);

    /**
     * 获取目标对象
     *
     * @param proxy 代理
     * @return 目标对象
     */
    public static Object getTarget(Object proxy) {
        if (proxy == null) {
            return null;
        }
        if (!AopUtils.isAopProxy(proxy)) {
            return proxy;
        }
        return getTarget(_getTarget(proxy));
    }

    /**
     * 获取目标对象
     *
     * @param proxy 代理
     * @return 目标对象
     */
    private static Object _getTarget(Object proxy) {
        if (proxy instanceof Advised) {
            TargetSource targetSource = ((Advised) proxy).getTargetSource();
            try {
                logger.info("get target object from Advised#getTargetSource[" + proxy.getClass().getName() + "]");
                return targetSource == null ? null : targetSource.getTarget();
            } catch (Exception e) {
                logger.error("get target object from Advised#getTargetSource[" + proxy.getClass().getName() + "] error", e);
            }
        }
        if (AopUtils.isCglibProxy(proxy)) {
            try {
                return getCglibProxyTargetObject(proxy);
            } catch (Exception e) {
                logger.error("get target object from Cglib [" + proxy.getClass().getName() + "] error", e);
            }
        } else if (AopUtils.isJdkDynamicProxy(proxy)) {
            try {
                return getJdkDynamicProxyTargetObject(proxy);
            } catch (Exception e) {
                logger.error("get target object from Jdk [" + proxy.getClass().getName() + "] error", e);
            }
        }
        return null;
    }

    /**
     * 获取cglib代码目标对象
     *
     * @param proxy 代理
     * @return 目标对象
     * @throws Exception 异常
     */
    private static Object getCglibProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
        h.setAccessible(true);
        Object dynamicAdvisedInterceptor = h.get(proxy);
        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        Object target = ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
        logger.info("get target object [" + target.getClass().getName() + "] from Cglib reflect ,from proxy:" + proxy.getClass().getName());
        return target;
    }

    /**
     * 获取cglib代码目标对象
     *
     * @param proxy 代理
     * @return 目标对象
     * @throws Exception 异常
     */
    private static Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);
        AopProxy aopProxy = (AopProxy) h.get(proxy);
        Field advised = aopProxy.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        Object target = ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();
        logger.info("get target object [" + target.getClass().getName() + "] from Jdk reflect ,from proxy:" + proxy.getClass().getName());
        return target;
    }

}
