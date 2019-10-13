package com.crud.cloud.evanliu2968.service.admin;

import com.baomidou.mybatisplus.service.IService;
import com.crud.cloud.evanliu2968.po.admin.AdminRoleRefPO;

/**
 * <p>
 * 管理员角色关联表 服务类
 * </p>
 */
public interface AdminRoleRefService extends IService<AdminRoleRefPO> {
    Long deleteAdminRoleRefListByAdminId(Long adminId);

    Integer insertAdminRoleRef(AdminRoleRefPO adminRoleRef);
}
