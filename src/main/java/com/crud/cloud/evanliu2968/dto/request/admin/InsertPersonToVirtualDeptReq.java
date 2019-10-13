package com.crud.cloud.evanliu2968.dto.request.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel(value = "加入虚拟部门请求对象")
public class InsertPersonToVirtualDeptReq {

    @ApiModelProperty(value = "人员id")
    @NotNull(message = "人员id")
    private List<Integer> personIds;

    @ApiModelProperty(value = "虚拟部门id")
    @NotNull(message = "虚拟部门id")
    private Integer virtualDeptId;

}
