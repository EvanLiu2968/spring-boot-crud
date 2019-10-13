package com.crud.cloud.evanliu2968.po;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 盘点任务人员关联表。
 *
 * @author auto.crud
 * @version v2.0.0
 */
@Data
@Accessors(chain = true)
@TableName("inventory_job_person")
public class InventoryJobPersonPO extends Model<InventoryJobPersonPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id。
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 盘点任务id。
     */
    @TableField("inventory_job_id")
    private Integer inventoryJobId;

    /**
     * 人员id。
     */
    @TableField("person_id")
    private Integer personId;

    @TableField(exist = false)
    private String personName;

    /**
     * 状态（0-未开始，1-进行中，2-已完成）。
     */
    @TableField("status")
    private Integer status;

    /**
     * 状态（0-未开始，1-进行中，2-已完成）。
     */
    @TableField("group_label")
    private String groupLabel;

    /**
     * 个人检视状态（0-未开启 1-开启个人检视但可关闭 2-开启个人检视不可关闭）。
     */
    @TableField("inspection_status")
    private Integer inspectionStatus;

    /**
     * 修改时间。
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 创建时间。
     */
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /**
     * 修改人。
     */
    @TableField("update_id")
    private Integer updateId;

    /**
     * 创建人。
     */
    @TableField("create_id")
    private Integer createId;

    /**
     * 备注。
     */
    @TableField("note")
    private String note;

    /**
     * 版本。
     */
    @TableField(value="version", update="%s+1")
    private Integer version;

    /**
     * 0：正常 1：已删除。
     */
    @TableField("del_flag")
    @TableLogic
    private Integer delFlag;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
