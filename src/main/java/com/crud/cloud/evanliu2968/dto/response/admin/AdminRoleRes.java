package com.crud.cloud.evanliu2968.dto.response.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "AdminRoleRes")
public class AdminRoleRes {
    /**
     * 主键
     */
    @ApiModelProperty(value ="主键id")
    private Integer id;

    /**
     * 角色
     * 名称
     */
    @ApiModelProperty(value ="角色名称")
    private String roleName;

    /**
     * 管理员账号数量
     */
    @ApiModelProperty(value ="管理员账号数量")
    private String adminCount;

    List<AdminPermissionsRes> permissions;
}
