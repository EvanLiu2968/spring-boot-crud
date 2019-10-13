package com.crud.cloud.evanliu2968.controller.admin;

import com.baomidou.mybatisplus.plugins.Page;
import com.crud.cloud.evanliu2968.dto.ResponseData;
import com.crud.cloud.evanliu2968.dto.request.admin.CopyToVirtualDeptReq;
import com.crud.cloud.evanliu2968.dto.request.admin.DeptSetSortReq;
import com.crud.cloud.evanliu2968.dto.request.admin.InsertDeptReq;
import com.crud.cloud.evanliu2968.dto.request.admin.InsertPersonToVirtualDeptReq;
import com.crud.cloud.evanliu2968.dto.response.admin.DeptRes;
import com.crud.cloud.evanliu2968.dto.response.admin.PostRes;
import com.crud.cloud.evanliu2968.service.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 机构管理接口
 *
 * @author xinhua.zhou
 * @date 2019-06-10
 */
@Api(tags = "crud_部门_部门管理")
@RequestMapping(path = "/evanliu2968/dept", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class AdminDeptController {

    private DeptService deptService;

    @Autowired
    public AdminDeptController(DeptService deptService) {
        this.deptService = deptService;
    }

    @ApiOperation(nickname = "adminDeptControllerListDept", value = "查询部门列表信息", httpMethod = "GET")
    @GetMapping(value = "/listDept", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData<List<DeptRes>> listDept(@RequestParam(value = "departShow", required = false, defaultValue = "N") String departShow,@RequestParam(value = "tree", required = false, defaultValue = "N") String tree) {
        List<DeptRes> deptResList = this.deptService.listDept(departShow,tree);
        return ResponseData.success(deptResList);
    }

    @ApiOperation(nickname = "adminDeptControllerGetDept", value = "查询单个部门信息", httpMethod = "GET")
    @GetMapping(value = "/getDept/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData<DeptRes> getDept(@PathVariable("id") Integer id) {
        DeptRes deptRes = this.deptService.getDept(id);
        return ResponseData.success(deptRes);
    }

    @ApiOperation(nickname = "adminDeptControllerInsertDept", value = "创建部门", httpMethod = "POST")
    @PostMapping(value = "/insertDept", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData insertDept(@Valid @RequestBody InsertDeptReq request) {
        boolean result = this.deptService.insertDept(request);
        return result ? ResponseData.success() : ResponseData.fail("服务错误，无法创建部门");
    }

    @ApiOperation(nickname = "adminDeptControllerUpdateDept", value = "修改部门", httpMethod = "PUT")
    @PutMapping(value = "/updateDept/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData updateDept(@PathVariable("id") Integer id, @Valid @RequestBody InsertDeptReq request) {
        boolean result = this.deptService.updateDept(id, request);
        return result ? ResponseData.success() : ResponseData.fail("服务错误，无法修改信息");
    }

    @ApiOperation(nickname = "adminDeptControllerDeleteDept", value = "删除部门", httpMethod = "DELETE")
    @DeleteMapping(value = "/deleteDept/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData deleteDept(@PathVariable("id") Integer id) {
        boolean result = this.deptService.deleteDept(id);
        return result ? ResponseData.success() : ResponseData.fail("服务错误，无法删除信息");
    }

    @ApiOperation(nickname = "getPostByDeptId", value = "通过部门id查询岗位信息", httpMethod = "GET")
    @GetMapping(value = "/getPostByDeptId/{deptId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData<Page<PostRes>> getPostByDeptId(@PathVariable("deptId") Integer deptId,
                                                       @RequestParam(name = "page",required = false,defaultValue = "1") Integer page,
                                                       @RequestParam(name = "size",required = false,defaultValue = "10") Integer size) {
        return ResponseData.success(this.deptService.getPostByDeptId(deptId,page,size));
    }

    @ApiOperation(nickname = "getDeptByPersonId", value = "通过人员id查询部门岗位信息", httpMethod = "GET")
    @GetMapping(value = "/getDeptByPersonId/{personId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData<DeptRes> getDeptByPersonId(@PathVariable("personId") Integer personId) {
        return ResponseData.success(this.deptService.getDeptByPersonId(personId));
    }

    @ApiOperation(nickname = "setSort", value = "上移或下移")
    @PutMapping(value = "/setSort", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData setSort(@Valid @RequestBody DeptSetSortReq req) {
        boolean result = this.deptService.setSort(req);
        return result ? ResponseData.success() : ResponseData.fail("无法进行上移或下移");
    }


    @ApiOperation(nickname = "adminDeptControllerGetChildrenDeptById", value = "根据部门id查询子部门列表信息", httpMethod = "GET")
    @GetMapping(value = "/getChildrenDeptById/{deptId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData<List<DeptRes>> getChildrenDeptById(@PathVariable("deptId") Integer deptId,
                                                           @RequestParam(name = "type",required = false,defaultValue = "0") Integer type,
           @RequestParam(name = "deptName",required = false) String deptName) {
        List<DeptRes> deptResList = this.deptService.getChildrenDeptById(deptId,type,deptName);
        return ResponseData.success(deptResList);
    }


    @ApiOperation(nickname = "copyToVirtualDept", value = "复制到虚拟部门")
    @PostMapping(value = "/copyToVirtualDept", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData copyToVirtualDept(@Valid @RequestBody CopyToVirtualDeptReq req) {
        boolean result = this.deptService.copyToVirtualDept(req);
        return result ? ResponseData.success() : ResponseData.fail("无法复制到虚拟部门");
    }

    @ApiOperation(nickname = "insertPersonToVirtualDept", value = "加入虚拟部门")
    @PostMapping(value = "/insertPersonToVirtualDept", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData insertPersonToVirtualDept(@Valid @RequestBody InsertPersonToVirtualDeptReq req) {
        boolean result = this.deptService.insertPersonToVirtualDept(req);
        return result ? ResponseData.success() : ResponseData.fail("无法加入虚拟部门");
    }
}
