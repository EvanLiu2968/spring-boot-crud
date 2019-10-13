package com.crud.cloud.evanliu2968.constant.admin;

/**
 * 启用和删除属性类型枚举值
 * @author li.wang
 */
public enum AdminEnableAndDisableEnum {
    /**
     * 属性类型：启用，删除
     */
    /**  **/
    ENABLE(0),
    /**  **/
    DISABLE(1);

    private Integer value;

    AdminEnableAndDisableEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
