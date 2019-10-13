package com.crud.cloud.evanliu2968.dto.response.admin;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "AdminMainListRes")
public class AdminMainListRes {
    private List<AdminMainRes> list;
}
