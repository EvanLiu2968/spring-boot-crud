package com.crud.cloud.evanliu2968.po;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * 新任基本信息。
 *
 * @author auto.crud
 * @version v2.0.0
 */
@Data
@Accessors(chain = true)
@TableName("incumbent_info")
public class IncumbentInfoPO extends Model<IncumbentInfoPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键。
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 流程Id。
     */
    @TableField("flow_path_id")
    private Integer flowPathId;
    /**
     * 新任人-id。
     */
    @TableField("proposed_id")
    private Integer proposedId;
    /**
     * 姓名。
     */
    @TableField("person_name")
    private String personName;

    /**
     * 新任人-所属集团。
     */
    @ApiModelProperty("group_name_proposed")
    private String groupNameProposed;
    /**
     * 新任人-所属部门。
     */
    @ApiModelProperty("dept_name_proposed")
    private String deptNameProposed;
    /**
     * 所属产业名称。
     */
    @TableField("industry_name")
    private String industryName;

    /**
     * 所属企业名称。
     */
    @TableField("company_name")
    private String companyName;

    /**
     * 职位名称。
     */
    @TableField("post_name")
    private String postName;

    /**
     * 任职发文时间。
     */
    @TableField("incumbent_doc_time")
    private Date incumbentDocTime;

    /**
     * 任职日期。
     */
    @TableField("expire_time")
    private Date expireTime;

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
    @TableField(value="version", update="%s+1")
    private Integer version;

    /**
     * 0：正常 1：已删除。
     */
    @TableField("del_flag")
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
