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
 * 管理员用户组关联表
 * </p>
 */
@Data
@Accessors(chain = true)
@TableName("admin_group_ref")
public class AdminGroupRefPO extends Model<AdminGroupRefPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主健
     */
    @TableId("id")
    private Integer id;
    /**
     * 用户组id
     */
    @TableField("group_id")
    private Integer groupId;
    /**
     * 管理员id
     */
    @TableField("admin_id")
    private Integer adminId;
    /**
     * 状态
     */
    @TableField("status")
    @TableLogic
    private Integer status;
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
