package com.crud.cloud.evanliu2968.dto.request.admin;

import com.crud.cloud.evanliu2968.annotation.IdentityAnntation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "DAdminMainUpdateRequest插入对象")
public class AdminMainUpdateReq {

    /**
     * 管理员名称
     */
    @ApiModelProperty(value = "管理员名称")
    private String adminName;
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
     * 管理员密码
     */
    @ApiModelProperty(value = "管理员密码")
    private String adminPwd;
    /**
     * 管理员账号
     */
    @ApiModelProperty(value = "管理员账号")
    private String adminAccount;

    /**
     * 管理员身份证号码
     */
    @IdentityAnntation(message = "身份证号码格式不正确")
    @ApiModelProperty(value = "管理员身份证号码")
    private String adminIdcard;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型,管理员(ADMIN),普通用户(NORMAL)", required = true)
    private String type;


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
