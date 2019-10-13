package com.crud.cloud.evanliu2968.service.admin;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.crud.cloud.evanliu2968.constant.admin.DataAuthorityAdminRefEnum;
import com.crud.cloud.evanliu2968.dto.ResponseData;
import com.crud.cloud.evanliu2968.dto.request.admin.DataAuthorityAdminListReq;
import com.crud.cloud.evanliu2968.dto.request.admin.DeleteDataAuthorityAdminReq;
import com.crud.cloud.evanliu2968.dto.request.admin.InsertDataAuthorityAdminReq;
import com.crud.cloud.evanliu2968.dto.response.admin.DataAuthorityAdminRes;
import com.crud.cloud.evanliu2968.po.admin.DataAuthorityAdminRefPO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * 数据管理员关联表Service接口。
 * @author xinhua.zhou
 * @version v1.0.0
 */
public interface DataAuthorityAdminRefService extends IService<DataAuthorityAdminRefPO> {

    /**
     * 查询所在库的管理员列表信息
     * @param page 页码
     * @param size 大小
     * @param id 库数据id
     * @param request 查询参数
     * @return
     */
    Page<DataAuthorityAdminRes> listAdmin(Integer page, Integer size, Integer id,DataAuthorityAdminListReq request);


    /**
     * 批量添加管理员
     * @param id 库数据id
     * @param request 管理员ids
     * @return
     */
    Boolean insertAdmin(Integer id,InsertDataAuthorityAdminReq request);

    /**
     * 批量移除管理员
     * @param id 库数据id
     * @param request 管理员ids
     * @return
     */
    Boolean batchDeleteAdmin(Integer id,DeleteDataAuthorityAdminReq request);


    /**
     * 通过管理员id和类型查询分享库的ids
     * @param adminId 管理员
     * @param dataAuthorityAdminRefEnum 类型
     * @return
     */
    List<Integer> getDataIdsByAdminId(Integer adminId, DataAuthorityAdminRefEnum dataAuthorityAdminRefEnum);

}