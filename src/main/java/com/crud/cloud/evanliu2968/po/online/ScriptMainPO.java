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
 * 人员信息表。
 *
 * @author guohao.yang
 * @version v1.0.0
 */
@Data
@Accessors(chain = true)
@TableName("script_main")
public class ScriptMainPO extends Model<ScriptMainPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键。
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名。
     */
    private String 姓名;

    /**
     * 邮箱。
     */
    private String 邮箱;

    /**
     * 手机号。
     */
    private String 手机;

    /**
     * 性别。
     */
    private String 性别;

    /**
     * 所在公司。
     */
    private String 所在公司;

    /**
     * 人员标签。
     */
    private String 人员标签;

    /**
     * 区分批次。
     */
    @TableField(value="version", update="%s+1")
    private Integer version;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}