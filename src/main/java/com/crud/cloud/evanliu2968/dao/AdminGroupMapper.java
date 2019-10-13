package com.crud.cloud.evanliu2968.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.crud.cloud.evanliu2968.dto.response.admin.AdminGroupRes;
import com.crud.cloud.evanliu2968.po.admin.AdminGroupPO;
import com.crud.cloud.evanliu2968.po.admin.AdminGroupRefPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色状Mapper 接口
 * </p>
 */
public interface AdminGroupMapper extends BaseMapper<AdminGroupPO> {

    AdminGroupPO selectByfixedId(@Param("id") Integer id);
    /**
     * 根据用户id查询所有角色
     */

    List<AdminGroupRes>  listGroupByAdminId(AdminGroupRefPO tAdminGroupRef);
    List<AdminGroupRes> selectGroupList();
    List<AdminGroupRes>  getGroupIdByUserId(Integer tAdminGroupRef);

    Integer insertAdminGroup(AdminGroupPO adminGroup);


    List<AdminGroupPO> getAdminGroupByGroupCode(String groupCode);
}
