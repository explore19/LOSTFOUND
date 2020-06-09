package com.lost_found.service;

import com.lost_found.VO.ReplyTree;
import com.lost_found.common.ServerResponse;
import com.lost_found.form.QueryPostForm;
import com.lost_found.pojo.Post;
import com.lost_found.pojo.Reply;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
     * 根据QueryPostForm查询获得一定数目帖子
     * @param queryPostForm
     * @return
     */
    ServerResponse query(QueryPostForm queryPostForm);

    /**
     * 根据帖子id查询该帖子的所有回复
     * @param postId
     * @return
     */
    ServerResponse<ReplyTree> getAllReply(Integer postId);

   Integer getPostUserId(Integer postId);

   /**
        * @Author MRH0045
        * @Description 根据id审核帖子
        * @Date 22:30 2020/6/2
        * @Param [id, operation]
        * @return com.lost_found.common.ServerResponse
        **/
    ServerResponse AuditPost(Integer id,Integer operation);


}
