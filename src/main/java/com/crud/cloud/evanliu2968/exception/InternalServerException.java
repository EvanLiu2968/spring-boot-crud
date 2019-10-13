package com.crud.cloud.evanliu2968.exception;

/**
 * 内部服务器错误异常。
 *
 * @author guohao.yang
 * @version v1.0.0
 * @since 2019/4/28 16:53
 */
public class InternalServerException extends BusinessException {

    public InternalServerException() {
        super();
    }

    public InternalServerException(String msg) {
        super(msg);
    }

}
