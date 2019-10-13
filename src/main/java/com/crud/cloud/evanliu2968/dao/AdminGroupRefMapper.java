package com.crud.cloud.evanliu2968.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.crud.cloud.evanliu2968.po.admin.AdminGroupRefPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 管理员角色关联表 Mapper 接口
 * </p>
 */
public interface AdminGroupRefMapper extends BaseMapper<AdminGroupRefPO> {
    Integer deleteAdminGroupRefListByAdminId(Integer adminId);

    /**
     * 通过用户组删除用户
     * @param ids
     * @return
     */
    Integer deleteByGroupIds(@Param("ids")  List<Integer> ids);
}
