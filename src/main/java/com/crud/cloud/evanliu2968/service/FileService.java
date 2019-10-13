package com.crud.cloud.evanliu2968.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.crud.cloud.evanliu2968.dto.request.GetFileReq;
import com.crud.cloud.evanliu2968.dto.response.FileGroupRes;
import com.crud.cloud.evanliu2968.dto.response.FileRes;
import com.crud.cloud.evanliu2968.po.FilePO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * fileService接口。
 *
 * @author guohao.yang@crud.com
 * @version v1.0.0
 */
public interface FileService extends IService<FilePO> {
    /**
     * 上传人员照片。
     *
     * @param file
     * @param path
     * @return url
     */
    FileRes uploadFile(MultipartFile file, String path);


    /**
     * 根据类型查询文件分组信息
     * @param taskType
     * @param nodeType
     * @return
     */
    List<FileGroupRes> getGroupFileList(String taskType, String nodeType);

    /**
     * 根据文件类型查询文件列表
     * @param getFileReq
     * @return
     */
    Page<FileRes> getFileList(GetFileReq getFileReq);

    /**
     * 更新文件下载次数
     * @param fileId
     * @return
     */
    Integer updateFileDownCount(Integer fileId);
}