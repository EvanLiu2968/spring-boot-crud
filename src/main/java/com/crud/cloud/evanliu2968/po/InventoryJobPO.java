package com.crud.cloud.evanliu2968.po;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * 盘点任务。
 *
 * @author auto.crud
 * @version v2.0.0
 */
@Data
@Accessors(chain = true)
@TableName("inventory_job")
public class InventoryJobPO extends Model<InventoryJobPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id。
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

       /**
     * 盘点任务名称。
     */
    @TableField("job_name")
    private String jobName;

    /**
     * 盘点启动时间。
     */
    @TableField("start_time")
    private Date startTime;

    /**
     * 方案名称。
     */
    @TableField("scheme_name")
    private String schemeName;

    /**
     * 盘点绩效时间。
     */
    @TableField("performce_time")
    private String performceTime;

    /**
     * 其他要求。
     */
    @TableField("other_demand")
    private String otherDemand;

    /**
     * 计划盘点完成时间。
     */
    @TableField("end_time")
    private Date endTime;

    /**
     * 盘点组织范围。
     */
    @TableField("dept_info")
    private String deptInfo;

    /**
     * 状态（1-归档）。
     */
    @TableField("status")
    private Integer status;

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
    /**
     * 盘点类型。
     */
    @TableField("inventory_type")
    private String inventoryType;

    /**
     * 人数
     */
    @TableField(exist = false)
    private Integer count;

    /**
     * 盘点时间类型。
     */
    @TableField("inventory_time_rank")
    private String inventoryTimeRank;

    /**
     * 盘点年份。
     */
    @TableField("inventory_year")
    private String inventoryYear;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}