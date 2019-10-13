package com.crud.cloud.evanliu2968.controller.admin;

import com.crud.cloud.evanliu2968.dto.ResponseData;
import com.crud.cloud.evanliu2968.dto.response.admin.PostAndDeptListRes;
import com.crud.cloud.evanliu2968.dto.response.admin.PostRes;
import com.crud.cloud.evanliu2968.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 机构管理接口
 *
 * @author xinhua.zhou
 * @date 2019-06-10
 */
@Api(tags = "crud_岗位_岗位管理")
@RequestMapping(path = "/evanliu2968/post", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class AdminPostController {

    private PostService postService;

    @Autowired
    public AdminPostController(PostService postService) {
        this.postService = postService;
    }

    @ApiOperation(nickname = "adminDeptListPost", value = "查询部门列表信息")
    @GetMapping(value = "/listPost", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData<List<PostRes>> listPost() {
        List<PostRes> postResList = this.postService.listPost();
        return ResponseData.success(postResList);
    }

    @ApiOperation(nickname = "likeListPost", value = "模糊查询岗位名称")
    @GetMapping(value = "/likeListPost", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData<List<PostAndDeptListRes>> likeListPost(String str) {
        List<PostAndDeptListRes> postResList = this.postService.likeListPost(str);
        return ResponseData.success(postResList);
    }
}
