package com.tencent.athena.core;

import com.tencent.athena.core.exception.BaseException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Objects;


/**
 * @author qinzhongjian
 */
public interface Assert {
    /**
     * 创建异常
     *
     * @param message 异常信息
     * @return 返回异常
     */
    BaseException newException(String message);

    /**
     * 创建异常
     *
     * @param t       异常
     * @param message 异常信息
     * @return 返回异常
     */
    BaseException newException(Throwable t, String message);

    /**
     * <p>断言对象<code>obj</code>非空。如果对象<code>obj</code>为空，则抛出异常
     *
     * @param obj 待判断对象
     */
    default void assertNotNull(Object obj) {
        if (obj == null) {
            throw newException(null);
        }
    }

    /**
     * <p>断言对象<code>obj</code>非空。如果对象<code>obj</code>为空，则抛出异常
     * <p>异常信息<code>message</code>支持传递参数方式，避免在判断之前进行字符串拼接操作
     *
     * @param obj     待判断对象
     * @param message message占位符对应的参数列表
     */
    default void assertNotNull(Object obj, String message) {
        if (obj == null) {
            throw newException(message);
        }
    }


    /***
     * 具体使用可以参考{@link org.springframework.util.Assert#state(boolean, String)}
     * @param expression 表达式
     * @param message 自定义信息
     */
    default void state(boolean expression, String message) {
        if (!expression) {
            throw newException(message);
        }
    }

    /**
     * 断言字符串是否为空
     *
     * @param text    字符串
     * @param message 自定义信息
     */
    default void hasText(@Nullable String text, String message) {
        if (StringUtils.isBlank(text)) {
            throw newException(message);
        }
    }

    /**
     * 判断集合是否为空
     *
     * @param collection 集合
     * @param message    自定义信息
     */
    default void notEmpty(@Nullable Collection<?> collection, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw newException(message);
        }
    }

    /**
     * 判断集合是否有空元素
     *
     * @param collection 集合
     * @param message    自定义信息
     */
    default void noNullElements(@Nullable Collection<?> collection, String message) {
        if (collection != null) {
            collection.stream().filter(Objects::isNull).findFirst().ifPresent(s -> {
                throw newException(message);
            });
        }
    }

    /**
     * 判断集合是否有空元素
     *
     * @param collection 集合
     * @param message    自定义信息
     */
    default void noBlankElements(@Nullable Collection<String> collection, String message) {
        if (collection != null) {
            collection.stream().filter(element -> StringUtils.isBlank(element)).findFirst().ifPresent(s -> {
                throw newException(message);
            });
        }
    }
}
