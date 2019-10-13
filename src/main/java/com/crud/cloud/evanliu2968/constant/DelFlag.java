package com.crud.cloud.evanliu2968.constant;

public enum DelFlag {
    /**正常*/
    NORMAL(0),

    /**删除*/
    DELETED(1),

        ;

    private Integer flag;

    public Integer getFlag() {
        return this.flag;
    }

    DelFlag(Integer flag) {
        this.flag = flag;
    }
}
