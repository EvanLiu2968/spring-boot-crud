package com.crud.cloud.evanliu2968.dto.request.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "DAdminMainSelectListRequest系统用查询请求对象")
public class AdminMainSelectListReq {


    /**
     * 管理员账号
     */
    @ApiModelProperty(value = "关键字")
    private String keyWord;
    /**
     */
    @ApiModelProperty(value = " 角色名称")
    private String roleNames;
    /**
     * 管理员状态
     */
    @ApiModelProperty(value = " 管理员状态")
    private Integer adminStatus;
    /**
     * 锁定状态
     */
    @ApiModelProperty(value = " 锁定状态")
    private Integer lockStatus;
    /**
     * 角色id
     */
    @ApiModelProperty(value = " 角色id")
    private Integer roleId;
    /**
     * 用户组id
     */
    @ApiModelProperty(value = " 用户组id")
    private Integer groupId;
    /**
     * 数据id
     */
    @ApiModelProperty(value = "库数据id")
    private Integer dataId;

    /**
     * 库类型
     */
    @ApiModelProperty(value = "库类型,人才库(TALENT)，1表示画像库(PORTRAIT)")
    private String type;



}
