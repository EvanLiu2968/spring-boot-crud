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
 * 字典明细表。
 *
 * @author guohao.yang
 * @version v1.0.0
 */
@Data
@Accessors(chain = true)
@TableName("dict")
public class DictPO extends Model<DictPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键。
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 类型id。
     */
    private Integer dictTypeId;

    @TableField(exist = false)
    private String dictTypeCode;

    /**
     * 父id。
     */
    private Integer parentId;

    /**
     * 代码。
     */
    private String code;

    /**
     * 名称。
     */
    private String name;

    /**
     * 排序。
     */
    private Integer sort;

    /**
     * 是否显示。
     */
    private Integer showFlag;

    /**
     * 类型（0-初始化的字典项  1-动态生成的字典项）。
     */
    private Integer type;

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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}