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
 * 绩效表。
 *
 * @author guohao.yang
 * @version v1.0.0
 */
@Data
@Accessors(chain = true)
@TableName("performance")
public class PerformancePO extends Model<PerformancePO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键。
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 人员主键。
     */
    private Integer personId;

    /**
     * 部门Id
     */
    private Integer deptId;

    /**
     * 岗位Id
     */
    private Integer postId;

    /**
     * 考核年度。
     */
    private Integer checkYear;

    /**
     * 考核类别。
     */
    private String type;

    /**
     * 直接上级。
     */
    private Integer directManage;

    /**
     * 权重百分比(%)。
     */
    private Integer percent;

    /**
     * 绩效等级。
     */
    private String levelCode;

    /**
     * 考核时间。
     */
    private Date checkTime;

    /**
     * 附件。
     */
    private String attachmentUrl;
    /**
     * 绩效表现
     */
    private String performanceAchievements;
    /**
     * 开始时间
     */
    private Date beginDate;
    /**
     * 结束时间
     */
    private Date endDate;
    /**
     * 考评期间类别
     */
    private String periodType;
    /**
     * 考评得分
     */
    private Double performanceScore;
    /**
     * 考评系数
     */
    private Double performanceCoefficient;

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


    /**
     * 绩效考核结论。
     */
    private String conclusion;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}