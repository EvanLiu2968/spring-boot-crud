package com.crud.cloud.evanliu2968.controller.admin;

import com.crud.cloud.evanliu2968.dto.ResponseData;
import com.crud.cloud.evanliu2968.dto.request.admin.*;
import com.crud.cloud.evanliu2968.service.admin.AdminMainService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "crud_系统管理_用户管理")
@RequestMapping(path = "/evanliu2968/admin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class AdminMainController {

    @Autowired
    private AdminMainService adminMainService;

    @ApiOperation(nickname = "adminMainControllerList", value = "查询用户列表信息", httpMethod = "GET")
    @GetMapping(value = "/getAdminMainList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData getAdminMainList(@Valid AdminMainSelectListReq request, @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                         @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        ResponseData<String> response = this.adminMainService.selectMainAndRoleList(page, size, request);
        return response;
    }

    @ApiOperation(nickname = "adminMainControllerInsert", value = "创建用户", httpMethod = "POST")
    @PostMapping(value = "/insertAdminMain", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData insertAdminMain(@Valid @RequestBody AdminMainInsertReq request) {
        ResponseData<String> response = this.adminMainService.insertAdminMain(request);
        return response;
    }

    @ApiOperation(nickname = "adminMainControllerUpdate", value = "修改用户", httpMethod = "PUT")
    @PutMapping(value = "/updateAdminMain/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData updateAdminMain(@PathVariable("id") Integer id, @Valid @RequestBody AdminMainUpdateReq request) {
        ResponseData<String> response = this.adminMainService.updateAdminMain(id, request);
        return response;
    }

    @ApiOperation(nickname = "adminMainControllerUpdateAdminMainPwd", value = "用户修改密码", httpMethod = "PUT")
    @PutMapping(value = "/updateAdminMainPwd/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData updateAdminMainPwd(@PathVariable("id") Integer id, @Valid @RequestBody UpdateAdminMainPwdReq request) {
        ResponseData<String> response = this.adminMainService.updateAdminMainPwd(id, request);
        return response;
    }

    @ApiOperation(value = "修改当前用户信息", httpMethod = "PUT")
    @PutMapping(value = "/updateAdminMainInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData updateAdminMainInfo(@Valid @RequestBody AdminMainUpdateRequest request) {
        ResponseData<String> response = this.adminMainService.updateAdminMainInfo(request);
        return response;
    }

}
