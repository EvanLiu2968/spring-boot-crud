package com.crud.cloud.evanliu2968.dto.response.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "新预警详细表")
public class NewWarnDetailsRes {
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
     * 预警方式(按比例,按数量)。
     */
    @ApiModelProperty(value = "预警方式")
    private String warnWay;

    /**
     * 规则(eq:等于 ne:不等于 gt:大于 ge:大于等于 lt:小于 le:小于等于)。
     */
    @ApiModelProperty(value = "规则(eq:等于 ne:不等于 gt:大于 ge:大于等于 lt:小于 le:小于等于)。")
    private String rule;

    /**
     * 比较值。
     */
    @ApiModelProperty(value = "比较值一")
    private Integer compareValueOne;

    /**
     * 预警规则。
     */
    @ApiModelProperty(value = "预警规则")
    private String warnRule;
    /**
     * 预警规则数值。
     */
    @ApiModelProperty(value = "预警规则数值")
    private Integer warnRuleValue;

    /**
     * 文案。
     */
    @ApiModelProperty(value = "文案")
    private String copy;

    /**
     * 条件符合文案。
     */
    @ApiModelProperty(value = "条件符合文案")
    private String throughCopy;

    /**
     * 条件不符合文案。
     */
    @ApiModelProperty(value = "条件不符合文案")
    private String notThroughCopy;
}
