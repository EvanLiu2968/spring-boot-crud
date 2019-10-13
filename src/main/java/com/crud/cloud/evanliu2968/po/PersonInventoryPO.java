package com.crud.cloud.evanliu2968.po;

import com.baomidou.mybatisplus.activerecord.Model;
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
 * person_inventory。
 *
 * @author jinpeng.bu
 * @version v1.0.0
 */
@Data
@Accessors(chain = true)
@TableName("person_inventory")
public class PersonInventoryPO extends Model<PersonInventoryPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键。
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 人员id。
     */
    private Integer personId;

    /**
     * 盘点类型，年度、半年度、季度、月份、项目。
     */
    private String inventoryType;

    /**
     * 盘点年份。
     */
    private String inventoryYear;

    /**
     * 盘点时间类型。
     */
    private String inventoryTimeRank;

    /**
     * 盘点时间。
     */
    private Date inventoryPeriod;

    /**
     * 绩效考核结论。
     */
    private String performanceConclusion;

    /**
     * 潜力。
     */
    private String potential;

    /**
     * 发展任用建议。
     */
    private String suggestion;

    /**
     * 个人发展意愿。
     */
    private String willing;

    /**
     * 胜任力与个性关键发现。
     */
    private String keyFindings;

    /**
     * 其他关键发现。
     */
    private String otherFindings;

    /**
     * 继任建议。
     */
    private String advice;

    /**
     * 继任准备度。
     */
    private String readiness;

    /**
     * 风险度(类型)。
     */
    private String riskTypeCode;

    /**
     * 风险度(级别)。
     */
    private String riskLevelCode;

    /**
     * 综合推荐度。
     */
    private String recommendation;

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