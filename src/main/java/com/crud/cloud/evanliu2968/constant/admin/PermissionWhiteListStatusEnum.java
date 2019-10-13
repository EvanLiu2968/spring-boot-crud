package com.crud.cloud.evanliu2968.constant.admin;

/**
 * 属性类型枚举值
 * @author li.wang
 */
public enum PermissionWhiteListStatusEnum {
    /**
     * 属性类型：白名单，黑名单
     */
    /**  **/
    WHITE("Y"),
    /**  **/
    BLACK("N");

    private String value;

    PermissionWhiteListStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
