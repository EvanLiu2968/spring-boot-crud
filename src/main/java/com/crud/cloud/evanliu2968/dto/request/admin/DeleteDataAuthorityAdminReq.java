package com.crud.cloud.evanliu2968.dto.request.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "DeleteDataAuthorityAdminReq")
public class DeleteDataAuthorityAdminReq {


    /**
     * 用户组id数组
     */
    @ApiModelProperty(value = "用户组ids")
    @NotNull
    private Integer[] ids;

    /**
     * 库类型
     */
    @ApiModelProperty(value = "库类型,人才库(TALENT)，1表示画像库(PORTRAIT)")
    @NotNull
    private String type;

}
