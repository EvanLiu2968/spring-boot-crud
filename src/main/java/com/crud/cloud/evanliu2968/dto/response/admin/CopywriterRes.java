package com.crud.cloud.evanliu2968.dto.response.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "新预警详细表")
public class CopywriterRes {
    /**
     * 主键。
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 维度。
     */
    @ApiModelProperty(value = "维度")
    private String dimensionCode;

    /**
     * 条目。
     */
    @ApiModelProperty(value = "条目")
    private Integer entry;

    /**
     * 通过词条内容。
     */
    @ApiModelProperty(value = "通过词条内容")
    private String entryContent;

    /**
     * 不通过词条内容。
     */
    @ApiModelProperty(value = "不通过词条内容")
    private String notEntryContent;

}
