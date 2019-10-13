package com.crud.cloud.evanliu2968.constant.admin;

/**
 * @author xinhua.zhou
 * @date 2019-06-21
 * 此类用于：人才库、画像库权限管理枚举类
 */
public enum DataAuthorityAdminRefEnum {
    /**
     * 核心人才库
     */
    TALENT("TALENT", "核心人才库"),

    /**
     *"画像库
     */
    PORTRAIT("PORTRAIT", "画像库"),


    /**
     * 任免
     */
    APPOINT("RM", "任免"),

    /**
     * 新任
     */
    INCUMBENT("XR", "新任"),

    /**
     * 盘点
     */
    INVENTORY("INVENTORY", "盘点"),
    ;

    /**
     * 库类型code
     */
    private String code;

    /**
     * 库类型名称
     */
    private String name;

    DataAuthorityAdminRefEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
