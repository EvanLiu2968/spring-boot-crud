package com.crud.cloud.evanliu2968.exception;

import com.crud.cloud.evanliu2968.constant.ResponseCodeEnum;

/**
 * 参数无效异常。
 *
 * @author guohao.yang
 * @version v1.0.0
 * @since 2019/4/28 16:53
 */
public class ParameterInvalidException extends BusinessException {

    public ParameterInvalidException() {
        super();
    }

    public ParameterInvalidException(ResponseCodeEnum resultCode) {
        super(resultCode);
    }

    public ParameterInvalidException(String msg) {
        super(msg);
    }

}
