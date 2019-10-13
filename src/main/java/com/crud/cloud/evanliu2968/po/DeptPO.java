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
 * 部门。
 *
 * @author guohao.yang
 * @version v1.0.0
 */
@Data
@Accessors(chain = true)
@TableName("dept")
public class DeptPO extends Model<DeptPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键。
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 部门名称。
     */
    private String name;

    /**
     * 部门简称。
     */
    private String shortName;

    /**
     * 部门编码。
     */
    private String code;

    /**
     * 上级部门id。
     */
    private Integer parentId;

    /**
     * 部门级别。
     */
    private String level;

    /**
     * 部门类型。
     */
    private String departType;

    /**
     * 排序。
     */
    private Integer sort;

    /**
     * 部门邮政编码。
     */
    private String postalCode;

    /**
     * 部门地址。
     */
    private String unitAddress;

    /**
     * 部门电话号码。
     */
    private String phone;

    /**
     * 部门传真号码。
     */
    private String faxNumber;

    /**
     * 部门网址。
     */
    private String webAddress;

    /**
     * 电子信箱。
     */
    private String email;

    /**
     * 部门联系人姓名。
     */
    private String contactName;

    /**
     * 部门联系人电话。
     */
    private String contactPhone;

    /**
     * 简介。
     */
    private String description;

    /**
     * 部门成立日期。
     */
    private String approveDate;

    /**
     * 部门类别（集团：DEPT_GROUP、产业：DEPT_INDUSTRY、公司：DEPT_COMPANY、部门：DEPT_DEPART）。
     */
    private String deptClass;

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
    @TableField(value="version")
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