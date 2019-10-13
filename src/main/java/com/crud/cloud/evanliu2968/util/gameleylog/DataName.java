package com.crud.cloud.evanliu2968.util.gameleylog;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface DataName {
    /**
     * 字段名称
     * @return
     */
    String name() default "";

 }
