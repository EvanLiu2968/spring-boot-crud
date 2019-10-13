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
 * 专业技能表。
 *
 * @author guohao.yang
 * @version v1.0.0
 */
@Data
@Accessors(chain = true)
@TableName("professional_skill")
public class ProfessionalSkillPO extends Model<ProfessionalSkillPO> {

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
     * 专业技能。
     */
    @TableField("skill")
    private String skill;

    /**
     * 水平。
     */
    @TableField("level")
    private String level;

    /**
     * 获得日期。
     */
    @TableField("receive_date")
    private Date receiveDate;

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

    /**
     * 专业系列
     */
    private String major;

    /**
     * 专业技术职务级别
     */
    private String positionLevel;

    /**
     * 证书编号
     */
    private String certificateNo;
    /**
     * 发证机构
     */
    private String certificateOrgan;

    /**
     * 工作邮箱
     */
    @TableField(exist = false)
    private String workEmail;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}