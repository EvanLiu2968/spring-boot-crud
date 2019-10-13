package com.crud.cloud.evanliu2968.service.template;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.crud.cloud.evanliu2968.dto.request.flow.InsertTemplateReq;
import com.crud.cloud.evanliu2968.dto.request.TemplateListReq;
import com.crud.cloud.evanliu2968.dto.request.UpdateTemplateReq;
import com.crud.cloud.evanliu2968.dto.response.TemplateRes;
import com.crud.cloud.evanliu2968.po.TemplatePO;

/** 模板服务类
 * @author xinhua.zhou
 * @date 2019-05-29
 */
public interface TemplateService extends IService<TemplatePO> {

    /**
     * 查询模板列表信息
     * @param page 页数
     * @param size 条数
     * @param request 查询对象
     * @return 查询列表
     */
    Page<TemplateRes> templateList(int page, int size, TemplateListReq request);

    /**
     * 查询具体的模板
     * @param id 模板id
     * @return 返回模板信息
     */
    TemplateRes getTemplate(Long id);

    /**
     * 删除模板
     * @param id 模板id
     * @return 返回删除是否成功的信息
     */
    boolean deleteTemplate(Long id);

    /**
     * 新增模板信息
     * @param request 新增模板信息的封装类
     * @return 返回新增是否成功的信息
     */
    boolean insertTemplate(InsertTemplateReq request);

    /**
     * 修改模板信息
     * @param id  模板id
     * @param request 修改模板信息的封装类
     * @return 返回修改是否成功的信息
     */
    boolean updateTemplate(Long id, UpdateTemplateReq request);
}
