package com.crud.cloud.evanliu2968.controller.file;

import com.baomidou.mybatisplus.plugins.Page;
import com.crud.cloud.evanliu2968.dto.ResponseData;
import com.crud.cloud.evanliu2968.dto.request.GetFileReq;
import com.crud.cloud.evanliu2968.dto.response.FileGroupRes;
import com.crud.cloud.evanliu2968.dto.response.FileRes;
import com.crud.cloud.evanliu2968.service.FileService;
import com.crud.cloud.evanliu2968.service.MailService;
import com.crud.cloud.evanliu2968.util.bigFile.BigFileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 上传文件管理接口
 * @author luoshuai
 */
@Api(tags = "上传文件_上传文件管理接口")
@RequestMapping(path = "/evanliu2968/file", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    MailService mailService;

    @Autowired
    private BigFileUtil fileUtil;

    @ApiOperation(nickname = "uploadFileController", value = "上传文件")
    @PostMapping(value = "/uploadFile", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData<String> uploadFile(@Valid @RequestBody MultipartFile file) {
        // 参数错误返回
        if (file.isEmpty()) {
            return ResponseData.fail("上传文件不能为空");
        }
        //成功返回
        return ResponseData.success(this.fileService.uploadFile(file, null));
    }

    /**
     *
     * @Description:
     *             接受文件分片，合并分片
     * @param md5value
     *             文件的MD5值
     * @param chunks
     *             当前所传文件的分片总数
     * @param chunk
     *             当前所传文件的当前分片数
     * @param id
     *             文件ID，如WU_FILE_1，后面数字代表当前传的是第几个文件,后续使用此ID来创建临时目录，将属于该文件ID的所有分片全部放在同一个文件夹中
     * @param name
     *             文件名称，如07-中文分词器和业务域的配置.avi
     * @param file  当前所传分片
     */
    @ResponseBody
    @RequestMapping(value = "/bigfileup")
    public String fileUpload(String md5value, String chunks, String chunk, String id, String name,
                              MultipartFile file,HttpServletRequest request) {
        return this.fileUtil.fileUpload(md5value, chunks, chunk, id, name, file, request);
    }



    @ApiOperation(nickname = "getGroupFileList", value = "文件分组查询")
    @PostMapping(value = "/getGroupFileList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData<List<FileGroupRes>> getGroupFileList(@Valid @RequestBody GetFileReq getFileReq){
        List<FileGroupRes> resultList = this.fileService.getGroupFileList(getFileReq.getTaskType(), getFileReq.getNodeType());

        return ResponseData.success(resultList);
    }

    @ApiOperation(nickname = "getFileList", value = "文件列表查询")
    @PostMapping(value = "/getFileList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData<Page<FileRes>> getFileList(@Valid @RequestBody GetFileReq getFileReq){
        return ResponseData.success(this.fileService.getFileList(getFileReq));
    }

    @ApiOperation(nickname = "updateFileDownCount", value = "更新文件下载次数")
    @PutMapping(value = "/updateFileDownCount/{fileId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData<Integer> updateFileDownCount(@PathVariable("fileId") Integer fileId){
        return ResponseData.success(this.fileService.updateFileDownCount(fileId));
    }
}
