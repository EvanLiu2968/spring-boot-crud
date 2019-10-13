package com.crud.cloud.evanliu2968.controller.admin;

import com.crud.cloud.evanliu2968.dto.ResponseData;
import com.crud.cloud.evanliu2968.dto.request.admin.AdminMainSelectListReq;
import com.crud.cloud.evanliu2968.service.admin.AdminPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "crud_系统管理_用户权限管理")
@RequestMapping(path = "/evanliu2968/permission", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class AdminPermissonController {

    @Autowired
    private AdminPermissionService adminPermissionService;

    @ApiOperation(nickname = "adminPermissionControllerList", value = "查询权限列表信息", httpMethod = "GET")
    @GetMapping(value = "/getAdminPermissionList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData getAdminPermissionList(@Valid AdminMainSelectListReq request, @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                               @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        ResponseData<String> response = this.adminPermissionService.getAdminPermissionList();
        return response;
    }

}
