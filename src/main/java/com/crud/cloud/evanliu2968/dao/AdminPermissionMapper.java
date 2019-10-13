package com.crud.cloud.evanliu2968.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.crud.cloud.evanliu2968.dto.response.admin.AdminPermissionsRes;
import com.crud.cloud.evanliu2968.po.admin.AdminPermissionPO;
import com.crud.cloud.evanliu2968.po.admin.AdminPermissionRefPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户权限Mapper 接口
 * </p>
 *
 * @author Sipin ERP Development Team
 */
public interface AdminPermissionMapper extends BaseMapper<AdminPermissionPO> {
    List<AdminPermissionsRes> selectPermissionsByAdminId(@Param("adminId") Integer adminId, @Param("url") String url);
    List<AdminPermissionsRes> listPermissionByRoleId(AdminPermissionRefPO tAdminPermissionRefRef);
    List<AdminPermissionsRes> getAdminPermissionList();
    List<AdminPermissionsRes> selectPermissionsByAdminIdWhileLogin(@Param("adminId") Integer adminId);
}
