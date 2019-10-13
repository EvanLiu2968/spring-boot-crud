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
 * 考察记录表。
 *
 * @author jinpeng.bu
 * @version v1.0.0
 */
@Data
@Accessors(chain = true)
@TableName("person_inspection")
public class PersonInspectionPO extends Model<PersonInspectionPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键。
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 人员id。
     */
    private Integer personId;

    /**
     * 考察类型。
     */
    private String inspectionType;

    /**
     * 考察时间(日期)。
     */
    private Date inspectionDate;

    /**
     * 考察内容。
     */
    private String inspectionContent;

    /**
     * 考察结论。
     */
    private String inspectionConclusion;

    /**
     * 考察附件url,多个用英文逗号隔开。
     */
    private String inspectionAttachments;

    /**
     * 附件id，多个用英文逗号隔开。
     */
    private String attachmentIds;

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