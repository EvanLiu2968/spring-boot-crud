package com.crud.cloud.evanliu2968.dto.request.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "AdminGroupUpdateReq")
public class AdminGroupUpdateReq {
    /**
     * 用户组名称
     */
    @ApiModelProperty(value = "用户组名称", required = true)
    @NotNull(message = "用户组名称不能为空")
    private String groupName;


}
