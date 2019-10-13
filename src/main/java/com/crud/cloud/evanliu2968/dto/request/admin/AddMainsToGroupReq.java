package com.crud.cloud.evanliu2968.dto.request.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "DAddMainsToGroupRequest插入对象")
public class AddMainsToGroupReq {
    /**
     * 用户id数组
     */
    @ApiModelProperty(value = "用户id数组")
    Integer[] adminIds;
}
