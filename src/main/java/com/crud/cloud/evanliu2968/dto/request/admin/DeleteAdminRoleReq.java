package com.crud.cloud.evanliu2968.dto.request.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "DeleteAdminRoleReq")
public class DeleteAdminRoleReq {


    /**
     * 角色ids
     */
    @ApiModelProperty(value = "角色ids")
    @NotNull
    private Integer[] ids;

}
