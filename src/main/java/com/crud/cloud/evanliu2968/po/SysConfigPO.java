package com.crud.cloud.evanliu2968.po;

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
import java.util.Date;

/**
 * sys_config。
 *
 * @author guohao.yang
 * @version v1.0.0
 */
@Data
@Accessors(chain = true)
@TableName("sys_config")
public class SysConfigPO extends Model<SysConfigPO> {

    private static final long serialVersionUID = 1L;

    /**
     * variable。
     */
    @TableId(value = "variable", type = IdType.AUTO)
    private String variable;

    /**
     * value。
     */
    private String value;

    /**
     * set_time。
     */
    private Date setTime;

    /**
     * set_by。
     */
    private String setBy;

    @Override
    protected Serializable pkVal() {
        return this.variable;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}