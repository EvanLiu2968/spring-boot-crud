package com.crud.cloud.evanliu2968.dto.response.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by evanliu2968 on 2019-10-14.
 */
@Data
@ApiModel(value = "DeptRes")
public class DeptTreeRes {

    @ApiModelProperty(value = "更新日期")
    private String updateTime; //更新日期

    @ApiModelProperty(value = "对象类型 O组织  S职位")
    private String orgType;//对象类型 O组织  S职位

    @ApiModelProperty(value = "部门code")
    private String orgId; //对象标识

    @ApiModelProperty(value = "对象标识")
    private String orgShort; //对象缩写

    @ApiModelProperty(value = "对象标识")
    private String orgName; //对象名称

    @ApiModelProperty(value = "部门类别")
    private String deptType; //部门类别

    @ApiModelProperty(value = "上级对象类型")
    private String upOrgType; //上级对象类型

    @ApiModelProperty(value = "上级对象code")
    private String upOrgId; //上级对象标识

    @ApiModelProperty(value = "上级对象名称")
    private String upOrgName; //上级对象名称

    @ApiModelProperty(value = "对象标识")
    private String flag; //1新增 2修改 3删除

    @ApiModelProperty(value = "子节点")
    private List<DeptTreeRes> children;

}

