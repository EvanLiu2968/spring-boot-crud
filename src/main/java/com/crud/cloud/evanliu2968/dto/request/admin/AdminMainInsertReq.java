package com.crud.cloud.evanliu2968.dto.request.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "DAdminMainInsertRequest插入对象")
public class AdminMainInsertReq {

    /**
     * 管理员名称
     */
    @ApiModelProperty(value = "管理员名称")
    private String adminName;

    /**
     * 管理员密码
     */
    @ApiModelProperty(value = "管理员密码", required = true)
    @NotNull(message = "用户密码不能为空")
    private String adminPwd;
    /**
     * 管理员账号
     */
    @ApiModelProperty(value = "管理员账号", required = true)
    @NotNull(message = "用户账号不能为空")
    private String adminAccount;
   /* *//**
     * 角色id数组
     *//*
    @ApiModelProperty(value = "角色id数组")
    Integer[] roleIds;*/
    /**
     * 管理员身份证号码
     */
    @ApiModelProperty(value = "管理员身份证号码")
//    @IdentityAnntation(message = "身份证号码格式不正确")
    private String adminIdcard;

    /**
     * 管理员类型
     */
    @ApiModelProperty(value = "管理员类型")
    private String type = "ADMIN";

    /**
     * 人员信息id
     */
    @ApiModelProperty(value = "人员信息id")
    private Integer personId;

    /**
     * 管理员手机
     */
    @ApiModelProperty(value = "管理员手机")
    private String adminMobile;

}
