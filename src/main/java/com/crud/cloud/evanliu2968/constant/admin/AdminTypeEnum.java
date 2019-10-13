package com.crud.cloud.evanliu2968.constant.admin;

/**
 * 管理员常量类
 * @author xinhua.zhou
 * @date
 */
public enum AdminTypeEnum {

    /**
     *  管理员
    */
    ADMIN_TYPE("ADMIN"),

    /**
     *  普通用户
     */
    NORMAL_TYPE("NORMAL");

    private String value;

    AdminTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
