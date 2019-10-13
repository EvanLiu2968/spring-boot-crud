package com.crud.cloud.evanliu2968.constant.admin;

/**
 * token常量类
 * @author xinhua.zhou
 * @date
 */
public enum AdminTokenEnum {

    /**
     *  token拼接的前半部分
    */
    TOKEN_KEY("serviceHeader_"),

    /**
     *  token字段名
     */
    ACCESS_TOKEN("accessToken");

    private String value;

    AdminTokenEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
