package com.crud.cloud.evanliu2968.service.admin;

import com.baomidou.mybatisplus.service.IService;
import com.crud.cloud.evanliu2968.dto.ResponseData;
import com.crud.cloud.evanliu2968.dto.request.admin.*;
import com.crud.cloud.evanliu2968.dto.response.admin.AdminRoleRes;
import com.crud.cloud.evanliu2968.po.admin.AdminRolePO;
import com.crud.cloud.evanliu2968.po.admin.AdminRoleRefPO;

import java.util.List;

/**
 * <p>
 * 角色  服务类
 * </p>
 */
public interface AdminRoleService extends IService<AdminRolePO> {

    /**
     * 根据用户id查询所有角色
     */

    List<AdminRoleRes> listRoleByAdminId(AdminRoleRefPO tAdminRoleRef);;

    /**
     * 修改角色添加用户账号
     */

    ResponseData selectRoleList(Integer page, Integer size, AdminRoleSelectListReq request);

    /**
     * 保存角色信息
     */
    ResponseData insertAdminRole(AdminRoleInsertReq request);

    /**
     * 修改角色信息
     */
    ResponseData deleteAdminRole(Integer id);

    /**
     * 修改角色信息
     */
    ResponseData batchdeleteAdminRole(Integer[] ids);

    /**
     * 修改角色添加用户账号
     */
    ResponseData addMainsToRole(Integer id, AddMainsToRoleReq request);
    /**
     * 删除角色添加用户账号
     */
    ResponseData deleteMainsToRole(Integer id, DeleteMainsToRoleReq request);

    /**
     * 修改角色添加用户账号
     */
    ResponseData updateAdminRole(Integer id,AdminRoleUpdateReq request);

    /**
     * 查询所有有效角色信息
     *
     * @return
     */
    List<AdminRolePO> getAllEnabledRoles();
}
