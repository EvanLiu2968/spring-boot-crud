package com.crud.cloud.evanliu2968.po;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 任职表。
 *
 * @author guohao.yang
 * @version v1.0.0
 */
@Data
@Accessors(chain = true)
@TableName("position_info")
public class PositionInfoPO extends Model<PositionInfoPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键。
     */
    private Integer id;

    /**
     * 依赖人员ID。
     */
    private Integer personId;

    /**
     * 部门id。
     */
    private Integer deptId;

    /**
     * 岗位id。
     */
    private Integer postId;

    /**
     * 专任岗位标记。
     */
    private Boolean mainPostFlag;

    /**
     * 岗位级别。
     */
    private Integer postLevel;

    /**
     * 任职方式（1：委派:2：任命）。
     */
    private Integer positionType;

    /**
     * 任职状态（1:挂任，2:试用）。
     */
    private Integer positionStatus;

    /**
     * 开始时间。
     */
    private Date beginDate;

    /**
     * 结束时间。
     */
    private Date endDate;

    /**
     * 职级类型code。
     */
    private String positionLevelTypeCode;

    /**
     * 职级code。
     */
    private String positionLevelCode;

    /**
     * ggs。
     */
    private Integer ggs;

    /**
     * 职位族。
     */
    private String positionTypeCode;

    /**
     * 工作内容。
     */
    private String positionContent;

    /**
     * 任职评价。
     */
    private String comment;

    /**
     * 任职文号。
     */
    private String fileNo;

    /**
     * 批准机关(公司、部门)。
     */
    private String approveUnit;

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

    /**
     * 公司名称。
     */
    private Integer belongCompanyId;
    private Integer belongDeptId;
    private Integer belongGroupId;
    private Integer belongIndustryId;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
