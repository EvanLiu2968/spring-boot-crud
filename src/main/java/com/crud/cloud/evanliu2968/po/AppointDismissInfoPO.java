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
 * 任免调岗信息表。
 *
 * @author guohao.yang
 * @version v3.0
 */
@Data
@Accessors(chain = true)
@TableName("appoint_dismiss_info")
public class AppointDismissInfoPO extends Model<AppointDismissInfoPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 任免调岗记录主键。
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
     * 任免信息子类型。
     */
    @TableField("ad_sub_type")
    private String adSubType;

    /**
     * 任免类型描述。
     */
    @TableField("ad_sub_type_desc")
    private String adSubTypeDesc;

    /**
     * 发文文号。
     */
    @TableField("ad_doc_num")
    private String adDocNum;

    /**
     * 变动前岗位。
     */
    @TableField("ad_pre_post")
    private String adPrePost;

    /**
     * 变动后岗位。
     */
    @TableField("ad_after_post")
    private String adAfterPost;

    /**
     * 岗位变动类型。
     */
    @TableField("ad_change_type")
    private String adChangeType;

    /**
     * 岗位变动类型描述。
     */
    @TableField("ad_change_type_desc")
    private String adChangeTypeDesc;

    /**
     * 考察期至。
     */
    @TableField("inspect_period_to")
    private Date inspectPeriodTo;

    /**
     * 考察记录。
     */
    @TableField("inspect_record")
    private String inspectRecord;

    /**
     * 发文业务中心。
     */
    @TableField("ad_business_center")
    private String adBusinessCenter;

    /**
     * 发文业务中心描述。
     */
    @TableField("ad_business_center_desc")
    private String adBusinessCenterDesc;

    /**
     * 发文部门。
     */
    @TableField("ad_department")
    private String adDepartment;

    /**
     * 变动前职务序列。
     */
    @TableField("ad_prePostSeq")
    private String adPrePostSeq;

    /**
     * 变动后职务序列。
     */
    @TableField("ad_afterPostSeq")
    private String adAfterPostSeq;

    /**
     * 变动前职务。
     */
    @TableField("ad_preJob")
    private String adPreJob;

    /**
     * 变动后职务。
     */
    @TableField("ad_afterJob")
    private String adAfterJob;

    /**
     * 职衔。
     */
    @TableField("ad_post_title")
    private String adPostTitle;

    /**
     * 职衔描述。
     */
    @TableField("ad_post_title_desc")
    private String adPostTitleDesc;

    /**
     * 管理人员类型。
     */
    @TableField("ad_adminPerson_type")
    private String adAdminPersonType;

    /**
     * 管理人员类型描述。
     */
    @TableField("ad_adminPerson_type_desc")
    private String adAdminPersonTypeDesc;

    /**
     * 是否重新计算岗龄：1-是 0-否。
     */
    @TableField("is_reCul_work_years")
    private Integer isReCulWorkYears;

    /**
     * 数据来源：GROUP-集团 SDBG-电子。
     */
    @TableField("data_source")
    private String dataSource;

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