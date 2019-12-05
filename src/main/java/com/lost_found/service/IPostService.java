package com.lost_found.service;

import com.lost_found.common.ServerResponse;
import com.lost_found.form.QueryPostForm;
import com.lost_found.pojo.Post;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface IPostService {

    /**
     * 发布寻物启事/拾物启示
     * @param post
     * @return
     */
    ServerResponse<String> save(Post post);

    /**
     * 根据id删除发布信息
     * @param id
     * @return
     */
    ServerResponse<String> remove(Integer id);

    /**
     * 根据Post对象来修改信息
     * @param post
     * @return
     */
    ServerResponse<String> update(Post post);


    /**
     * 根据postId查询
     * @param id
     * @return
     */
    ServerResponse queryById(Integer id);

    /**
     * 根据userId查询用户所有帖子
     * @param userId
     * @return
     */
    ServerResponse<List<Post>> queryByUserId(Integer userId);

    /**
     * 根据QueryPostForm查询获得一定数目帖子
     * @param queryPostForm
     * @return
     */
    ServerResponse query(QueryPostForm queryPostForm);


}
