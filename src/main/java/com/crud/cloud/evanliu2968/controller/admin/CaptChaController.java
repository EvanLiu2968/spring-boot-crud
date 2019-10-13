package com.crud.cloud.evanliu2968.controller.admin;

import com.crud.cloud.evanliu2968.annotation.NoLogin;
import com.crud.cloud.evanliu2968.constant.ResponseCodeEnum;
import com.crud.cloud.evanliu2968.dto.ResponseData;
import com.crud.cloud.evanliu2968.dto.request.user.CheckCodeReq;
import com.crud.cloud.evanliu2968.service.admin.CaptchaService;
import com.crud.cloud.evanliu2968.util.CaptchaUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * @author xinhua.zhou
 * 验证码管理接口
 */
@Api(tags = "crud_登录_验证码管理")
@RequestMapping(path = "/evanliu2968/captcha", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class CaptChaController {

    private CaptchaService captchaService;

    @Autowired
    public CaptChaController(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    @NoLogin
    @ApiOperation(nickname = "getImageCode", value = "获取验证码")
    @GetMapping(value = "/getImageCode", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void getImageCode(@ApiParam(name = "width", value = "验证码宽度", defaultValue = "100") @RequestParam(value = "width", required = false, defaultValue = "100") int width,
                             @ApiParam(name = "height", value = "验证码高度", defaultValue = "30") @RequestParam(value = "height", required = false, defaultValue = "20") int height,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        OutputStream os = response.getOutputStream();
        //返回验证码和图片的map
        Map<String, Object> map = CaptchaUtil.getImageCode(width, height, os);
        String simpleCaptcha = "simpleCaptcha";
        request.getSession().setAttribute(simpleCaptcha, map.get("strEnsure").toString().toLowerCase());
        request.getSession().setAttribute("codeTime", System.currentTimeMillis());
        try {
            ImageIO.write((BufferedImage) map.get("image"), "jpg", os);
        } catch (IOException e) {
        } finally {
            if (os != null) {
                os.flush();
                os.close();
            }
        }
    }

    @NoLogin
    @ApiOperation(nickname = "verifyCode", value = "验证验证码")
    @PostMapping(value = "/verifyCode", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData verifyCode(@Valid @RequestBody CheckCodeReq req) {
        return this.captchaService.verifyCode(req.getCaptcha()) ? ResponseData.success() : new ResponseData(ResponseCodeEnum.INVALID_CODE.getValue(), ResponseCodeEnum.INVALID_CODE.getMessage());
    }
}
