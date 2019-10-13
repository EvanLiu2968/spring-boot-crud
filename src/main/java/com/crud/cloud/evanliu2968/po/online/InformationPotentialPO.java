package com.crud.cloud.evanliu2968.po.online;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 潜能信息数据表。
 *
 * @author guohao.yang
 * @version v1.0.0
 */
@Data
@Accessors(chain = true)
@TableName("information_potential")
public class InformationPotentialPO extends Model<InformationPotentialPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键。
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 人员id。
     */
    private Integer persionId;

    /**
     * 能力。
     */
    private Double 能力;

    /**
     * 引领变革。
     */
    private Double 引领变革;

    /**
     * 创新意识。
     */
    private Double 创新意识;

    /**
     * 变革管理。
     */
    private Double 变革管理;

    /**
     * 事业企图心。
     */
    private Double 事业企图心;

    /**
     * 成就导向。
     */
    private Double 成就导向;

    /**
     * 结果导向。
     */
    private Double 结果导向;

    /**
     * 坚持不懈。
     */
    private Double 坚持不懈;

    /**
     * 合作共赢。
     */
    private Double 合作共赢;

    /**
     * 善于社交。
     */
    private Double 善于社交;

    /**
     * 换位思考。
     */
    private Double 换位思考;

    /**
     * 客户导向。
     */
    private Double 客户导向;

    /**
     * 愿景激励。
     */
    private Double 愿景激励1;

    /**
     * 愿景激励。
     */
    private Double 愿景激励2;

    /**
     * 说服影响。
     */
    private Double 说服影响;

    /**
     * 经营意识。
     */
    private Double 经营意识;

    /**
     * 确定轻重缓急。
     */
    private Double 确定轻重缓急;

    /**
     * 制定决策。
     */
    private Double 制定决策;

    /**
     * 资源整合。
     */
    private Double 资源整合;

    /**
     * 资源配置。
     */
    private Double 资源配置;

    /**
     * 了解组织运作。
     */
    private Double 了解组织运作;

    /**
     * 战略思维。
     */
    private Double 战略思维;

    /**
     * 商业敏锐度。
     */
    private Double 商业敏锐度;

    /**
     * 前瞻思维。
     */
    private Double 前瞻思维;

    /**
     * 进取性。
     */
    private Double 进取性;

    /**
     * 抱负。
     */
    private Integer 抱负;

    /**
     * 对抗性。
     */
    private Integer 对抗性;

    /**
     * 独立性。
     */
    private Integer 独立性;

    /**
     * 外向性。
     */
    private Double 外向性;

    /**
     * 活力。
     */
    private Integer 活力;

    /**
     * 乐群性。
     */
    private Integer 乐群性;

    /**
     * 社交性。
     */
    private Integer 社交性;

    /**
     * 尽责性。
     */
    private Double 尽责性;

    /**
     * 条理性。
     */
    private Integer 条理性;

    /**
     * 责任感。
     */
    private Integer 责任感;

    /**
     * 精确性。
     */
    private Integer 精确性;

    /**
     * 宜人性。
     */
    private Double 宜人性;

    /**
     * 利他。
     */
    private Integer 利他;

    /**
     * 同理心。
     */
    private Integer 同理心;

    /**
     * 信任。
     */
    private Integer 信任;

    /**
     * 情绪性。
     */
    private Double 情绪性;

    /**
     * 焦虑。
     */
    private Integer 焦虑;

    /**
     * 忧虑。
     */
    private Integer 忧虑;

    /**
     * 敏感。
     */
    private Integer 敏感;

    /**
     * 探索可能。
     */
    private Double 探索可能;

    /**
     * 评估方案。
     */
    private Double 评估方案;

    /**
     * 设定方向。
     */
    private Double 设定方向;

    /**
     * 采取行动。
     */
    private Double 采取行动;

    /**
     * 人际网络。
     */
    private Double 人际网络;

    /**
     * 团队凝聚。
     */
    private Double 团队凝聚;

    /**
     * 交付成果。
     */
    private Double 交付成果;

    /**
     * 压力承受。
     */
    private Double 压力承受;

    /**
     * 答题不一致性。
     */
    private Integer 答题不一致性1;

    /**
     * 大五趋中倾向。
     */
    private Integer 大五趋中倾向;

    /**
     * 社会赞许性。
     */
    private Integer 社会赞许性;

    /**
     * 异常原因。
     */
    private String 异常原因1;

    /**
     * 中断次数。
     */
    private Integer 中断次数1;

    /**
     * 答题时间(分钟)。
     */
    private Double 答题时间1;

    /**
     * 测评完成时间  。
     */
    private String 测评完成时间1;

    /**
     * 积极性。
     */
    private Integer 积极性;

    /**
     * 成就。
     */
    private Integer 成就;

    /**
     * 成长机会。
     */
    private Integer 成长机会;

    /**
     * 多样性。
     */
    private Integer 多样性;

    /**
     * 权力导向。
     */
    private Integer 权力导向;

    /**
     * 竞争。
     */
    private Integer 竞争;

    /**
     * 面对客户。
     */
    private Integer 面对客户;

    /**
     * 商业。
     */
    private Integer 商业;

    /**
     * 获得认同。
     */
    private Integer 获得认同;

    /**
     * 人际互动。
     */
    private Integer 人际互动;

    /**
     * 灵活性。
     */
    private Integer 灵活性;

    /**
     * 独立自主。
     */
    private Integer 独立自主;

    /**
     * 工作氛围。
     */
    private Integer 工作氛围;

    /**
     * 薪酬福利。
     */
    private Integer 薪酬福利;

    /**
     * 答题不一致性。
     */
    private Integer 答题不一致性2;

    /**
     * 驱动力趋中倾向。
     */
    private Integer 驱动力趋中倾向;

    /**
     * 异常原因。
     */
    private String 异常原因2;

    /**
     * 中断次数。
     */
    private Integer 中断次数2;

    /**
     * 答题时间(分钟)。
     */
    private Double 答题时间2;

    /**
     * 测评完成时间  。
     */
    private String 测评完成时间2;

    /**
     * 区分批次。
     */
    @TableField(value="version", update="%s+1")
    private Integer version;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}