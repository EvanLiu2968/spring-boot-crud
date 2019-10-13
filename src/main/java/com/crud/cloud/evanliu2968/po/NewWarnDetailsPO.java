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
 * 新预警方案表。
 *
 * @author guohao.yang
 * @version v1.0.0
 */
@Data
@Accessors(chain = true)
@TableName("new_warn_details")
public class NewWarnDetailsPO extends Model<NewWarnDetailsPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键。
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 维度。
     */
    private String dimension;

    /**
     * 预警方式(按比例,按数量)。
     */
    private String warnWay;

    /**
     * 比较规则(eq:等于 ne:不等于 gt:大于 ge:大于等于 lt:小于 le:小于等于)等等。
     */
    private String rule;

    /**
     * 比较值一。
     */
    private Integer compareValueOne;

    /**
     * 预警规则。
     */
    private String warnRule;

    /**
     * 预警规则数值。
     */
    private Integer warnRuleValue;

    /**
     * 文案。
     */
    private String copy;

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
     * 0：显示 1：不显示。
     */
    private Integer showFlag;

    /**
     * 预警通过文案。
     */
    private String throughCopy;

    /**
     * 预警不通过文案。
     */
    private String notThroughCopy;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}