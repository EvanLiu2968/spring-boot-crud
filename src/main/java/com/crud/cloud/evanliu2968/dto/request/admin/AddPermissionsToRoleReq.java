package com.crud.cloud.evanliu2968.dto.request.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "DAddPermissionsToRoleRequest插入对象")
public class AddPermissionsToRoleReq {
    /**
     * 用户id数组
     */
    @ApiModelProperty(value = "权限id数组")
    Integer[] permissionIds;
}
