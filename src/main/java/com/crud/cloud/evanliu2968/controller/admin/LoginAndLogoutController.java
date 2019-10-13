package com.crud.cloud.evanliu2968.controller.admin;

import com.crud.cloud.evanliu2968.annotation.NoLogin;
import com.crud.cloud.evanliu2968.dto.ResponseData;
import com.crud.cloud.evanliu2968.dto.request.admin.LoginReq;
import com.crud.cloud.evanliu2968.dto.request.user.CommonUserLoginReq;
import com.crud.cloud.evanliu2968.service.admin.AdminMainService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author xinhua.zhou
 * @date 2019-06-06
 */
@Api(tags = "crud_系统管理_登录和登出")
@RequestMapping(path = "/evanliu2968/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@NoLogin
public class LoginAndLogoutController {

    @Autowired
    private AdminMainService adminMainService;

    @NoLogin
    @ApiOperation(nickname = "loginController", value = "登录信息", httpMethod = "POST")
    @PostMapping(value = "/adminLogin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData adminLogin(@Valid @RequestBody LoginReq request) {
        ResponseData<String> response = this.adminMainService.adminLogin(request);
        return response;
    }

    @ApiOperation(nickname = "getHeaderController", value = "获取header", httpMethod = "GET")
    @GetMapping(value = "/getHeader", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData<String> getHeader() {
        return this.adminMainService.getHeader();
    }

    @ApiOperation(nickname = "logoutController", value = "登出", httpMethod = "PUT")
    @PutMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData logout() {
        return this.adminMainService.logout();
    }

    @NoLogin
    @ApiOperation(nickname = "loginControllerUserLogin", value = "普通用户登录信息")
    @PostMapping(value = "/userLogin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData userLogin(@Valid @RequestBody CommonUserLoginReq request) {
        ResponseData<String> response = this.adminMainService.userLogin(request);
        return response;
    }


}
