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
 * 岗位信息。
 *
 * @author guohao.yang
 * @version v1.0.0
 */
@Data
@Accessors(chain = true)
@TableName("post")
public class PostPO extends Model<PostPO> {

    private static final long serialVersionUID = 1L;

    /**
     * id。
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 岗位编码
     */
    private String postCode;

    /**
     * 岗位名称。
     */
    private String name;

    /**
     * 岗位全称。
     */
    private String fullName;

    /**
     * 岗位类别。
     */
    private String type;

    /**
     * 岗位层级。
     */
    private Integer level;

    /**
     * 描述信息。
     */
    private String description;

    /**
     * 生效时间。
     */
    private String timeBegin;

    /**
     * 失效时间。
     */
    private String timeEnd;

    /**
     * 空缺时间。
     */
    private String vacancyTime;

    private Integer ggs;
    private String postTypeCode;
    private String postLevelTypeCode;
    private String postLevelCode;
    private String postUrgentCode;

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