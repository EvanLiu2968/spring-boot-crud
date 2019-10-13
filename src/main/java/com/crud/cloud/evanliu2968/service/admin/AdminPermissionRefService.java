package com.crud.cloud.evanliu2968.service.admin;

import com.baomidou.mybatisplus.service.IService;
import com.crud.cloud.evanliu2968.po.admin.AdminPermissionRefPO;

/**
 * <p>
 * 用户权限关联服务类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
public interface AdminPermissionRefService extends IService<AdminPermissionRefPO> {
    Integer deleteAdminPermissionRefListByRoleId(Integer adminId);
}
