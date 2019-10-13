package com.crud.cloud.evanliu2968.dto.response.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "AdminMainRes")
public class AdminMainRes {
    /**
     * 主键
     */
    private Integer id;

    /**
     * AdminPermissionsRes
     */
    private String adminName;
    /**
     * 管理员状态
     */
    private Integer adminStatus;
    /**
     * 锁定状态
     */
    @ApiModelProperty(value = " 锁定状态")
    private Integer lockStatus;
    /**
     * 管理员状态名称
     */
    private String adminStatusName;
    /**
     * 管理员创建人账号
     */
    private String createdAdminAccount;
    /**
     * 管理员创建时间
     */
    private Date createTime;
    /**
     * 管理员账号
     */
    private String adminAccount;
    /**
     * 角色名称组合
     */
    private String roleNames;
    /**
     * 管理员身份证号码
     */
    private String adminIdcard;

    /**
     * 管理员类型
     */
    private String type;

    private  Boolean selected;

    /**
     * 人员id
     */
    private String personId;

    /**
     * 人员名称
     */
    private String personName;

    /**
     * 管理员所属集团
     */
    private String adminGroup;

    /**
     * 管理员任职岗位
     */
    private String adminPost;

    /**
     * 个性签名
     */
    private String personalSignature;

    /**
     * 管理员头像
     */
    private String imageUrl;

    List<AdminRoleRes> roles;

    List<AdminGroupRes> groups;

    /**
     * 管理员手机
     */
    private String adminMobile;

    private Integer allowFlag;
}
