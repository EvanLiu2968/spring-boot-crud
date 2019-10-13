package com.crud.cloud.evanliu2968.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.crud.cloud.evanliu2968.dto.ResponseData;
import com.crud.cloud.evanliu2968.dto.request.flow.InsertTemplateReq;
import com.crud.cloud.evanliu2968.dto.request.TemplateListReq;
import com.crud.cloud.evanliu2968.dto.request.UpdateTemplateReq;
import com.crud.cloud.evanliu2968.dto.response.TemplateRes;
import com.crud.cloud.evanliu2968.service.template.TemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


/** 示例模板管理接口
 * @author xinhua.zhou
 * @date 2019-05-29
 */
@Api(tags = "基础版系统_示例模板接口")
@RequestMapping(path = "/basic/template", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class TemplateController {

    private TemplateService templateService;

    @Autowired
    public TemplateController(TemplateService templateService){
        this.templateService = templateService;
    }

    @SuppressWarnings("unchecked")
    @ApiOperation(nickname = "TemplateControllerTemplateList", value = "查询模板列表信息")
    @GetMapping(value = "/templateList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData<Page<TemplateRes>> templateList(@Valid TemplateListReq request,
                                                        @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                        @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        Page<TemplateRes> templatePage = templateService.templateList(page, size, request);
        return ResponseData.success(templatePage);
    }

    @SuppressWarnings("unchecked")
    @ApiOperation(nickname = "TemplateControllerGetTemplate", value = "查询具体模板信息")
    @GetMapping(value = "/getTemplate/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData<TemplateRes> getTemplate(@PathVariable("id") Long id) {
        TemplateRes res =  templateService.getTemplate(id);
        return (ResponseData<TemplateRes>)ResponseData.success(res);
    }

    @ApiOperation(nickname = "TemplateControllerInsertTemplate", value = "新增模板信息")
    @PostMapping(value = "/insertTemplate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData insertTemplate(@Valid @RequestBody InsertTemplateReq request) {
        boolean result =   templateService.insertTemplate(request);
        return result ? ResponseData.success():ResponseData.fail("服务错误，无法新增信息");
    }

    @ApiOperation(nickname = "TemplateControllerUpdateTemplate", value = "修改模板信息")
    @PutMapping(value = "/updateTemplate/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData updateTemplate(@PathVariable("id") Long id,@Valid @RequestBody UpdateTemplateReq request) {
        boolean result =   templateService.updateTemplate(id,request);
        return result ? ResponseData.success():ResponseData.fail("服务错误，无法更新信息");
    }

    @ApiOperation(nickname = "TemplateControllerDeleteTemplate", value = "删除模板信息")
    @DeleteMapping(value = "/deleteTemplate/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData deleteTemplate(@PathVariable("id") Long id) {
        boolean result =  templateService.deleteTemplate(id);
        return result ? ResponseData.success():ResponseData.fail("服务错误，无法删除信息");
    }
}
