package com.crud.cloud.evanliu2968.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("分页公共参数对象")
@Data
public class PageReq {

    @ApiModelProperty("页码")
    private Integer page = 1;

    @ApiModelProperty("分页大小")
    private Integer size = 10;

    public PageReq(){}

    public PageReq(Integer page,Integer size){
        this.page = page;
        this.size = size;
    }

}
