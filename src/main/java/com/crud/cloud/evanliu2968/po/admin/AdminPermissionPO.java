package com.crud.cloud.evanliu2968.po.admin;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 权限表
 * </p>
 */
@Data
@Accessors(chain = true)
@TableName("admin_permission")
public class AdminPermissionPO extends Model<AdminPermissionPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("id")
    private Integer id;
    /**
     * 权限名称
     */
    @TableField("permission_name")
    private String permissionName;
    /**
     * 编码
     */
    @TableField("rec_code")
    private String recCode;

    /**
     * 父权限id
     */
    @TableField("parent_id")
    private String parentId;

    /**
     * 权限url
     */
    @TableField("url")
    private String url;

    /**
     * 是否白名单
     */
    @TableField("white_list_status")
    private String whiteListStatus;

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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
