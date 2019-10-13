package com.crud.cloud.evanliu2968.po.online;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 在线测评模板。
 *
 * @author guohao.yang
 * @version v1.0.0
 */
@Data
@Accessors(chain = true)
@TableName("online_assessment")
public class OnlineAssessmentPO extends Model<OnlineAssessmentPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键。
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 组织。
     */
    private String 组织;

    /**
     * 姓名。
     */
    private String 姓名;

    /**
     * 360调查专业业绩加权得分。
     */
    private String 业绩得分;

    /**
     * 360调查专业业绩得分最高角色。
     */
    private String 业绩得分最高;

    /**
     * 360调查专业业绩得分最低角色。
     */
    private String 业绩得分最低;

    /**
     * 360调查专业业绩方面高分一项。
     */
    private String 业绩高分;

    /**
     * 360调查专业业绩方面低分一项。
     */
    private String 业绩低分;

    /**
     * 360调查通用能力加权得分。
     */
    private String 通用能力得分;

    /**
     * 360调查通用能力得分最高角色。
     */
    private String 通用能力得分最高;

    /**
     * 360调查通用能力得分最低角色（潜能表）。
     */
    private String 通用能力得分最低;

    /**
     * 360调查通用能力方面高分二项（6选2）。
     */
    private String 通用能力高分二项;

    /**
     * 360调查通用能力方面低分二项。
     */
    private String 通用能力低分二项;

    /**
     * 综合能力分。
     */
    private String 综合能力分;

    /**
     * 能力项前三条。
     */
    private String 能力项前三条;

    /**
     * 能力行为点高分三条。
     */
    private String 行为点高分三条;

    /**
     * 能力项后三条。
     */
    private String 能力项后三条;

    /**
     * 能力行为点后三条。
     */
    private String 能力行为点后三条;

    /**
     * 大五高分两项。
     */
    private String 大五高分两项;

    /**
     * 大五低分两项。
     */
    private String 大五低分两项;

    /**
     * 擅长的工作环。
     */
    private String 擅长的工作环;

    /**
     * 不擅长的工作环。
     */
    private String 不擅长的工作环;

    /**
     * 驱动因素。
     */
    private String 驱动因素;

    /**
     * 区分批次。
     */
    @TableField(value="version", update="%s+1")
    private Integer version;

    @Override
    public String toString() {
        return "OnlineAssessmentPO{" +
                "id=" + id +
                ", 组织='" + 组织 + '\'' +
                ", 姓名='" + 姓名 + '\'' +
                ", 业绩得分='" + 业绩得分 + '\'' +
                ", 业绩得分最高='" + 业绩得分最高 + '\'' +
                ", 业绩得分最低='" + 业绩得分最低 + '\'' +
                ", 业绩高分='" + 业绩高分 + '\'' +
                ", 业绩低分='" + 业绩低分 + '\'' +
                ", 通用能力得分='" + 通用能力得分 + '\'' +
                ", 通用能力得分最高='" + 通用能力得分最高 + '\'' +
                ", 通用能力得分最低='" + 通用能力得分最低 + '\'' +
                ", 通用能力高分二项='" + 通用能力高分二项 + '\'' +
                ", 通用能力低分二项='" + 通用能力低分二项 + '\'' +
                ", 综合能力分='" + 综合能力分 + '\'' +
                ", 能力项前三条='" + 能力项前三条 + '\'' +
                ", 行为点高分三条='" + 行为点高分三条 + '\'' +
                ", 能力项后三条='" + 能力项后三条 + '\'' +
                ", 能力行为点后三条='" + 能力行为点后三条 + '\'' +
                ", 大五高分两项='" + 大五高分两项 + '\'' +
                ", 大五低分两项='" + 大五低分两项 + '\'' +
                ", 擅长的工作环='" + 擅长的工作环 + '\'' +
                ", 不擅长的工作环='" + 不擅长的工作环 + '\'' +
                ", 驱动因素='" + 驱动因素 + '\'' +
                ", version=" + version +
                '}';
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}