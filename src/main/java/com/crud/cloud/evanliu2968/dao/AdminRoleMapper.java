package com.crud.cloud.evanliu2968.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.crud.cloud.evanliu2968.dto.request.admin.AdminRoleSelectListReq;
import com.crud.cloud.evanliu2968.dto.response.admin.AdminRoleRes;
import com.crud.cloud.evanliu2968.po.admin.AdminRolePO;
import com.crud.cloud.evanliu2968.po.admin.AdminRoleRefPO;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * <p>
 * 角色状Mapper 接口
 * </p>
 */
public interface AdminRoleMapper extends BaseMapper<AdminRolePO> {
    AdminRolePO selectByfixedId(@Param("id") Integer id);
    /**
     * 根据用户id查询所有角色
     */

    List<AdminRoleRes>  listRoleByAdminId(AdminRoleRefPO tAdminRoleRef);
    List<AdminRoleRes> selectRoleList(@Param("limit") Integer limit, @Param("offset") Integer size, @Param("request") AdminRoleSelectListReq request);
}
