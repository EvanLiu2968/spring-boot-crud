package com.crud.cloud.evanliu2968.po;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
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
 * 联系信息表。
 *
 * @author guohao.yang
 * @version v1.0.0
 */
@Data
@Accessors(chain = true)
@TableName("contact")
public class ContactPO extends Model<ContactPO> {

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
     * 家庭住址。
     */
    private String homeAddress;

    /**
     * 家庭电话。
     */
    private String homePhone;

    /**
     * 家庭邮政编码 。
     */
    private String homePostalCode;

    /**
     * 工作单位地址。
     */
    private String unitAddress;

    /**
     * 办公电话。
     */
    private String officePhone;

    /**
     * 工作单位邮政编码。
     */
    private String unitPostalCode;

    /**
     * 移动电话。
     */
    private String mobilePhone;

    /**
     * 电子邮箱。
     */
    private String email;

    /**
     * 微信。
     */
    private String wechat;

    /**
     * QQ。
     */
    @TableField(value="QQ")
    private String QQ;

    /**
     * 支付宝。
     */
    private String alipay;

    /**
     * 钉钉。
     */
    private String dingding;

    /**
     * 微博。
     */
    private String microblog;

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

    /**
     * 紧急联系人。
     */
    @ApiModelProperty(value = "紧急联系人")
    private String emergencyContact;

    /**
     * 紧急联系人电话。
     */
    @ApiModelProperty(value = "紧急联系人电话")
    private String emergencyContactPhone;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}