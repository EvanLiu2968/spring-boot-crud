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
 * 任免考察安排。
 *
 * @author auto.crud
 * @version v2.0.0
 */
@Data
@Accessors(chain = true)
@TableName("inspection_schedule")
public class InspectionSchedulePO extends Model<InspectionSchedulePO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键。
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 流程Id。
     */
    @TableField("flow_path_id")
    private Integer flowPathId;

    /**
     * 被访谈人。
     */
    @TableField("interview_people")
    private String interviewPeople;

    /**
     * 职务。
     */
    @TableField("duty")
    private String duty;

    /**
     * 评价关系。
     */
    @TableField("relationship")
    private String relationship;

    /**
     * 联系电话。
     */
    @TableField("tel")
    private String tel;

    /**
     * 邮箱。
     */
    @TableField("email")
    private String email;

    /**
     * 访谈记录。
     */
    @TableField("interview_note")
    private String interviewNote;

    /**
     * 访谈类型。
     */
    @TableField("interview_type")
    private String interviewType;

    /**
     * 访谈地点。
     */
    @TableField("interview_area")
    private String interviewArea;

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
