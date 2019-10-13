package com.crud.cloud.evanliu2968.dto.request.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "LoginReq")
public class LoginReq {


    /**
     * 管理员账号
     */
    @ApiModelProperty(value = "管理员账号", required = true)
    @NotNull(message = "用户账号不能为空")
    private String adminAccount;

    /**
     * 管理员密码
     */
    @ApiModelProperty(value = "管理员密码", required = true)
    @NotNull(message = "用户密码不能为空")
    private String adminPwd;

//    /**
//     * 验证码
//     */
//    @ApiModelProperty(value = "验证码", required = true)
//    @NotNull(message = "验证码不能为空")
//    private String imageCode;

}
