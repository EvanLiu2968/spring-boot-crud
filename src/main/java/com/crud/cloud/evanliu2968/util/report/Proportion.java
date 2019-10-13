package com.crud.cloud.evanliu2968.util.report;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by evanliu2968 on 2019-10-14.
 */
@Data
public class Proportion {
    private String text;//区间中的名称 待发展、中等、优势
    private BigDecimal value;//数值
    private String percentage;//百分比
    private List<Integer> userIds;//包含用户
    private String colorStyle;//样式
    private String inteval;//区间

}
