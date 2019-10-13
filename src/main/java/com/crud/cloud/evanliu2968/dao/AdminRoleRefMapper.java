package com.crud.cloud.evanliu2968.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.crud.cloud.evanliu2968.po.admin.AdminRoleRefPO;

/**
 * <p>
 * 管理员角色关联表 Mapper 接口
 * </p>
 */
public interface AdminRoleRefMapper extends BaseMapper<AdminRoleRefPO> {
    Long deleteAdminRoleRefListByAdminId(Long adminId);
}
