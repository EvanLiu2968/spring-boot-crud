package com.crud.cloud.evanliu2968.service.admin;

import com.baomidou.mybatisplus.service.IService;
import com.crud.cloud.evanliu2968.dto.ResponseData;
import com.crud.cloud.evanliu2968.dto.request.admin.*;
import com.crud.cloud.evanliu2968.dto.request.user.CommonUserLoginReq;
import com.crud.cloud.evanliu2968.po.admin.AdminMainPO;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 管理员服务类
 * </p>
 */
public interface AdminMainService extends IService<AdminMainPO> {

    /**
     * cas登录
     */
    ResponseData casLogin();
    /**
     * 获取header
     */
    ResponseData getHeader();
    /**
     *第二天零点自动解锁此账号
     */
    ResponseData resetAdminMainLocked();

    /**
     * 登出信息
     */
    ResponseData logout();

    /**
     * 登录信息
     */
    ResponseData adminLogin(LoginReq request);

    /**
     * 查询用户信息
     */
    ResponseData selectMainAndRoleList(Integer page, Integer size, AdminMainSelectListReq request);


    /**
     * 根据角色查询用户列表信息
     */
    ResponseData getAdminMainListByRoleId(Integer roleId);

    /**
     * 保存用户信息
     */
    ResponseData insertAdminMain(AdminMainInsertReq request);

    /**
     * 修改用户信息
     */
    ResponseData updateAdminMain(Integer id,AdminMainUpdateReq request);


    /**
     * 下载用户导入模板
     *
     * @param servletResponse
     * @return
     */
    ResponseData<String> downloadAdminTemplate(HttpServletResponse servletResponse);

    /**
     * 导入用户信息
     *
     * @param request
     * @return
     */
    ResponseData<String> uploadAdmin(AdminUploadReq request);

    /**
     * 下载用户信息
     *
     * @param servletResponse
     * @return
     */
    ResponseData<String> downLoadAdmin(AdminMainSelectListReq request, HttpServletResponse servletResponse);


    /**
     * 用户修改密码
     */
    ResponseData updateAdminMainPwd(Integer id,UpdateAdminMainPwdReq request);


    /**
     * 普通用户登录信息
     */
    ResponseData userLogin(CommonUserLoginReq request);

    /**
     * 修改当前用户信息
     * @param request
     * @return
     */
    ResponseData updateAdminMainInfo(AdminMainUpdateRequest request);
}
