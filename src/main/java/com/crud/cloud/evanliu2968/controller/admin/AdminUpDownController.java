package com.crud.cloud.evanliu2968.controller.admin;

import com.crud.cloud.evanliu2968.dto.request.admin.AdminMainSelectListReq;
import com.crud.cloud.evanliu2968.service.admin.AdminMainService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author jinpeng.bu
 */
@Api(tags = "crud_用户管理_导入导出")
@RequestMapping(path = "/evanliu2968/adminUpDown", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@Log4j2
public class AdminUpDownController {

    @Autowired
    private AdminMainService adminMainService;

    @ApiOperation(nickname = "downAdminInfo", value = "导出系统管理用户列表", httpMethod = "GET")
    @GetMapping(value = "/downLoadAdmin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void downLoadAdmin(@Valid AdminMainSelectListReq request, HttpServletResponse servletResponse) {
        this.adminMainService.downLoadAdmin(request, servletResponse);
    }

}
