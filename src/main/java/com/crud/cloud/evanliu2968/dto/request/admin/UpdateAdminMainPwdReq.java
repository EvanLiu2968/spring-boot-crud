package com.crud.cloud.evanliu2968.dto.request.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "UpdateAdminMainPwdReq")
public class UpdateAdminMainPwdReq {

    /**
     * 管理员旧密码
     */
    @ApiModelProperty(value = "管理员旧密码")
    private String adminPwdOld;
    /**
     * 管理员新密码
     */
    @ApiModelProperty(value = "管理员新密码")
    private String adminPwdNew;

    /**
     * 确认密码
     */
    @ApiModelProperty(value = "确认密码")
    private String adminPwdEnsure;


}
