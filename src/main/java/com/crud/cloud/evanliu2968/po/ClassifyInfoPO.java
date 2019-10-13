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
import java.util.List;

/**
 * 人员分类表。
 *
 * @author guohao.yang
 * @version v1.0.0
 */
@Data
@Accessors(chain = true)
@TableName("classify_info")
public class ClassifyInfoPO extends Model<ClassifyInfoPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键。
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父id。
     */
    private Integer parentId;

    /**
     * 人员类别名称。
     */
    private String name;

    /**
     * 级别
     */
    private String level;

    /**
     * 类型。见枚举类--PersonClassifyEnum
     */
    private Integer type;

    /**
     * 0-不限制条件，1-限制入库条件。
     */
    private Integer limitFlag;

    /**
     * 参考的画像 id。
     */
    private Integer imageId;

    /**
     * 画像/人才库 配置主表id。
     */
    private Integer portraitConfigId;

    /**
     * 封面url。
     */
    private String coverUrl;

    /**
     * 置顶  1-置顶  0-非置顶。
     */
    private Integer topFlag;

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

    private Integer sort;

    @TableField(exist = false)
    private List<ClassifyInfoPO> children;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}