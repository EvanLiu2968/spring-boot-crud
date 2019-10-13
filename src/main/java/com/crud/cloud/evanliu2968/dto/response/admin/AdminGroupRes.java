package com.crud.cloud.evanliu2968.dto.response.admin;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "AdminGroupRes")
public class AdminGroupRes {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 角色
     * 名称
     */
    private String groupName;

    /**
     * 管理员账号数量
     */
    private String adminCount;

    /**
     * 分组编码
     */
    private String groupCode;

    /**
     * 父级id
     */
    private Integer parentId;

    /**
     * 是否有子分组 true:有  false：没有
     */
    private Boolean hasSon = false;

    List<AdminPermissionsRes> permissions;
}
