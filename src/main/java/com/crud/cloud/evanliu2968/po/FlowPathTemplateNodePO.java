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
 * 流程模板节点。
 *
 * @author auto.crud
 * @version v2.0.0
 */
@Data
@Accessors(chain = true)
@TableName("flow_path_template_node")
public class FlowPathTemplateNodePO extends Model<FlowPathTemplateNodePO> {

    private static final long serialVersionUID = 1L;

    /**
     * 流程模板节点Id。
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 流程模板Id。
     */
    @TableField("flow_path_template_id")
    private Integer flowPathTemplateId;
    /**
     * 流程类型。
     */
    @TableField("flow_path_type")
    private String flowPathType;
    /**
     * 流程模板节点名称。
     */
    @TableField("node_name")
    private String nodeName;

    /**
     * 流程模板节点类型。
     */
    @TableField("node_type")
    private String nodeType;

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

    /**
     * 排序。
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 流程模板状态:启用1，未启用0。
     */
    @TableField("status")
    private Integer status;

    /**
     * 流程节点启动时间。
     */
    @TableField("process_node_start_time")
    private Integer processNodeStartTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
