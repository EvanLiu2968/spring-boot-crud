package com.crud.cloud.evanliu2968.service.admin;

import com.baomidou.mybatisplus.service.IService;
import com.crud.cloud.evanliu2968.dto.ResponseData;
import com.crud.cloud.evanliu2968.dto.request.admin.AddMainsToGroupReq;
import com.crud.cloud.evanliu2968.dto.request.admin.AdminGroupInsertReq;
import com.crud.cloud.evanliu2968.dto.request.admin.AdminGroupUpdateReq;
import com.crud.cloud.evanliu2968.dto.response.admin.AdminGroupRes;
import com.crud.cloud.evanliu2968.po.admin.AdminGroupPO;
import com.crud.cloud.evanliu2968.po.admin.AdminGroupRefPO;

import java.util.List;

/**
 * <p>
 * 用户组  服务类
 * </p>
 */
public interface AdminGroupService extends IService<AdminGroupPO> {

    /**
     * 根据用户id查询所有用户组
     */

    List<AdminGroupRes> listGroupByAdminId(AdminGroupRefPO tAdminGroupRef);;

    /**
     * 查询用户组列表信息
     */

    ResponseData selectGroupList();

    /**
     * 保存用户组信息
     */
    ResponseData insertAdminGroup(AdminGroupInsertReq request);

    /**
     * 删除分组
     */
    ResponseData deleteAdminGroup(Integer id,String groupCode);

    /**
     * 修改用户组信息
     */
    ResponseData batchdeleteAdminGroup(Integer[] ids);

    /**
     * 修改用户组添加用户账号
     */
    ResponseData addMainsToGroup(Integer id, AddMainsToGroupReq request);

    /**
     * 删除用户组添加用户账号
     */
    ResponseData deleteMainsToGroup(Integer id, AddMainsToGroupReq request);
    /**
     * 修改分组名称
     */
    ResponseData updateAdminGroup(Integer id,AdminGroupUpdateReq request);

    /**
     * 查询所有有效用户组信息
     *
     * @return
     */
    List<AdminGroupPO> getAllEnabledGroups();
}
