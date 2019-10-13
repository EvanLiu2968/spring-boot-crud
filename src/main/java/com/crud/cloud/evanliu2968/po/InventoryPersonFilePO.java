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
 * 盘点人员附件。
 *
 * @author guohao.yang
 * @version v3.1
 */
@Data
@Accessors(chain = true)
@TableName("inventory_person_file")
public class InventoryPersonFilePO extends Model<InventoryPersonFilePO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id。
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 盘点任务id。
     */
    @TableField("inventory_job_id")
    private Integer inventoryJobId;

    /**
     * 任务日程id。
     */
    @TableField("job_schedule_id")
    private Integer jobScheduleId;

    /**
     * 人员id。
     */
    @TableField("person_id")
    private Integer personId;

    @TableField("dept_id")
    private Integer deptId;

    /**
     * 附件id。
     */
    @TableField("file_id")
    private Integer fileId;

    /**
     * 报告准备flag。
     */
    @TableField("prepare_flag")
    private Integer prepareFlag;

    /**
     * 盘点会flag。
     */
    @TableField("inventory_flag")
    private Integer inventoryFlag;

    /**
     * 个人检视flag。
     */
    @TableField("inspection_flag")
    private Integer inspectionFlag;

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
