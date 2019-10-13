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
 * 学历学位表。
 *
 * @author guohao.yang
 * @version v1.0.0
 */
@Data
@Accessors(chain = true)
@TableName("degree")
public class DegreePO extends Model<DegreePO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键。
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 依赖人员ID。
     */
    private Integer personId;

    /**
     * 学校名称。
     */
    private String schoolName;

    /**
     * 院系名称。
     */
    private String department;

    /**
     * 专业名称。
     */
    private String major;

    /**
     * 学 制（年）。
     */
    private Double schoolYear;

    /**
     * 入学日期。
     */
    private Date beginDate;

    /**
     * 毕业日期。
     */
    private Date endDate;

    /**
     * 入学年份
     */
    @TableField(exist = false)
    private String beginYear;

    /**
     * 毕业年份
     */
    @TableField(exist = false)
    private String endYear;

    /**
     * 学历 (大专 本科 硕士 博士 其他)。
     */
    private String academicCode;

    @TableField(exist = false)
    private String academicName;

    /**
     * 专业类型code。
     */
    private String majorTypeCode;

    /**
     * 是否全日制（0 非全日制 1全日制）。
     */
    private Integer fullTimeFlag;

    /**
     * 是否学历最高 （0否 1是）。
     */
    private Integer highestAcademicFlag;

    /**
     * 是否第一学历 （0否 1是）。
     */
    private Integer firstDegreeFlag;

    private String studyForm;
    private String retrieveChannel;
    private String academicDegree;
    private String majorSecond;
    private String firstDegreeType;

    /**
     * 修改时间。
     */
    private Date updateTime;

    /**
     * 创建时间。
     */
    private Date createTime;

    /**
     * 修改人。
     */
    private Integer updateId;

    /**
     * 创建人。
     */
    private Integer createId;

    /**
     * 备注。
     */
    private String note;

    /**
     * 版本。
     */
    @TableField(value="version", update="%s+1")
    private Integer version;

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