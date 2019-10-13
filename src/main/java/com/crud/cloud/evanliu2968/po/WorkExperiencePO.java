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
 * work_experience。
 *
 * @author guohao.yang
 * @version v1.0.0
 */
@Data
@Accessors(chain = true)
@TableName("work_experience")
public class WorkExperiencePO extends Model<WorkExperiencePO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键。
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 依赖人员ID。
     */
    @TableField("person_id")
    private Integer personId;

    /**
     * 开始时间。
     */
    @TableField("begin_date")
    private Date beginDate;

    /**
     * 结束时间。
     */
    @TableField("end_date")
    private Date endDate;

    /**
     * 公司名称。
     */
    @TableField("company_name")
    private String companyName;

    /**
     * 公司规模。
     */
    @TableField("company_scale_code")
    private String companyScaleCode;

    /**
     * 公司性质。
     */
    @TableField("company_industry_code")
    private String companyIndustryCode;

    /**
     * 公司行业。
     */
    @TableField("company_nature_code")
    private String companyNatureCode;

    /**
     * 职位名称。
     */
    @TableField("position_name")
    private String positionName;

    /**
     * 任职部门。
     */
    @TableField("position_dept")
    private String positionDept;

    /**
     * 工作内容。
     */
    @TableField("work_content")
    private String workContent;

    /**
     * 工作类型 （0 非全职 1 全职）。
     */
    @TableField("work_type")
    private Integer workType;

    /**
     * 离职原因。
     */
    @TableField("leave_reason")
    private String leaveReason;

    /**
     * 证明人。
     */
    @TableField("certifier")
    private String certifier;

    /**
     * 证明人电话。
     */
    @TableField("certifier_phone")
    private String certifierPhone;

    private Integer internalFlag;

    private String belongCountry;
    private String belongCity;
    private String mainPerformance;
    private Integer ggs;
    private String postTypeCode;
    private String postLevelCode;

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
    @TableField(value = "version", update = "%s+1")
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