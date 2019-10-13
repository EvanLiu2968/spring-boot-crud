package com.crud.cloud.evanliu2968.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.crud.cloud.evanliu2968.po.admin.AdminPermissionRefPO;

/**
 * <p>
 * 用户权限关联表Mapper 接口
 * </p>
 */
public interface AdminPermissionRefMapper extends BaseMapper<AdminPermissionRefPO> {
    Integer deleteAdminPermissionRefListByRoleId(Integer roleId);
}
