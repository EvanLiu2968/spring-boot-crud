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
 * 专业经验信息表。
 *
 * @author jinpeng.bu
 * @version v1.0.0
 */
@Data
@Accessors(chain = true)
@TableName("professional_experience")
public class ProfessionalExperiencePO extends Model<ProfessionalExperiencePO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id。
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 人才ID。
     */
    private Integer personId;

    /**
     * 开始时间。
     */
    private Date experienceBegin;

    /**
     * 结束时间。
     */
    private Date experienceEnd;

    /**
     * 专业经验类别。
     */
    private String experienceType;

    /**
     * 专业经验年限。
     */
    private String experienceYears;

    /**
     * 专业经验说明。
     */
    private String experienceNote;

    /**
     * 创建时间。
     */
    private Date createTime;

    /**
     * 修改时间。
     */
    private Date updateTime;

    /**
     * 创建人。
     */
    private Integer createId;

    /**
     * 修改人。
     */
    private Integer updateId;

    /**
     * 0：正常 1：已删除。
     */
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