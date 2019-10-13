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
 * 模型。
 *
 * @author guohao.yang
 * @version v2.0.0
 */
@Data
@Accessors(chain = true)
@TableName("model")
public class ModelPO extends Model<ModelPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键。
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 模型名称。
     */
    private String modelName;

    /**
     * 模型别名。
     */
    private String modelAliasName;

    /**
     * 模型描述。
     */
    private String modelDesc;

    /**
     * 编辑状态（草稿'DRAFT',已完成'FINISHED'）。
     */
    private String editStatus;

    /**
     * 模型顺序。
     */
    private Integer modelNum;

    /**
     * 模型属性。
     */
    private String modelProperty;

    /**
     * 模型层级。
     */
    private String modelLevel;

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