package com.crud.cloud.evanliu2968.controller.admin;

import com.baomidou.mybatisplus.plugins.Page;
import com.crud.cloud.evanliu2968.dto.ResponseData;
import com.crud.cloud.evanliu2968.dto.request.admin.*;
import com.crud.cloud.evanliu2968.dto.response.admin.DataAuthorityAdminRes;
import com.crud.cloud.evanliu2968.service.admin.DataAuthorityAdminRefService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**人才库画像库权限管理
 * @author xinhua.zhou
 */
@Api(tags = "crud系统_人才库画像库权限管理")
@RequestMapping(path = "/evanliu2968/dataAuthority", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class DataAuthorityAdminController {

    private DataAuthorityAdminRefService dataAuthorityAdminRefService;

    @Autowired
    public DataAuthorityAdminController(DataAuthorityAdminRefService dataAuthorityAdminRefService){
        this.dataAuthorityAdminRefService = dataAuthorityAdminRefService;
    }

    @SuppressWarnings("unchecked")
    @ApiOperation(nickname = "DataAuthorityAdminControllerListAdmin", value = "查询所在库的管理员列表信息")
    @GetMapping(value = "/listAdmin/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData<Page<DataAuthorityAdminRes>> listAdmin(@PathVariable("id") Integer id, @Valid DataAuthorityAdminListReq request, @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                               @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        Page<DataAuthorityAdminRes> response = dataAuthorityAdminRefService.listAdmin(page, size, id,request);
        return ResponseData.success(response);
    }

    @ApiOperation(nickname = "DataAuthorityAdminControllerInsertAdmin", value = "批量添加管理员")
    @PostMapping(value = "/insertAdmin/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData insertAdmin(@PathVariable("id") Integer id,@Valid @RequestBody InsertDataAuthorityAdminReq request) {
        Boolean result =  dataAuthorityAdminRefService.insertAdmin(id,request);
        return result ? ResponseData.success():ResponseData.fail("无法添加管理员");
    }

    @ApiOperation(nickname = "DataAuthorityAdminControllerBatchDeleteAdmin", value = "批量移除管理员")
    @DeleteMapping(value = "/batchDeleteAdmin/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData batchDeleteAdmin(@PathVariable("id") Integer id,@Valid @RequestBody DeleteDataAuthorityAdminReq request) {
        Boolean result =  dataAuthorityAdminRefService.batchDeleteAdmin(id,request);
        return result ? ResponseData.success():ResponseData.fail("无法移除管理员");
    }

}
