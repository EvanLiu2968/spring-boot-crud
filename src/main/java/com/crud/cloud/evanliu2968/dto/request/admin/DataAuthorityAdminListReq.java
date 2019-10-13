package com.crud.cloud.evanliu2968.dto.request.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
@ApiModel(value = "DataAuthorityAdminListReq")
public class DataAuthorityAdminListReq {

    /**
     * 管理员名称
     */
    @ApiModelProperty(value = "管理员名称")
    private String adminName;

    /**
     * 帐号
     */
    @ApiModelProperty(value = "帐号")
    private String adminAccount;

    /**
     * 库类型
     */
    @ApiModelProperty(value = "库类型,人才库(TALENT)，1表示画像库(PORTRAIT)",required = true)
    @NotNull(message = "库类型不能为空")
    private String type;


}
