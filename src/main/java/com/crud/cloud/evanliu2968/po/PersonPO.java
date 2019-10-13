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
 * 人员表。
 *
 * @author guohao.yang
 * @version v1.0.0
 */
@Data
@Accessors(chain = true)
@TableName("person")
public class PersonPO extends Model<PersonPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键。
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 照片。
     */
    private String photoUrl;

    /**
     * 姓名。
     */
    private String name;

    /**
     * 工号。
     */
    private String code;

    /**
     * 证件类型。
     */
    private String certType;

    /**
     * 身份证号。
     */
    private String certId;

    /**
     * 拼音。
     */
    private String spell;

    /**
     * 曾用名。
     */
    private String oldName;

    /**
     * 英文名。
     */
    private String englishName;

    /**
     * 性别（字典表code）。
     */
    private String genderCode;

    /**
     * 出生日期。
     */
    private Date birthday;

    /**
     * 身高。
     */
    private Integer height;

    /**
     * 体重。
     */
    private Double weight;

    /**
     * 国籍(字典表)。
     */
    private String countryCode;

    /**
     * 籍贯(字典表)。
     */
    private String nativePlaceCode;

    /**
     * 籍贯补充。
     */
    private String nativePlaceSuppl;

    /**
     * 民族（字典表）。
     */
    private String nationCode;

    /**
     * 政治面貌（字典表）。
     */
    private String politicsCode;

    /**
     * 婚姻状态（字典表code）。
     */
    private String maritalCode;

    /**
     * 特长专长。
     */
    private String speciality;

    /**
     * 兴趣爱好。
     */
    private String hobby;

    /**
     * 参加工作时间。
     */
    private Date joinWorkTime;

    /**
     * 出生地（字典表）。
     */
    private String birthPlaceCode;

    /**
     * 出生地补充。
     */
    private String birthPlaceSuppl;

    /**
     * 成长地（字典表）。
     */
    private String growPlace;

    /**
     * 成长地补充。
     */
    private String growPlaceSuppl;

    /**
     * 描述。
     */
    private String description;

    /**
     * 入职时间。
     */
    private Date entryDate;

    /**
     * 附件url。
     */
    private String attachmentUrl;

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

    /**
     * 入党时间。
     */
    private Date joinPartyDate;

    /**
     * 职位序列类型（字典表code）。
     */
    private String positionLevelTypeCode;

    /**
     * 职位序列（字典表code）。
     */
    private String positionLevelCode;

    /**
     * ggs。
     */
    private Integer ggs;

    /**
     * 职位族（字典表code）。
     */
    private String positionTypeCode;

    /**
     * 健康状况（字典表code）。
     */
    private String healthCode;

    /**
     * 外派意愿 0 不愿意 1 愿意。
     */
    private Integer assign;

    /**
     * 高风险岗位。
     */
    private String highRiskPost;

    /**
     * 数据来源。
     */
    private String dataFrom;

    /**
     * 同步按钮
     */
    private Boolean syncFlag;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
