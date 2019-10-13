package com.crud.cloud.evanliu2968.service.admin;

import com.baomidou.mybatisplus.service.IService;
import com.crud.cloud.evanliu2968.po.admin.AdminGroupRefPO;

import java.util.List;

/**
 * <p>
 * 管理员角色关联表 服务类
 * </p>
 */
public interface AdminGroupRefService extends IService<AdminGroupRefPO> {
    Integer deleteAdminGroupRefListByAdminId(Integer adminId);

    Integer insertAdminGroupRef(AdminGroupRefPO adminGroupRef);

    /**
     * 通过用户组删除用户
     * @param ids
     * @return
     */
    Integer deleteByGroupIds(List<Integer> ids);
}
