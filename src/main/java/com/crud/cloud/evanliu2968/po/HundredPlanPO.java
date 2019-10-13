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
 * 百日计划。
 *
 * @author auto.crud
 * @version v2.0.0
 */
@Data
@Accessors(chain = true)
@TableName("hundred_plan")
public class HundredPlanPO extends Model<HundredPlanPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键。
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 流程节点id。
     */
    @TableField("flow_path_node_id")
    private Integer flowPathNodeId;

    /**
     * 工作主题。
     */
    @TableField("work_subject")
    private String workSubject;

    /**
     * 工作内容。
     */
    @TableField("work_content")
    private String workContent;

    /**
     * 时间安排。
     */
    @TableField("time_plan")
    private String timePlan;

    /**
     * 预定目标。
     */
    @TableField("intended_target")
    private String intendedTarget;

    /**
     * 达成情况。
     */
    @TableField("goal")
    private String goal;

    /**
     * 自评。
     */
    @TableField("self_comment")
    private String selfComment;

    /**
     * 通知发送状态。
     */
    @TableField("leader_suggest")
    private String leaderSuggest;

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