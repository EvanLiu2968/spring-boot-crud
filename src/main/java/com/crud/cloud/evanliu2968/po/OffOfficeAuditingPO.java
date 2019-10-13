package com.crud.cloud.evanliu2968.po;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * 离任人离任审计。
 *
 * @author auto.crud
 * @version v2.0.0
 */
@Data
@Accessors(chain = true)
@TableName("off_office_auditing")
public class OffOfficeAuditingPO extends Model<OffOfficeAuditingPO> {

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
     * 流程节点Id。
     */
    @TableField("flow_path_node_id")
    private Integer flowPathNodeId;
    /**
     * 离任人Id。
     */
    @TableField("proposed_id")
    private Integer proposedId;
    /**
     * 免职类型。
     */
    @TableField("remove_type")
    private String removeType;
    /**
     * 离任人-所属产业。
     */
    @TableField("industry_name_proposed")
    private String industryNameProposed;

    /**
     * 离任人-所属企业。
     */
    @TableField("company_name_proposed")
    private String companyNameProposed;
    /**
     * 离任人-所属部门。
     */
    @ApiModelProperty("dept_name_proposed")
    private String deptNameProposed;

    /**
     * 离任人-所属集团。
     */
    @ApiModelProperty("group_name_proposed")
    private String groupNameProposed;
    /**
     * 离任人-姓名。
     */
    @TableField("name_proposed")
    private String nameProposed;

    /**
     * 离任人-现任职位。
     */
    @TableField("post_name_proposed")
    private String postNameProposed;

    /**
     * 离任人-岗位职责。
     */
    @TableField("duty_proposed")
    private String dutyProposed;

    /**
     * 离任人原因。
     */
    @TableField("reason_proposed")
    private String reasonProposed;

    /**
     * 离任审计结果。
     */
    @TableField("audit_opinion_proposed")
    private String auditOpinionProposed;

    /**
     * 离任单位意见。
     */
    @TableField("unit_comment_proposed")
    private String unitCommentProposed;
    /**
     * 签名。
     */
    @TableField("sign_name")
    private String signName;
    /**
     * 签名_日期。
     */
    @TableField("sign_date")
    private Date signDate;

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
     * 节点状态
     */
    @TableField(exist = false)
    private String nodeStatus;

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
