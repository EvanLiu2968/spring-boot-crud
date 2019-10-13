package com.crud.cloud.evanliu2968.dto.request.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "新预警设置修改")
public class NewWarnDeatilsReq {
    /**
     * 主键。
     */
    @ApiModelProperty(value = "主键")
    private Integer id;


    /**
     * 比较值。
     */
    @ApiModelProperty(value = "比较值一")
    private Integer compareValueOne;

    /**
     * 预警规则数值。
     */
    @ApiModelProperty(value = "预警规则数值")
    private Integer warnRuleValue;

    /**
     * 预警通过文案。
     */
    @ApiModelProperty(value = "预警通过文案")
    private String throughCopy;

    /**
     * 预警不通过文案。
     */
    @ApiModelProperty(value = "预警不通过文案")
    private String notThroughCopy;
}
