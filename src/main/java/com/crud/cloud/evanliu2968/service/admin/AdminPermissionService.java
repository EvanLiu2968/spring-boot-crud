package com.crud.cloud.evanliu2968.service.admin;

import com.baomidou.mybatisplus.service.IService;
import com.crud.cloud.evanliu2968.dto.ResponseData;
import com.crud.cloud.evanliu2968.dto.response.admin.AdminPermissionsRes;
import com.crud.cloud.evanliu2968.po.admin.AdminPermissionPO;
import com.crud.cloud.evanliu2968.po.admin.AdminPermissionRefPO;

import java.util.List;

/**
 * <p>
 * 用户权限服务类
 * </p>
 */
public interface AdminPermissionService extends IService<AdminPermissionPO> {
    /**
     * 根据用户id查询所有角色
     */

    List<AdminPermissionsRes> listPermissionByRoleId(AdminPermissionRefPO tAdminPermissionRefRef);
    /**
     * 查询用户信息
     */
    List<AdminPermissionsRes> selectPermissionsByAdminId(Integer adminId, String url);
    /**
     * 查询用户信息
     */
    List<AdminPermissionsRes> selectPermissionsByAdminIdWhileLogin(Integer adminId);
    /**
     * 查询权限点信息
     */
    ResponseData getAdminPermissionList();
}
