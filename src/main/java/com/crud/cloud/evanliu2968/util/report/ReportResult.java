package com.crud.cloud.evanliu2968.util.report;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by evanliu2968 on 2019-10-14.
 */
@Data
public class ReportResult {
    List<ReportScore> reportScore;//报告分数
    private String commentResult;//评语结论
    Map<String, Map<String, Proportion>> proportion;//指标、区间、占比

}
