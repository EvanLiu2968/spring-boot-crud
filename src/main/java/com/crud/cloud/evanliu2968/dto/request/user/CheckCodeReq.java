package com.crud.cloud.evanliu2968.dto.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "CheckCodeReq")
public class CheckCodeReq {

    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码", required = true)
    @NotNull(message = "验证码不能为空")
    private String captcha;
}
