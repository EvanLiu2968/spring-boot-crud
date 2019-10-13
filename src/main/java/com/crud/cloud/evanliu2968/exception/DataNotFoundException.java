package com.crud.cloud.evanliu2968.exception;

import com.crud.cloud.evanliu2968.constant.ResponseCodeEnum;

/**
 * 数据没有找到异常。
 *
 * @author guohao.yang
 * @version v1.0.0
 * @since 2019/4/28 16:53
 */
public class DataNotFoundException extends BusinessException {

    public DataNotFoundException() {
        super();
    }

    public DataNotFoundException(ResponseCodeEnum resultCode) {
        super(resultCode);
    }

    public DataNotFoundException(String msg) {
        super(msg);
    }

}
