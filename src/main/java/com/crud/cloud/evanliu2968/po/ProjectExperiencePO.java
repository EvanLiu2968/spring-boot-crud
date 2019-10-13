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
 * 项目经历。
 *
 * @author guohao.yang
 * @version v1.0.0
 */
@Data
@Accessors(chain = true)
@TableName("project_experience")
public class ProjectExperiencePO extends Model<ProjectExperiencePO> {

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


    private String companyName;

    /**
     * 开始时间。
     */
    private Date beginDate;

    /**
     * 结束时间。
     */
    private Date endDate;

    /**
     * 项目名称。
     */
    private String projectName;

    /**
     * 项目描述。
     */
    private String projectDesc;

    /**
     * 责任描述。
     */
    private String dutyDesc;

    /**
     * 项目成就。
     */
    private String projectAchievement;

    /**
     * 项目链接。
     */
    private String projectUrl;

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
    @TableField(value = "version", update = "%s+1")
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