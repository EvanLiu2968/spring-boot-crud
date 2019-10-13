package com.crud.cloud.evanliu2968.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 不拦截注解（请求白名单）。
 *
 * @author guohao.yang
 * @version v1.0.0
 * @since 2019/5/7 10:53
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NoLogin {
}
