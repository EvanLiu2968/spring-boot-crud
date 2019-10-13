package com.crud.cloud.evanliu2968.dto.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "DAdminMainSelectListRequest系统用查询请求对象")
public class ListUserReq {


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
}
