package com.crud.cloud.evanliu2968.po.admin;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户管理员
 * </p>
 */
@Data
@Accessors(chain = true)
@TableName("admin_main")
public class AdminMainPO extends Model<AdminMainPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 管理员名称
     */
    @TableField("admin_name")
    private String adminName;

    /**
     * 管理员手机
     */
    @TableField("admin_mobile")
    private String adminMobile;
    /**
     * 管理员状态
     */
    @TableField("admin_status")
    private Integer adminStatus;

    /**
     * 管理员密码
     */
    @TableField("admin_pwd")
    private String adminPwd;
    /**
     * 管理员账号
     */
    @TableField("admin_account")
    private String adminAccount;
    /**
     * 管理员身份证号码
     */
    @TableField("admin_idcard")
    private String adminIdcard;
    /**
     * 锁定标志:1代表锁定状态 0未锁定状态
     */
    @TableField("lock_status")
    private Integer lockStatus;
    /**
     * 登录失败次数
     */
    @TableField("failure_num")
    private Integer failureNum;

    /**
     * 修改时间。
     */
    private Date updateTime;

    /**
     * 创建时间。
     */
    private Date createTime;

    /**
     * 修改人。
     */
    private Integer updateId;

    /**
     * 创建人。
     */
    private Integer createId;

    /**
     * 备注。
     */
    private String note;

    /**
     * 版本。
     */
    @TableField(value="version", update="%s+1")
    private Integer version;

    /**
     * 0：正常 1：已删除。
     */
    @TableLogic
    private Integer delFlag;


    /**
     * 管理员类型
     */
    private String type;

    /**
     * 人员id
     */
    private Integer personId;

    /**
     * 所属集团
     */
    @TableField("admin_group")
    private String adminGroup;

    /**
     * 任职岗位
     */
    @TableField("admin_post")
    private String adminPost;

    /**
     * 个性签名
     */
    @TableField("personal_signature")
    private String personalSignature;

    /**
     * 管理员头像
     */
    @TableField("image_url")
    private String imageUrl;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
