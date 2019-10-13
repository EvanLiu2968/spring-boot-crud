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
 * 培训经历表。
 *
 * @author guohao.yang
 * @version v1.0.0
 */
@Data
@Accessors(chain = true)
@TableName("training")
public class TrainingPO extends Model<TrainingPO> {

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
     * 开始时间。
     */
    private Date beginDate;

    /**
     * 结束时间。
     */
    private Date endDate;

    /**
     * 培训时长天。
     */
    private Integer day;

    /**
     * 培训时长时。
     */
    private Integer hour;

    /**
     * 培训机构。
     */
    private String trainingOrgan;

    /**
     * 培训项目。
     */
    private String trainingProject;

    /**
     * 培训形式。
     */
    private String trainingForms;
    /**
     * 学员考核得分
     */
    private Double assessmentScore;
    /**
     * 培训内容。
     */
    private String trainingContent;

    /**
     * 所获证书。
     */
    private String certificate;

    /**
     * 证书颁发机构。
     */
    private String certificateOrgan;

    /**
     * 掌握程度。
     */
    private String masterLevelCode;

    @TableField(exist = false)
    private String masterLevelName;

    /**
     * 证明人。
     */
    private String certifier;

    /**
     * 证明人电话。
     */
    private String certifierPhone;

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