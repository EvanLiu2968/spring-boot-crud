package com.crud.cloud.evanliu2968.po;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * 部门岗位关联表。
 *
 * @author guohao.yang
 * @version v1.0.0
 */
@Data
@Accessors(chain = true)
@TableName("dept_post_ref")
public class DeptPostRefPO extends Model<DeptPostRefPO> {

    private static final long serialVersionUID = 1L;

    /**
     * id。
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 部门主表id。
     */
    private Integer departId;

    /**
     * 岗位id。
     */
    private Integer postId;

    /**
     * 状态 0 无效 1 有效。
     */
    private Integer status;

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
    private Long updateId;

    /**
     * 创建人。
     */
    private Integer createId;

    /**
     * 备注。
     */
    private String note;

    /**
     * 岗位名
     */
    @TableField(exist = false)
    private String postName;

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