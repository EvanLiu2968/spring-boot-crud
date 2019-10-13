package com.crud.cloud.evanliu2968.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * file。
 *
 * @author shuai.luo
 * @version v1.0.0
 */
@Data
@ApiModel(value = "文件返回对象")
public class FileRes {

    /**
     * 文件上传记录主键。
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 文件名。
     */
    @ApiModelProperty(value = "文件名")
    private String fileName;

    /**
     * 文件地址。
     */
    @ApiModelProperty(value = "文件地址")
    private String fileUrl;

    /**
     * 文件类型。
     */
    @ApiModelProperty(value = "文件类型")
    private String fileType;

    /**
     * 文件大小。
     */
    @ApiModelProperty(value = "文件大小")
    private String fileSize;

    /**
     * 下载次数。
     */
    @ApiModelProperty(value = "下载次数")
    private Integer fileDownCount;

    /**
     * 创建者。
     */
    @ApiModelProperty(value = "创建者")
    private String createName;

    /**
     * 创建时间。
     */
    @ApiModelProperty(value = "创建时间")
    private String createTime;

}