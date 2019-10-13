package com.crud.cloud.evanliu2968.exception;

import com.crud.cloud.evanliu2968.constant.ResponseCodeEnum;

/**
 * 权限不足异常。
 *
 * @author guohao.yang
 * @version v1.0.0
 * @since 2019/4/28 16:53
 */
public class PermissionForbiddenException extends BusinessException {

    public PermissionForbiddenException() {
        super();
    }

    public PermissionForbiddenException(ResponseCodeEnum resultCode) {
        super(resultCode);
    }

    public PermissionForbiddenException(String msg) {
        super(msg);
    }

}
