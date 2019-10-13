package com.crud.cloud.evanliu2968.dto.request.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "复制到虚拟部门")
public class CopyToVirtualDeptReq {

    @ApiModelProperty(value = "实际部门id")
    @NotNull(message = "实际部门id")
    private Integer deptId;

    @ApiModelProperty(value = "虚拟部门id")
    @NotNull(message = "虚拟部门id")
    private Integer virtualDeptId;

}
