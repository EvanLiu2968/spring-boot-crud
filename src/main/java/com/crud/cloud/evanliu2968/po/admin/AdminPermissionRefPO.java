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
 * 用户权限关联表
 * </p>
 */
@Data
@Accessors(chain = true)
@TableName("admin_permission_ref")
public class AdminPermissionRefPO extends Model<AdminPermissionRefPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("id")
    private Integer id;
    /**
     * 角色主键
     */
    @TableField("role_id")
    private Integer roleId;
    /**
     * 权限主键
     */
    @TableField("permission_id")
    private Integer permissionId;
    /**
     * 权限组主键
     */
    @TableField("permission_group_id")
    private Integer permissionGroupId;
    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 修改人
     */
    @TableField("update_id")
    private Integer updateId;
    /**
     * 状态
     */
    @TableField("status")
    @TableLogic
    private Integer status;


    /**
     * 创建时间。
     */
    private Date createTime;

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
