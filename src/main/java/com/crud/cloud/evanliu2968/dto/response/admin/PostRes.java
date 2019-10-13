package com.crud.cloud.evanliu2968.dto.response.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "PostRes")
public class PostRes {
    /**
     * 主键
     */
    @ApiModelProperty(value ="主键id")
    private Integer id;
    /**
     * 岗位名称
     */
    @ApiModelProperty(value ="岗位名称")
    private String name;

    /**
     * 岗位人数
     */
    @ApiModelProperty(value ="岗位人数")
    private String postCount;

    /**
     * 集团名称
     */
    @ApiModelProperty(value ="集团名称")
    private String groupName;

    /**
     * 产业名称
     */
    @ApiModelProperty(value ="产业名称")
    private String industryName;

    /**
     * 公司名称
     */
    @ApiModelProperty(value ="公司名称")
    private String CompanyName;

    /**
     * 部门名称
     */
    @ApiModelProperty(value ="部门名称")
    private String deptName;
}
