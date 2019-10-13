package com.crud.cloud.evanliu2968.dto.response.admin;

import com.crud.cloud.evanliu2968.dto.request.admin.PostReq;
import com.crud.cloud.evanliu2968.po.DeptPostRefPO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "DeptRes")
public class DeptRes {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键id")
    private Integer id;
    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    private String name;
    /**
     * 上级部门主键
     */
    @ApiModelProperty(value = "上级部门主键")
    private Integer parentId;
    /**
     * 创建管理员主键
     */
    @ApiModelProperty(value = "创建管理员主键")
    private Integer createId;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;
    /**
     * 部门名称拼音
     */
    @ApiModelProperty(value = "部门名称拼音")
    private String departNamePy;
    /**
     * 部门类型
     */
    @ApiModelProperty(value = "部门类型")
    private String departType;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
    /**
     * 修改人
     */
    @ApiModelProperty(value = "主键id")
    private Integer updateId;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String note;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private Integer number = 0;

    /**
     * 岗位列表信息
     */
    @ApiModelProperty(value = "岗位列表信息")
    private List<PostReq> postReqList;

    /**
     * 岗位列表信息
     */
    @ApiModelProperty(value = "岗位列表信息")
    private List<PostRes> postResList;

    /**
     * 岗位列表信息
     */
    @ApiModelProperty(value = "子节点")
    private List<DeptRes> children;

    /**
     * 子部门个税
     */
    @ApiModelProperty(value = "子部门个数")
    private Integer childrenCount;

    /**
     * 部门类型
     */
    @ApiModelProperty(value = "部门类型")
    private String deptClass;

    /**
     * 岗位ids
     */
    @ApiModelProperty(value = "部门类型")
    private List<Integer> postIds;

    /**
     * 岗位列表
     */
    @ApiModelProperty(value = "岗位列表")
    private List<DeptPostRefPO> deptPostRefPOList;


}
