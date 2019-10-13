package com.crud.cloud.evanliu2968.annotation;

import java.lang.annotation.*;

/**
 * Created by evanliu2968 on 2019-10-14.
 * @author kanghong.zhao
 * 用来标志不需要会话的
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoSession {
}
