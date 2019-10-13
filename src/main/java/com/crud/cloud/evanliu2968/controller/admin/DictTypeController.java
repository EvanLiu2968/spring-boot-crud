package com.crud.cloud.evanliu2968.controller.admin;

import com.crud.cloud.evanliu2968.dto.ResponseData;
import com.crud.cloud.evanliu2968.service.DictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "crud_系统管理_代码表管理")
@RequestMapping(path = "/evanliu2968/dictType", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class DictTypeController {

    @Autowired
    private DictTypeService dictTypeService;

    @ApiOperation(nickname = "DictTypeControllerListDict", value = "项目代码表查询", httpMethod = "GET")
    @GetMapping(value = "/listDict", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData listDict() {
        return this.dictTypeService.listDict();
    }
}
