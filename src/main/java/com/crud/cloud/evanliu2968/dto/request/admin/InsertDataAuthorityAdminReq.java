package com.crud.cloud.evanliu2968.dto.request.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "InsertDataAuthorityAdminReq")
public class InsertDataAuthorityAdminReq {

    /**
     * 管理员ids
     */
    @ApiModelProperty(value = "管理员ids")
    @NotNull
    private Integer[] ids;

    /**
     * 库类型
     */
    @ApiModelProperty(value = "库类型,人才库(TALENT)，画像库(PORTRAIT),任免(LM)，新任(XR)，盘点(INVENTORY)")
    @NotNull
    private String type;


}
