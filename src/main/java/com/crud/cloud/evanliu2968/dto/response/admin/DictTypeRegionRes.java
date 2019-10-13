package com.crud.cloud.evanliu2968.dto.response.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "代码表地区")
public class DictTypeRegionRes {
    /**
     * 字典表id。
     */
    @ApiModelProperty(value = "字典表id")
    private Integer id;

    /**
     * 字典类别表id。
     */
    @ApiModelProperty("字典类别表id")
    private Integer dictTypeId;

    /**
     * 父id。
     */
    @ApiModelProperty("父id")
    private Integer parentId;

    /**
     * 编码。
     */
    @ApiModelProperty("编码")
    private String code;

    /**
     * 名称。
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 排序字段。
     */
    @ApiModelProperty("排序字段")
    private Integer sort;

    /**
     * 汉语拼音。
     */
    @ApiModelProperty("汉语拼音")
    private String pinyin;

    /**
     * 简称。
     */
    @ApiModelProperty("简称")
    private String firstShortName;

    @ApiModelProperty("类型：0-初始化的字典  1-动态新增的字典项")
    private Integer type;
}
