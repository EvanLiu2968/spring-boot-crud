package com.crud.cloud.evanliu2968.util.report;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by evanliu2968 on 2019-10-14.
 */
@Data
public class ReportScore {
    private String indexName;//指标名称
    private BigDecimal avgScore;//团队均分
    private BigDecimal division25Score;//25分值
    private BigDecimal division75Score;//75分值
    private Appraise360IndexStyle appraise360IndexStyle;//360指标的图例颜色及是否优劣势
}
