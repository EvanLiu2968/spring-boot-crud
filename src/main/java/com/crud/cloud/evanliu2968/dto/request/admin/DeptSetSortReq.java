package com.crud.cloud.evanliu2968.dto.request.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
@ApiModel(value = "上移、下移请求对象")
public class DeptSetSortReq {

    @ApiModelProperty(value = "父id")
    @NotNull(message = "父部门不能为空")
    private Integer parentId;

    @ApiModelProperty(value = "需要移动的部门Id")
    @NotNull(message = "需要移动的部门不能为空")
    private Integer sourceId;

    @ApiModelProperty(value = "上移/下移操作对应的Id")
    @NotNull(message = "操作对应的部门不能为空")
    private Integer targetId;

}
