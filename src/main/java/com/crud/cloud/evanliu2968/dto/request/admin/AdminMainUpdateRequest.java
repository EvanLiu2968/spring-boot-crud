package com.crud.cloud.evanliu2968.dto.request.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by evanliu2968 on 2019-10-14.
 */
@Data
@ApiModel(value = "AdminMainUpdateRequest")
public class AdminMainUpdateRequest {

    /**
     * 管理员姓名
     */
    @ApiModelProperty(value = "管理员姓名")
    private String adminName;

    /**
     * 所在集团
     */
    @ApiModelProperty(value = "所在集团")
    private String adminGroup;

    /**
     * 任职岗位
     */
    @ApiModelProperty(value = "任职岗位")
    private String adminPost;

    /**
     * 个性签名
     */
    @ApiModelProperty(value = "个性签名")
    private String personalSignature;

    /**
     * 管理员头像
     */
    @ApiModelProperty(value = "管理员头像")
    private String imageUrl;




}
