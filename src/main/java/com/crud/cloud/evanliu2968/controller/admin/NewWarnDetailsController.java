package com.crud.cloud.evanliu2968.controller.admin;

import com.crud.cloud.evanliu2968.constant.ResponseCodeEnum;
import com.crud.cloud.evanliu2968.dto.ResponseData;
import com.crud.cloud.evanliu2968.dto.request.admin.NewWarnDeatilsReq;
import com.crud.cloud.evanliu2968.dto.request.admin.WarnDeatilsSetReq;
import com.crud.cloud.evanliu2968.dto.response.admin.NewWarnDetailsRes;
import com.crud.cloud.evanliu2968.dto.response.admin.WarnDetailsSetRes;
import com.crud.cloud.evanliu2968.service.NewWarnDetailsService;
import com.crud.cloud.evanliu2968.service.WarnDetailsSetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 预警新方案设置
 *
 * @author cheng.wang
 * @date 2019-08-22
 */
@Api(tags = "预警新方案")
@RequestMapping(path = "/evanliu2968/newWarnDetails", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class NewWarnDetailsController {

    @Autowired
    private NewWarnDetailsService newWarnDetailsService;

    @ApiOperation(nickname = "newWarnDetailsControllerList", value = "新预警列表查询")
    @GetMapping(value = "/getNewWarnDetailsList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData<List<NewWarnDetailsRes>> getNewWarnDetailsList() {
        List<NewWarnDetailsRes> warnDetailsRes = this.newWarnDetailsService.selectNewWarnDetailsList();
        ResponseData<List<NewWarnDetailsRes>> responseData = new ResponseData<>(ResponseCodeEnum.SUCCESS.getValue(),ResponseCodeEnum.SUCCESS.getMessage());
        responseData.setData(warnDetailsRes);
        return responseData;
    }

    @ApiOperation(nickname = "newWarnDetailsControllerUpdate", value = "预警方案设置修改")
    @PostMapping(value = "/updateNewWarnDetails", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData<String> updateWarnDetailsSet(@Valid @RequestBody List<NewWarnDeatilsReq> request) {
        ResponseData<String> responseData = this.newWarnDetailsService.updateNewWarnDetails(request);
        return responseData;
    }
}
