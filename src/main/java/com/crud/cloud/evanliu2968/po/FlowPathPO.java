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
 * 流程。
 *
 * @author auto.crud
 * @version v2.0.0
 */
@Data
@Accessors(chain = true)
@TableName("flow_path")
public class FlowPathPO extends Model<FlowPathPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 流程Id。
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 流程模板Id。
     */
    @TableField("flow_path_template_id")
    private Integer flowPathTemplateId;

    /**
     * 流程名称。
     */
    @TableField("flow_path_name")
    private String flowPathName;

    /**
     * 流程类型。
     */
    @TableField("flow_path_type")
    private String flowPathType;
    /**
     * 流程状态：JXZ:进行中,YWC:已完成。
     */
    @TableField("flow_path_status")
    private String flowPathStatus;
    /**
     * 任务名称。
     */
    @TableField("service_name")
    private String serviceName;

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
