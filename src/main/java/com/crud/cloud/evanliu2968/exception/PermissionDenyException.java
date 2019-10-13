package com.crud.cloud.evanliu2968.exception;

import com.crud.cloud.evanliu2968.constant.ResponseCodeEnum;

/**
 * 无权访问异常。
 *
 * @author guohao.yang
 * @version v1.0.0
 * @since 2019/4/28 16:53
 */
public class PermissionDenyException extends BusinessException {

    public PermissionDenyException(int code, String feedback) {
        super(code, feedback);
    }

    public PermissionDenyException(ResponseCodeEnum responseCodeEnum) {
        super(responseCodeEnum.getValue(), responseCodeEnum.getMessage());
    }

}
