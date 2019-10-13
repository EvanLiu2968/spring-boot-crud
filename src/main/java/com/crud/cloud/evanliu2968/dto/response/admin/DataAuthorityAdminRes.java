package com.crud.cloud.evanliu2968.dto.response.admin;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "DataAuthorityAdminRes")
public class DataAuthorityAdminRes {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 管理员姓名
     */
    private String adminName;

    /**
     * 管理员账号
     */
    private String adminAccount;


}
