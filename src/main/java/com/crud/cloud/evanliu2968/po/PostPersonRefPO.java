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
 * 岗位人员关系表。
 *
 * @author auto.crud
 * @version v2.0.0
 */
@Data
@Accessors(chain = true)
@TableName("post_person_ref")
public class PostPersonRefPO extends Model<PostPersonRefPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id。
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 岗位id。
     */
    @TableField("post_id")
    private Integer postId;

    /**
     * 人员id。
     */
    @TableField("person_id")
    private Integer personId;

    /**
     * 继任code。
     */
    @TableField("succession_code")
    private String successionCode;

    @TableField(exist = false)
    private String successionInfo;

    @TableField(exist = false)
    private String postName;

    /**
     * 状态（0-正常添加，1-报告准备添加）。
     */
    @TableField("prepare_flag")
    private Integer prepareFlag;

    /**
     * 状态（0-正常添加，1-盘点会添加）。
     */
    @TableField("inventory_flag")
    private Integer inventoryFlag;

    /**
     * 状态（0-正常添加，1-个人检视添加）。
     */
    @TableField("inspection_flag")
    private Integer inspectionFlag;


    /**
     * 状态（0-部门中，1-继任在该部门）。
     */
    @TableField("status")
    private Integer status;

    /**
     * 修改时间。
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 创建时间。
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 修改人。
     */
    @TableField("update_id")
    private Integer updateId;

    /**
     * 创建人。
     */
    @TableField("create_id")
    private Integer createId;

    /**
     * 备注。
     */
    @TableField("note")
    private String note;

    /**
     * 版本。
     */
    @TableField(value="version", update="%s+1")
    private Integer version;

    /**
     * 0：正常 1：已删除。
     */
    @TableField("del_flag")
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