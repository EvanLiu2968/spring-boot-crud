package com.crud.cloud.evanliu2968.controller.admin;

import com.crud.cloud.evanliu2968.dto.ResponseData;
import com.crud.cloud.evanliu2968.dto.request.admin.*;
import com.crud.cloud.evanliu2968.service.admin.AdminRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "crud_系统管理_角色管理")
@RequestMapping(path = "/evanliu2968/role", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class AdminRoleController {

    @Autowired
    private AdminRoleService adminRoleService;

    @GetMapping(value = "/getAdminRoleList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData getAdminRoleList(@Valid AdminRoleSelectListReq request, @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                         @RequestParam(value = "size", required = false, defaultValue = "10") int size, BindingResult result) {
        ResponseData<String> response = this.adminRoleService.selectRoleList(page, size, request);
        return response;
    }

    @ApiOperation(nickname = "adminRoleControllerInsert", value = "创建角色", httpMethod = "POST")
    @PostMapping(value = "/insertAdminRole", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData insertAdminRole(@Valid @RequestBody AdminRoleInsertReq request) {
        ResponseData<String> response = this.adminRoleService.insertAdminRole(request);
        return response;
    }

    @ApiOperation(nickname = "adminRoleControllerDelete", value = "批量删除角色", httpMethod = "DELETE")
    @DeleteMapping(value = "/batchDeleteAdminRole", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData batchDeleteAdminRole(@Valid @RequestBody DeleteAdminRoleReq req) {
        ResponseData<String> response = this.adminRoleService.batchdeleteAdminRole(req.getIds());
        return response;
    }

    @ApiOperation(nickname = "addMainsToRoleController", value = "修改角色添加用户账号", httpMethod = "PUT")
    @PutMapping(value = "/addMainsToRole/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData addMainsToRole(@PathVariable("id") Integer id, @Valid @RequestBody AddMainsToRoleReq request) {
        ResponseData<String> response = this.adminRoleService.addMainsToRole(id, request);
        return response;
    }

    @ApiOperation(nickname = "deleteMainsToRoleController", value = "修改角色删除用户账号", httpMethod = "DELETE")
    @DeleteMapping(value = "/deleteMainsToRole/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData deleteMainsToRole(@PathVariable("id") Integer id, @Valid @RequestBody DeleteMainsToRoleReq request) {
        ResponseData<String> response = this.adminRoleService.deleteMainsToRole(id, request);
        return response;
    }

    @ApiOperation(nickname = "updateAdminRoleController", value = "修改角色添加权限", httpMethod = "PUT")
    @PutMapping(value = "/updateAdminRole/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData updateAdminRole(@PathVariable("id") Integer id, @Valid @RequestBody AdminRoleUpdateReq request) {
        ResponseData<String> response = this.adminRoleService.updateAdminRole(id, request);
        return response;
    }
}
