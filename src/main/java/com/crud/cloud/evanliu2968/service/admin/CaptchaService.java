package com.crud.cloud.evanliu2968.service.admin;


/** 模板服务类
 * @author xinhua.zhou
 * @date 2019-05-29
 */
public interface CaptchaService {

    /**
     * 获取验证码
     * @param code 验证码
     * @return 返回验证是否成功
     */
    boolean verifyCode(String code);
}
