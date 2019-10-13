package com.crud.cloud.evanliu2968.dto.response.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "AdminPermissionsRes")
public class AdminPermissionsRes {
  /**
   * 权限id
   */
  @ApiModelProperty(value ="权限id")
  private String id;
    /**
     * 权限名称
     */
  @ApiModelProperty(value ="权限名称")
    private String permissionName;
    /**
     * 编码
     */
  @ApiModelProperty(value ="编码")
    private String recCode;

    /**
     * 父权限id
     */
  @ApiModelProperty(value ="父权限id")
    private String parentId;


}
