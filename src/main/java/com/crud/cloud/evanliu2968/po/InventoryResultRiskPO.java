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
 * 盘点结论——风险度。
 *
 * @author auto.crud
 * @version v2.0.0
 */
@Data
@Accessors(chain = true)
@TableName("inventory_result_risk")
public class InventoryResultRiskPO extends Model<InventoryResultRiskPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id。
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 盘点结论id。
     */
    @TableField("job_result_id")
    private Integer jobResultId;

    /**
     * 风险度类型code。
     */
    @TableField("risk_type_code")
    private String riskTypeCode;

    /**
     * 风险度code。
     */
    @TableField("risk_code")
    private String riskCode;

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