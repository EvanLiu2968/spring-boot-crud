package com.crud.cloud.evanliu2968.exception;

import com.crud.cloud.evanliu2968.constant.ResponseCodeEnum;
import com.crud.cloud.evanliu2968.dto.ResponseData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 处理全局controller类中的异常
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LogManager.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理请求异常
     */
    @ExceptionHandler(RequestException.class)
    @ResponseBody
    ResponseData RequestException(RequestException e) {
        return ResponseData.build(e.getCode(), e.getMessage());
    }

    /**
     * 处理无授权
     */
    @ExceptionHandler(PermissionDenyException.class)
    @ResponseBody
    ResponseData permissionException(PermissionDenyException e) {
        return ResponseData.build(e.getCode(), e.getMessage());
    }

    /**
     * 处理BaseException异常
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    ResponseData baseException(BusinessException e) {
        LOGGER.error(e.getMessage(), e);
        return ResponseData.build(e.getCode(), e.getMessage());
    }

    /**
     * 处理校验valid异常(两种异常：BindException或者MethodArgumentNotValidException)，
     * 在post RequestBody的时候验证失败抛出MethodArgumentNotValidException。
     *
     * @param e MethodArgumentNotValidException
     * @return ResponseData
     */
    @ResponseBody
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseData methodArgumentNotValidException(MethodArgumentNotValidException e) {
        if (null != e) {
            BindingResult result = e.getBindingResult();
            return this.paramValidError(result);
        }
        return ResponseData.fail();
    }

    @ResponseBody
    @ExceptionHandler(value = {BindException.class})
    public ResponseData bindException(BindException e) {
        if (null != e) {
            BindingResult result = e.getBindingResult();
            return this.paramValidError(result);
        }
        return ResponseData.fail();
    }

    private ResponseData paramValidError(BindingResult result) {
        String msg = result.getAllErrors().get(0).getDefaultMessage();
        LOGGER.error(ResponseCodeEnum.PARAM_INVALID.getMessage(), msg);
        return ResponseData.build(
                ResponseCodeEnum.PARAM_INVALID.getValue(),
                msg,
                ResponseCodeEnum.PARAM_INVALID.getMessage()
        );
    }

    /**
     * 处理所有不可知的异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    ResponseData handleUnException(Exception e) {
        LOGGER.error(e.getMessage(), e);

        return ResponseData.build(
                ResponseCodeEnum.SYSTEM_ERROR.getValue(),
                ResponseCodeEnum.SYSTEM_ERROR.getMessage(), e.getMessage()
        );
    }
}
