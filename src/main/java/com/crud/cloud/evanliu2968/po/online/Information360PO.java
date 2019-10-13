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
 * 360信息数据表。
 *
 * @author guohao.yang
 * @version v1.0.0
 */
@Data
@Accessors(chain = true)
@TableName("information_360")
public class Information360PO extends Model<Information360PO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键。
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 人员id。
     */
    private Integer persionId;

    /**
     * 评估关系。
     */
    private String 评估关系;

    /**
     * 完成问卷数。
     */
    private Integer 完成问卷数;

    /**
     * 有效问卷数。
     */
    private Integer 有效问卷数;

    /**
     * 评估人保护。
     */
    private String 评估人保护;

    /**
     * 总分。
     */
    private Double 总分;

    /**
     * 专业业务能力。
     */
    private Double 专业业务能力;

    /**
     * 业绩客观结果。
     */
    private Double 业绩客观结果;

    /**
     * 主观能动性。
     */
    private Double 主观能动性;

    /**
     * 周边价值体现。
     */
    private Double 周边价值体现;

    /**
     * 知识经验积累。
     */
    private Double 知识经验积累;

    /**
     * 通用能力。
     */
    private Double 通用能力;

    /**
     * 团队建设。
     */
    private Double 团队建设;

    /**
     * 沟通协作。
     */
    private Double 沟通协作;

    /**
     * 战略意识。
     */
    private Double 战略意识;

    /**
     * 创新意识。
     */
    private Double 创新意识;

    /**
     * 抗压能力。
     */
    private Double 抗压能力;

    /**
     * 价值观。
     */
    private Double 价值观;

    /**
     * 责任担当意识。
     */
    private Double 责任担当意识;

    /**
     * 事业自驱力。
     */
    private Double 事业自驱力;

    /**
     * 职业品德。
     */
    private Double 职业品德;

    /**
     * 工作价值观。
     */
    private Double 工作价值观;

    /**
     * 象屿精神。
     */
    private Double 象屿精神;

    /**
     * 象屿文化。
     */
    private Double 象屿文化;

    /**
     * 完成时间。
     */
    private String 完成时间;

    /**
     * 区分批次。
     */
    @TableField(value="version", update="%s+1")
    private Integer version;

    @Override
    public String toString() {
        return "Information360PO{" +
                "id=" + id +
                ", persionId=" + persionId +
                ", 评估关系='" + 评估关系 + '\'' +
                ", 完成问卷数=" + 完成问卷数 +
                ", 有效问卷数=" + 有效问卷数 +
                ", 评估人保护='" + 评估人保护 + '\'' +
                ", 总分=" + 总分 +
                ", 专业业务能力=" + 专业业务能力 +
                ", 业绩客观结果=" + 业绩客观结果 +
                ", 主观能动性=" + 主观能动性 +
                ", 周边价值体现=" + 周边价值体现 +
                ", 知识经验积累=" + 知识经验积累 +
                ", 通用能力=" + 通用能力 +
                ", 团队建设=" + 团队建设 +
                ", 沟通协作=" + 沟通协作 +
                ", 战略意识=" + 战略意识 +
                ", 创新意识=" + 创新意识 +
                ", 抗压能力=" + 抗压能力 +
                ", 价值观=" + 价值观 +
                ", 责任担当意识=" + 责任担当意识 +
                ", 事业自驱力=" + 事业自驱力 +
                ", 职业品德=" + 职业品德 +
                ", 工作价值观=" + 工作价值观 +
                ", 象屿精神=" + 象屿精神 +
                ", 象屿文化=" + 象屿文化 +
                ", 完成时间='" + 完成时间 + '\'' +
                ", version=" + version +
                '}';
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}