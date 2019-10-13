package com.crud.cloud.evanliu2968.dto.response.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "PostAndDeptListRes")
public class PostAndDeptListRes {
    /**
     * 主键
     */
    @ApiModelProperty(value ="主键id")
    private Integer postId;
    /**
     * 岗位名称
     */
    @ApiModelProperty(value ="岗位名称")
    private String name;

    /**
     * 部门id
     */
    @ApiModelProperty(value ="部门id")
    private Integer departId;

    /**
     * 所属产业
     */
    @ApiModelProperty(value ="所属产业")
    private String industry;
    /**
     * 所属公司
     */
    @ApiModelProperty(value ="所属公司")
    private String company;

    /**
     * 所属部门
     */
    @ApiModelProperty(value ="所属部门")
    private String depart;

    /**
     * 所属集团
     */
    @ApiModelProperty(value ="所属集团")
    private String group;

    /**
     * 职位族
     */
    @ApiModelProperty(value ="职位族")
    private String postTypeCode;

    /**
     * ggs
     */
    @ApiModelProperty(value ="ggs")
    private String ggs;

    /**
     * 序列
     */
    @ApiModelProperty(value ="序列")
    private String postLevelCode;

    /**
     * 当前任职人id
     */
    @ApiModelProperty(value ="当前任职人id")
    private Integer personId;
    /**
     * 当前任职人
     */
    @ApiModelProperty(value ="当前任职人")
    private String personName;

    /**
     * 任职时间
     */
    @ApiModelProperty(value ="任职时间")
    private String beginDate;
}
