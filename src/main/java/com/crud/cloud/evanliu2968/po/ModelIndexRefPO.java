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
 * 模型指标。
 *
 * @author guohao.yang
 * @version v2.0.0
 */
@Data
@Accessors(chain = true)
@TableName("model_index_ref")
public class ModelIndexRefPO extends Model<ModelIndexRefPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键。
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 指标id。
     */
    private Integer indexId;

    /**
     * 模型id。
     */
    private Integer modelId;

    /**
     * 父指标id。
     */
    private Integer parentIndexId;

    /**
     * 指标权重。
     */
    private Double indexWeight;

    /**
     * 指标别名。
     */
    private String indexAliasName;

    /**
     * 指标描述。
     */
    private String indexAliasDesc;

    /**
     * 指标层级。
     */
    private Integer indexLevel;

    /**
     * 指标顺序。
     */
    private Integer indexSort;

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