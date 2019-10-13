package com.crud.cloud.evanliu2968.dto.request.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel(value = "InsertDeptReq")
public class InsertDeptReq {
    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称", required = true)
    @NotNull(message = "部门名称不能为空")
    private String name;

    /**
     * 部门描述
     */
    @ApiModelProperty(value = "部门描述")
    private String note;

    /**
     * 部门类型
     */
    @ApiModelProperty(value = "部门类型，0代表实际部门，1代表虚拟部门")
    private String type;

    /**
     * 上级部门主健
     */
    @ApiModelProperty(value = "上级部门主健，没有上级部门传空")
    private Integer deptId;

    /**
     * 岗位列表信息
     */
    @ApiModelProperty(value = "岗位列表信息")
    List<PostReq> postReqList;
}
