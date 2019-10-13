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
 * 合同信息表。
 *
 * @author jinpeng.bu
 * @version v1.0.0
 */
@Data
@Accessors(chain = true)
@TableName("contract")
public class ContractPO extends Model<ContractPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id。
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 人才ID。
     */
    private Integer personId;

    /**
     * 合同生效日期，时间戳。
     */
    private Date effectiveDate;

    /**
     * 合同失效日期，时间戳。
     */
    private Date expirationDate;

    /**
     * 合同类型。
     */
    private String contractType;

    /**
     * 合同标识。
     */
    private String contractMarking;

    /**
     * 合同变化原因。
     */
    private String changeReason;

    /**
     * 合同备注。
     */
    private String contractNote;

    /**
     * 合同主体。
     */
    private String contractSubject;

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