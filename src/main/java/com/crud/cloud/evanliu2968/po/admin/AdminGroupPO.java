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
 * 用户组表
 * </p>
 */
@Data
@Accessors(chain = true)
@TableName("admin_group")
public class AdminGroupPO extends Model<AdminGroupPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户组名称
     */
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 用户组名称
     */
    @TableField("group_code")
    private String groupCode;

    /**
     * 用户组名称
     */
    @TableField("group_name")
    private String groupName;
    /**
     * 用户组状态
     */

    @TableField("group_status")
    @TableLogic
    private Integer groupStatus;
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
