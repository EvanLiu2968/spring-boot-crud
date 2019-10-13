package com.crud.cloud.evanliu2968.dto.request.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "DAdminUploadRequest系统用户导入请求对象")
public class AdminUploadReq {

    @ApiModelProperty(value = "系统用户导入文件", required = true)
    @NotNull(message = "系统用户导入文件不能为空")
    private MultipartFile adminFile;
}
