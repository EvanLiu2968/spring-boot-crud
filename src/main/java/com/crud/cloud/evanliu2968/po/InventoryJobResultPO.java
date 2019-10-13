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
 * 盘点结论。
 *
 * @author guohao.yang
 * @version v3.0
 */
@Data
@Accessors(chain = true)
@TableName("inventory_job_result")
public class InventoryJobResultPO extends Model<InventoryJobResultPO> {

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

    /**
     * 绩效code。
     */
    @TableField("performance_code")
    private String performanceCode;

    /**
     * 潜力code。
     */
    @TableField("potential_code")
    private String potentialCode;

    /**
     * 发展任用建议。
     */
    @TableField("develop_advise")
    private String developAdvise;

    /**
     * 个人发展意愿。
     */
    @TableField("pensonal_willing")
    private String pensonalWilling;

    /**
     * 胜任力与个性关键发现。
     */
    @TableField("qualified_info")
    private String qualifiedInfo;

    /**
     * 其他关键发现。
     */
    @TableField("other_key_info")
    private String otherKeyInfo;

    /**
     * 岗位紧迫度。
     */
    @TableField("post_urgent_code")
    private String postUrgentCode;

    /**
     * 综合推荐度（字典项）。
     */
    @TableField("recommend_code")
    private String recommendCode;

    /**
     * 盘点结论。
     */
    @TableField("conclusion")
    private String conclusion;

    //------------- v3.1 新增标记 -------------
    @TableField("prepare_flag")
    private Integer prepareFlag;

    @TableField("inventory_flag")
    private Integer inventoryFlag;

    @TableField("inspection_flag")
    private Integer inspectionFlag;
    //---------------------------------------

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
