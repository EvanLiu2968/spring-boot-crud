package com.crud.cloud.evanliu2968.po;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * 任免基础信息。
 *
 * @author auto.crud
 * @version v2.0.0
 */
@Data
@Accessors(chain = true)
@TableName("appoint_remove_base_info")
public class AppointRemoveBaseInfoPO extends Model<AppointRemoveBaseInfoPO> {

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
     * 免职人-id。
     */
    @TableField("appoint_id")
    private Integer appointId;
    /**
     * 免职类型。
     */
    @TableField("remove_type")
    private String removeType;
    /**
     * 是否离任审计。
     */
    @TableField("whether_off_audit")
    private Integer whetherOffAudit;
    /**
     * 是否已经启动离任审计。
     */
    @ApiModelProperty("whether_have_off_audit")
    private Integer whetherHaveOffAudit;
    /**
     * 任免-岗位名称。
     */
    @TableField("appoint_post_name")
    private String appointPostName;

    /**
     * 任免-所属产业。
     */
    @TableField("appoint_industry_name")
    private String appointIndustryName;

    /**
     * 任免-所属企业。
     */
    @TableField("appoint_company_name")
    private String appointCompanyName;

    /**
     * 任免-职位族。
     */
    @TableField("appoint_job_family")
    private String appointJobFamily;

    /**
     * 任免-职位序列。
     */
    @TableField("appoint_post_level")
    private String appointPostLevel;

    /**
     * 任免-GGS。
     */
    @TableField("appoint_ggs")
    private String appointGgs;

    /**
     * 任免-当前任职人。
     */
    @TableField("appoint_current_holder")
    private String appointCurrentHolder;

    /**
     * 任免-任职时间。
     */
    @TableField("appoint_employment_period")
    private Date appointEmploymentPeriod;

    /**
     * 任免-所属部门。
     */
    @TableField("appoint_dept_name")
    private String appointDeptName;

    /**
     * 任免-所属集团。
     */
    @TableField("appoint_group_name")
    private String appointGroupName;

    /**
     * 是否免职。
     */
    @TableField("is_remove")
    private Integer isRemove;
    /**
     * 任职类型。
     */
    @TableField("appoint_type")
    private String appointType;
    /**
     * 提议人-所属产业。
     */
    @TableField("proposed_industry_name")
    private String proposedIndustryName;
  /**
     * 提议人-id。
     */
    @TableField("proposed_id")
    private Integer proposedId;
    /**
     * 提议人-所属企业。
     */
    @TableField("proposed_company_name")
    private String proposedCompanyName;

    /**
     * 提议人-姓名。
     */
    @TableField("proposed_name")
    private String proposedName;

    /**
     * 提议人-现任职位。
     */
    @TableField("proposed_post_name")
    private String proposedPostName;

    /**
     * 提议人-所属集团。
     */
    @TableField("proposed_group_name")
    private String proposedGroupName;

    /**
     * 提议人-所属部门。
     */
    @TableField("proposed_dept_name")
    private String proposedDeptName;

    /**
     * 提议人-职位族。
     */
    @TableField("proposed_job_family")
    private String proposedJobFamily;

    /**
     * 提议人-职位序列。
     */
    @TableField("proposed_post_level")
    private String proposedPostLevel;

    /**
     * 提议人-GGS。
     */
    @TableField("proposed_ggs")
    private String proposedGgs;
    /**
     * 沟通工作是否完成。
     */
    @TableField("communicate_is_completed")
    private Integer communicateIsCompleted;
    /**
     * 拟试用期。
     */
    @TableField("probation_period")
    private Integer probationPeriod;
    /**
     * 是否试用。
     */
    @TableField("whether_the_trial")
    private Integer whetherTheTrial;
    /**
     * 拟任期。
     */
    @TableField("proposed_term")
    private String proposedTerm;
    /**
     *
     * 实试用期。
     */
    @TableField("true_period")
    private Integer truePeriod;

    /**
     * 实任岗位。
     */
    @TableField("true_post_name")
    private String truePostName;
    /**
     * 实任期。
     */
    @TableField("true_term")
    private String trueTerm;
    /**
     * 是否兼职。
     */
    @TableField("whether_the_part_time")
    private Integer whetherThePartTime;
    /**
     * 是否新任五步法。
     */
    @TableField("whether_the_new")
    private Integer whetherTheNew;
    /**
     * 岗位要求。
     */
    @TableField("job_requirements")
    private String jobRequirements;


    /**
     * 提议原因。
     */
    @TableField("reason_proposed")
    private String reasonProposed;

    /**
     * 综合评价。
     */
    @TableField("comprehensive_assessment")
    private String comprehensiveAssessment;

    /**
     * 提议人意见。
     */
    @TableField("proposer_opinion")
    private String proposerOpinion;

    /**
     * 任职发文时间。
     */
    @TableField("incumbent_doc_time")
    private Date incumbentDocTime;
    /**
     * 任职日期。
     */
    @TableField("expire_time")
    private Date expireTime;
    /**
     * 审核时间。
     */
    @TableField("audit_time")
    private Date auditTime;

    /**
     * 提议人签名。
     */
    @TableField("proposed_sign")
    private String proposedSign;

    /**
     * 综合考察结论。
     */
    @TableField("investigation_conclusion")
    private String investigationConclusion;

    /**
     * 审核意见。
     */
    @TableField("audit_opinion")
    private String auditOpinion;
    /**
     * c否已完成发文。
     */
    @TableField("is_complete_post")
    private Integer isCompletePost;

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
