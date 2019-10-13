package com.crud.cloud.evanliu2968.dto.request.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "PostReq")
public class PostReq {

    /**
     * 岗位id
     */
    @ApiModelProperty(value = "岗位id", required = true)
    @NotNull(message = "岗位主键不能为空")
    private Integer id;

    /**
     * 岗位名称
     */
    @ApiModelProperty(value = "岗位名称", required = true)
    @NotNull(message = "岗位名称不能为空")
    private String postName;

    /**
     * 岗位层级
     */
    @ApiModelProperty(value = "岗位层级", required = true)
    @NotNull(message = "岗位层级不能为空")
    private String level;

    /**
     * 岗位数量
     */
    @ApiModelProperty(value = "岗位数量")
    private Integer count;

    /**
     * 部门地址
     */
    @ApiModelProperty(value = "部门地址")
    private String place;

}
