package com.crud.cloud.evanliu2968.exception;

/**
 * 权限不足异常。
 *
 * @author guohao.yang
 * @version v1.0.0
 * @since 2019/4/28 16:53
 */
public class UserNotLoginException extends BusinessException {

    public UserNotLoginException() {
        super();
    }

    public UserNotLoginException(String msg) {
        super(msg);
    }

}
