package com.crud.cloud.evanliu2968.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.crud.cloud.evanliu2968.dto.response.FileGroupRes;
import com.crud.cloud.evanliu2968.dto.response.FileRes;
import com.crud.cloud.evanliu2968.po.FilePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * fileMapper接口。
 *
 * @author guohao.yang@crud.com
 * @version v1.0.0
 */
public interface FileMapper extends BaseMapper<FilePO> {

    /**
     * 根据类型分组查询文件数量
     * @param idList
     * @return
     */
    List<FileGroupRes> getFileGroupListByFileType(@Param("idList") List<Integer> idList,
                                                  @Param("taskType") String taskType,
                                                  @Param("createId") Integer createId);

    /**
     * 根据文件类型查询文件列表总数
     * @param idList
     * @param taskType
     * @param fileType
     * @param createId
     * @return
     */
    Integer getFileListByFileTypeCount(@Param("idList")List<Integer> idList,
                          @Param("taskType") String taskType,
                          @Param("fileType") String fileType,
                          @Param("createId") Integer createId);

    /**
     * 根据文件类型查询文件列表
     * @param idList
     * @param fileType
     * @return
     */
    List<FileRes> getFileListByFileType(@Param("idList")List<Integer> idList,
                                        @Param("taskType") String taskType,
                                        @Param("fileType") String fileType,
                                        @Param("createId") Integer createId,
                                        @Param("offset") Integer offset,
                                        @Param("limit") Integer limit);

    /**
     * 根据岗位分组查询文件数量
     * @param idList
     * @param taskType
     * @param createId
     * @return
     */
    List<FileGroupRes> getFileGroupListByPostName(@Param("idList")List<Integer> idList,
                                                  @Param("taskType") String taskType,
                                                  @Param("createId") Integer createId);

    /**
     * 根据岗位名称查询文件列表总数
     * @param idList
     * @param taskType
     * @param postName
     * @param createId
     * @return
     */
    Integer getFileListByPostNameCount(@Param("idList")List<Integer> idList,
                          @Param("taskType") String taskType,
                          @Param("postName") String postName,
                          @Param("createId") Integer createId);

    /**
     * 根据岗位名称查询文件列表
     * @param idList
     * @param taskType
     * @param postName
     * @param createId
     * @return
     */
    List<FileRes> getFileListByPostName(@Param("idList")List<Integer> idList,
                                       @Param("taskType") String taskType,
                                       @Param("postName") String postName,
                                       @Param("createId") Integer createId,
                                       @Param("offset") Integer offset,
                                       @Param("limit") Integer limit);

    /**
     * 按任务阶段分组查询
     * @param idList
     * @return
     */
    List<FileGroupRes> getFileGroupListByNodeType(@Param("idList") List<Integer> idList,
                                                  @Param("taskType") String taskType,
                                                  @Param("createId") Integer createId);

    /**
     * 根据任务类型查询文件列表总数
     * @param idList
     * @param taskType
     * @param nodeType
     * @param createId
     * @return
     */
    Integer getFileListByNodeTypeCount(@Param("idList")List<Integer> idList,
                          @Param("taskType") String taskType,
                          @Param("nodeType") String nodeType,
                          @Param("createId") Integer createId);

    /**
     * 根据任免分页查询文件列表
     *
     * @param idList
     * @param taskType
     * @param personName
     * @param createId
     * @param offset
     * @param limit
     * @return
     */
    List<FileRes> getFileListByPerson(@Param("idList") List<Integer> idList,
                                      @Param("taskType") String taskType,
                                      @Param("personName") String personName,
                                      @Param("createId") Integer createId,
                                      @Param("offset") Integer offset,
                                      @Param("limit") Integer limit);

    /**
     * 按人分组查询
     * @param idList
     * @param taskType
     * @param createId
     * @return
     */
    List<FileGroupRes> getFileGroupListByPerson(@Param("idList") List<Integer> idList,
                                                @Param("taskType") String taskType,
                                                @Param("createId") Integer createId);

    /**
     * 根据任务类型查询文件列表
     * @param idList
     * @param nodeType
     * @return
     */
    List<FileRes> getFileListByNodeType(@Param("idList")List<Integer> idList,
                                       @Param("taskType") String taskType,
                                       @Param("nodeType") String nodeType,
                                       @Param("createId") Integer createId,
                                       @Param("offset") Integer offset,
                                       @Param("limit") Integer limit);


    /**
     * 根据人查询文件列表count
     * @param idList
     * @param taskType
     * @param personName
     * @param createId
     * @return
     */
    Integer getFileListByPersonCount(@Param("idList") List<Integer> idList,
                                     @Param("taskType") String taskType,
                                     @Param("personName") String personName,
                                     @Param("createId") Integer createId);

    /**
     * 根据人查询文件列表
     * @param idList
     * @param taskType
     * @param detailType
     * @param createId
     * @param offset
     * @param limit
     * @return
     */
    List<FileRes> getFileListByPersonName(@Param("idList")List<Integer> idList,
                                          @Param("taskType") String taskType,
                                          @Param("detailType") String detailType,
                                          @Param("createId") Integer createId,
                                          @Param("offset") Integer offset,
                                          @Param("limit") Integer limit);
}
