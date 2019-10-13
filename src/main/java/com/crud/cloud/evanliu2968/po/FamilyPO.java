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
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Date;

/**
 * 家庭信息。
 *
 * @author guohao.yang
 * @version v1.0.0
 */
@Data
@Accessors(chain = true)
@TableName("family")
public class FamilyPO extends Model<FamilyPO> {

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
     * 关系（配偶、父亲、母亲、儿子、女儿、祖父、祖母、孙子、孙女）。
     */
    private String relationCode;

    /**
     * 姓名。
     */
    private String name;

    /**
     * 证件类型。
     */
    private String certType;

    /**
     * 证件号码。
     */
    private String certId;

    /**
     * 性别。
     */
    private String genderCode;

    /**
     * 国籍(字典表)。
     */
    private String countryCode;

    /**
     * 政治面貌（字典表）。
     */
    private String politicsCode;

    /**
     * 学历 (大专 本科 硕士 博士 其他)。
     */
    private String academicCode;

    /**
     * 民族（字典表）。
     */
    private String nationCode;

    /**
     * 出生日期。
     */
    private Date birthday;

    /**
     * 工作单位及职务。
     */
    private String unitPosition;

    /**
     * 联系电话。
     */
    private String cellphone;

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
     * 紧急联系人
     */
    private String emergencyContact;

    /**
     * 详细地址
     */
    private String detailAddress;
    /**
     * 邮政编码
     */
    private String postalCode;
    /**
     * 工作邮箱
     */
    @TableField(exist = false)
    private String workEmail;



    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}