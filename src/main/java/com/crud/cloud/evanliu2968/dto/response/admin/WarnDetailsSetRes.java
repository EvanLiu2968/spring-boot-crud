package com.crud.cloud.evanliu2968.dto.response.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "预警详细表")
public class WarnDetailsSetRes {
    /**
     * 主键。
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 维度。
     */
    @ApiModelProperty(value = "维度")
    private String dimension;

    /**
     * 规则(eq:等于 ne:不等于 gt:大于 ge:大于等于 lt:小于 le:小于等于)。
     */
    @ApiModelProperty(value = "规则(eq:等于 ne:不等于 gt:大于 ge:大于等于 lt:小于 le:小于等于)。")
    private String rule;

    /**
     * 比较值。
     */
    @ApiModelProperty(value = "比较值")
    private Integer compareValue;

    /**
     * 创建时间。
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 修改时间。
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 备注。
     */
    @ApiModelProperty(value = "备注")
    private String note;

    /**
     * 规则。
     */
    @ApiModelProperty(value = "规则")
    private Integer ruleFlag;

    /**
     * 展示。
     */
    @ApiModelProperty(value = "展示")
    private Integer showFlag;
}
