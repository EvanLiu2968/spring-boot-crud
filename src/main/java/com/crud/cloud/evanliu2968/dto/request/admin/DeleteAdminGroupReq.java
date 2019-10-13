package com.crud.cloud.evanliu2968.dto.request.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "DeleteAdminGroupReq")
public class DeleteAdminGroupReq {


    /**
     * 用户组id数组
     */
    @ApiModelProperty(value = "用户组ids")
    @NotNull
    private Integer[] ids;

}
