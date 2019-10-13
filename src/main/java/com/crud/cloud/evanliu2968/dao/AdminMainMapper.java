package com.crud.cloud.evanliu2968.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.crud.cloud.evanliu2968.dto.request.admin.AdminMainSelectListReq;
import com.crud.cloud.evanliu2968.dto.response.admin.AdminMainRes;
import com.crud.cloud.evanliu2968.po.admin.AdminMainPO;
import org.apache.ibatis.annotations.Param;
import java.util.List;


/**
 * <p>
 * 用户管理员Mapper 接口
 * </p>
 */
public interface AdminMainMapper extends BaseMapper<AdminMainPO> {

    List<AdminMainRes> selectMainAndRoleList(@Param("limit") Integer limit, @Param("offset") Integer size, @Param("request") AdminMainSelectListReq request,
                                             @Param("userId") Integer userId);

    Integer selectMainAndRoleListCount(@Param("limit") Integer limit, @Param("offset") Integer size, @Param("request") AdminMainSelectListReq request);

    List<AdminMainRes> getAdminMainListByRoleId(Integer roleId);

    void resetAdminMainLocked();

    void updateAdminMainInfo(AdminMainPO currentUser);
}
