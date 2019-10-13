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
 * 奖惩信息表。
 *
 * @author guohao.yang
 * @version v1.0.0
 */
@Data
@Accessors(chain = true)
@TableName("award_punishment")
public class AwardPunishmentPO extends Model<AwardPunishmentPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键。
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 依赖人员ID。
     */
    private Integer personId;

    /**
     * 类别 0惩罚 1奖励。
     */
    private Integer apType;

    /**
     * 奖惩级别（公司自定义 例如集团  公司 其他）。
     */
    private String apLevelCode;

    /**
     * 奖惩名称。
     */
    private String apName;

    /**
     * 奖惩时间。
     */
    private Date apDate;

    /**
     * 奖惩说明。
     */
    private String apDesc;
    /**
     * 奖励项
     */
    private String rewardItem;
    /**
     * 违规项
     */
    private String punishmentItem;
    /**
     * 开始日期
     */
    private Date beginDate;
    /**
     * 结束日期
     */
    private Date endDate;

    /**
     * 影响期。
     */
    private Date effectPeriod;

    /**
     * 撤销日期。
     */
    private Date cancleDate;

    /**
     * 文件号。
     */
    private String fileNo;

    /**
     * 批准机关(公司)。
     */
    private String approveUnit;

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