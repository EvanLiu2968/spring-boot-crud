package com.crud.cloud.evanliu2968.util.session;

import lombok.Data;
import java.util.List;

/**
 * 请求头
 * Created by evanliu2968 on 2019-10-14.
 */
@Data
public class ServiceHeader {
    /**
     * 客户ID
     */
    private Integer adminId;
    /**
     * 关联员工ID
     */
    private Integer refStaffId;
    /**
     * 账号
     */
    private String adminAccount;
    /**
     * 客户名称
     */
    private String adminName;
    /**
     * 登录标记
     */
    private String accessToken;
    /**
     * 用户权限码
     */
    private List  permissionCodes;
    /**
     * 用户所属组织编码
     */
    private String orgCode;
    /**
     * 登陆token类型，0管理员登陆、1查阅登陆
     */
    private Integer accessType = 0;
    /**
     * 查阅申请ID
     */
    private Integer applyId;
    /**
     * 授权查阅的截止时间
     */
    private Long permissionEndTime;

    /**
     * 管理员类型
     */
    private String type;

    /**
     * 所属集团
     */
    private String adminGroup;

    /**
     * 任职岗位
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

}
