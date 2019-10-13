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
 * 流程节点。
 *
 * @author auto.crud
 * @version v2.0.0
 */
@Data
@Accessors(chain = true)
@TableName("flow_path_node")
public class FlowPathNodePO extends Model<FlowPathNodePO> {

    private static final long serialVersionUID = 1L;

    /**
     * 流程节点Id。
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 流程模板Id。
     */
    @TableField("flow_path_template_id")
    private Integer flowPathTemplateId;

    /**
     * 流程模板节点Id。
     */
    @TableField("flow_path_template_node_id")
    private Integer flowPathTemplateNodeId;

    /**
     * 流程Id。
     */
    @TableField("flow_path_id")
    private Integer flowPathId;

    /**
     * 流程类型。
     */
    @TableField("flow_path_type")
    private String flowPathType;

    /**
     * 节点名称。
     */
    @TableField("node_name")
    private String nodeName;
    /**
     * 节点类型。
     */
    @TableField("node_Type")
    private String nodeType;

    /**
     * 紧急程度。
     */
    @TableField("urgency")
    private String urgency;

    /**
     * 审批人。
     */
    @TableField("approval_person")
    private Integer approvalPerson;

    /**
     * 审批时间。
     */
    @TableField("approval_date")
    private Date approvalDate;

    /**
     * 审批结果。
     */
    @TableField("approval_result")
    private String approvalResult;

    /**
     * 审批备注。
     */
    @TableField("approval_remark")
    private String approvalRemark;

    /**
     * 审批状态。
     */
    @TableField("approval_status")
    private String approvalStatus;
    /**
     * 任务启动时间。
     */
    @TableField("task_start_time")
    private Date taskStartTime;
    /**
     * 任务提醒时间。
     */
    @TableField("task_remind_time")
    private Date taskRemindTime;
    /**
     * 任务提醒频率。
     */
    @TableField("task_remind_frequency")
    private String taskRemindFrequency;
    /**
     * 颜色号。
     */
    @TableField("color_number")
    private String colorNumber;
    /**
     * 发文号。
     */
    @TableField("post_uuid")
    private String postUuid;

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
