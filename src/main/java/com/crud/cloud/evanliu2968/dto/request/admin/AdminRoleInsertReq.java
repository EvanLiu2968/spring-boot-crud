package com.crud.cloud.evanliu2968.dto.request.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "DAdminRoleInsertRequest插入对象")
public class AdminRoleInsertReq {

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称", required = true)
    @NotNull(message = "角色名称不能为空")
    private String roleName;
    /**
     * 权限id数组
     */
    @ApiModelProperty(value = "权限id数组")
    Integer[] permissionIds;


}
