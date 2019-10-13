package com.crud.cloud.evanliu2968.po;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 盘点任务日程。
 *
 * @author auto.crud
 * @version v2.0.0
 */
@Data
@Accessors(chain = true)
@TableName("inventory_job_schedule")
public class InventoryJobSchedulePO extends Model<InventoryJobSchedulePO> {

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
     * 日程、会议标题。
     */
    @TableField("title")
    private String title;

    /**
     * 开始时间。
     */
    @TableField("begin_time")
    private Date beginTime;

    /**
     * 结束时间。
     */
    @TableField("end_time")
    private Date endTime;

    /**
     * 提醒方案字典项。
     */
    @TableField("alert_scheme_code")
    private String alertSchemeCode;

    /**
     * 提醒类型字典项。
     */
    @TableField("alert_type_code")
    private String alertTypeCode;

    /**
     * 地点。
     */
    @TableField("place")
    private String place;

    /**
     * 描述。
     */
    @TableField("describe")
    private String describe;

    /**
     * 修改时间。
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 创建时间。
     */
    @TableField("create_time")
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