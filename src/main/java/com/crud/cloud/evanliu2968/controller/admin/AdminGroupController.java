package com.crud.cloud.evanliu2968.controller.admin;

import com.crud.cloud.evanliu2968.dto.ResponseData;
import com.crud.cloud.evanliu2968.dto.request.admin.AddMainsToGroupReq;
import com.crud.cloud.evanliu2968.dto.request.admin.AdminGroupInsertReq;
import com.crud.cloud.evanliu2968.dto.request.admin.AdminGroupUpdateReq;
import com.crud.cloud.evanliu2968.dto.request.admin.DeleteAdminGroupReq;
import com.crud.cloud.evanliu2968.service.admin.AdminGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "crud_系统管理_用户组管理")
@RequestMapping(path = "/evanliu2968/group", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class AdminGroupController {

    @Autowired
    private AdminGroupService adminGroupService;

    @ApiOperation(nickname = "groupControllerList", value = "查询分组列表信息", httpMethod = "GET")
    @GetMapping(value = "/getAdminGroupList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData getAdminGroupList() {
        ResponseData<String> response = this.adminGroupService.selectGroupList();
        return response;
    }

    @ApiOperation(nickname = "adminGroupControllerInsert", value = "创建分组", httpMethod = "POST")
    @PostMapping(value = "/insertAdminGroup", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData insertAdminGroup(@Valid @RequestBody AdminGroupInsertReq request) {
        ResponseData<String> response = this.adminGroupService.insertAdminGroup(request);
        return response;
    }

    @ApiOperation(nickname = "adminGroupControllerDelete", value = "批量删除用户组", httpMethod = "DELETE")
    @DeleteMapping(value = "/batchDeleteAdminGroup", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData batchDeleteAdminGroup(@Valid @RequestBody DeleteAdminGroupReq req) {
        ResponseData<String> response = this.adminGroupService.batchdeleteAdminGroup(req.getIds());
        return response;
    }

    @ApiOperation(nickname = "addMainsToGroupController", value = "修改用户组添加用户账号", httpMethod = "PUT")
    @PutMapping(value = "/addMainsToGroup/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData addMainsToGroup(@PathVariable("id") Integer id, @Valid @RequestBody AddMainsToGroupReq request) {
        ResponseData<String> response = this.adminGroupService.addMainsToGroup(id, request);
        return response;
    }

    @ApiOperation(nickname = "deleteMainsToGroupController", value = "用户组删除用户账号", httpMethod = "DELETE")
    @DeleteMapping(value = "/deleteMainsToGroup/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData deleteMainsToGroup(@PathVariable("id") Integer id, @Valid @RequestBody AddMainsToGroupReq request) {
        ResponseData<String> response = this.adminGroupService.deleteMainsToGroup(id, request);
        return response;
    }

    @ApiOperation(nickname = "updateAdminGroupController", value = "修改分组名", httpMethod = "PUT")
    @PutMapping(value = "/updateAdminGroup/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData updateAdminGroup(@PathVariable("id") Integer id, @Valid @RequestBody AdminGroupUpdateReq request) {
        ResponseData<String> response = this.adminGroupService.updateAdminGroup(id, request);
        return response;
    }

    @ApiOperation(nickname = "deleteAdminGroupDelete", value = "删除分组", httpMethod = "DELETE")
    @DeleteMapping(value = "/deleteAdminGroup", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData deleteAdminGroup(@Valid @RequestParam("id") Integer id, @RequestParam("groupCode") String groupCode) {
        ResponseData<String> response = this.adminGroupService.deleteAdminGroup(id,groupCode);
        return response;
    }

}
