package com.crud.cloud.evanliu2968.exception;

import com.crud.cloud.evanliu2968.constant.BusinessExceptionEnum;
import com.crud.cloud.evanliu2968.constant.ResponseCodeEnum;
import lombok.Data;

/**
 * 异常基类，各个模块的运行期异常均继承该类。
 *
 * @author guohao.yang
 * @version v1.0.0
 */
@Data
public class BusinessException extends RuntimeException {

    protected Integer code;

    protected String message;

    protected ResponseCodeEnum resultCode;

    /**
     * 无参构造器，code+message为默认的BusinessExceptionEnum里面的映射。
     */
    public BusinessException() {
        BusinessExceptionEnum exceptionEnum = BusinessExceptionEnum.getByEClass(this.getClass());
        if (exceptionEnum != null) {
            this.resultCode = exceptionEnum.getResponseCodeEnum();
            this.code = exceptionEnum.getResponseCodeEnum().getValue();
            this.message = exceptionEnum.getResponseCodeEnum().getMessage();
        }

    }

    /**
     * 覆盖message构造器。
     */
    public BusinessException(String message) {
        this();
        this.message = message;
    }

    /**
     * 覆盖ResponseCodeEnum构造器。
     */
    public BusinessException(ResponseCodeEnum resultCode) {
        this.resultCode = resultCode;
        this.code = resultCode.getValue();
        this.message = resultCode.getMessage();
    }

    /**
     * 覆盖code， message构造器。
     */
    public BusinessException(Integer code, String message) {
        this();
        this.code = code;
        this.message = message;
    }

}
