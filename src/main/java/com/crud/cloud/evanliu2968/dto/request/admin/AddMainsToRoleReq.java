package com.crud.cloud.evanliu2968.dto.request.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "DAddMainsToRoleRequest插入对象")
public class AddMainsToRoleReq {
    /**
     * 用户id数组
     */
    @ApiModelProperty(value = "用户id数组")
    Integer[] adminIds;
}
