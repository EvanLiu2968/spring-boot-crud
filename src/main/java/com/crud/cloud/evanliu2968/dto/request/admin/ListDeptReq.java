package com.crud.cloud.evanliu2968.dto.request.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "ListDeptReq")
public class ListDeptReq {

   public ListDeptReq(Integer archivesStatus, Integer auditStatus, String keyWord) {
        this.archivesStatus = archivesStatus;
        this.auditStatus = auditStatus;
        this.keyWord = "".equals(keyWord)||keyWord==null?null:keyWord.split(",");
    }

    /**
     * 档案状态
     */
    @ApiModelProperty(value = " 档案状态")
    private Integer archivesStatus;
    /**
     * 审核状态
     */
    @ApiModelProperty(value = "审核状态")
    private Integer auditStatus;

    /**
     * 关键字
     */
    @ApiModelProperty(value = " 关键字")
    private String[] keyWord;
  /*  public String [] getStaffNameOrDepartName() {
        String[] split = this.staffNameOrDepartName.split("\\,");
        return split;
    }*/


}
