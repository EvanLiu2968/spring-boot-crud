package com.crud.cloud.evanliu2968.exception;

import com.crud.cloud.evanliu2968.constant.ResponseCodeEnum;

/**
 * 数据已经存在异常。
 *
 * @author guohao.yang
 * @version v1.0.0
 * @since 2019/4/28 16:53
 */
public class DataConflictException extends BusinessException {

    public DataConflictException() {
        super();
    }

    public DataConflictException(ResponseCodeEnum resultCode) {
        super(resultCode);
    }

    public DataConflictException(String msg) {
        super(msg);
    }
}
