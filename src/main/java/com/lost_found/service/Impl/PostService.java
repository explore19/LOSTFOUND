package com.lost_found.service.Impl;


import com.lost_found.common.Const;
import com.lost_found.common.ServerResponse;
import com.lost_found.dao.PostMapper;
import com.lost_found.pojo.Post;
import com.lost_found.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PostService implements IPostService
{
    @Autowired
    PostMapper postMapper;


    @Override
    public ServerResponse save(Post post)
    {
        post.setStatus(Const.STATUS.NEED_EXAMINE_POST.getStatus());  //需要审核
        post.setCreateTime(new Date());
        post.setUpdateTime(new Date());

        //将新添加的post信息加入到数据库中
        return postMapper.insert(post) > 0 ?
                ServerResponse.createBySuccessMessage("发布成功") :
                ServerResponse.createByErrorMessage("发布失败,请稍后再试");
    }

    @Override
    public ServerResponse remove(Integer id)
    {
        return postMapper.deleteByPrimaryKey(id) > 0 ?
                ServerResponse.createBySuccessMessage("删除成功") :
                ServerResponse.createByErrorMessage("删除失败");
    }

    @Override
    public ServerResponse update(Post post)
    {
        post.setUpdateTime(new Date());
        return postMapper.updateByPrimaryKeySelective(post) > 0 ?
                ServerResponse.createBySuccessMessage("更新帖子成功") :
                ServerResponse.createByErrorMessage("删除失败");
    }

    /**
     * 根据postId查询
     *
     * @param id
     * @return
     */
    @Override
    public ServerResponse<Post> queryById(Integer id)
    {
        Post post = postMapper.selectByPrimaryKey(id);
        if (null != post)
        {
            //查询成功
            return ServerResponse.createBySuccess(post);
        }
        return ServerResponse.createByErrorMessage("未找到帖子信息");
    }


    /**
     * 根据用户id来查询其全部发布
     *
     * @param userId
     * @return
     */
    @Override
    public ServerResponse<List<Post>> queryByUserId(Integer userId)
    {
        return ServerResponse.createBySuccess(postMapper.queryByUserId(userId));
    }


    /**
     * 上传帖子的图片并将图片的地址存储到数据库
     *
     * @param file
     * @return
     */
    @Override
    public ServerResponse uploadImg(HttpServletRequest request, MultipartFile file) throws IOException
    {
        request.setCharacterEncoding("utf-8");
        if (!file.isEmpty())
        {
            String originalFilename = file.getOriginalFilename();//获取图片文件的名字
            String path = null;
            String type = null; //图片类型
            type = originalFilename.indexOf(".") != -1 ?
                    originalFilename.substring(originalFilename.lastIndexOf(".") + 1, originalFilename.length()) : null;

            if (type != null)
            {
                if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase()))
                {
                    // 项目在容器中实际发布运行的根路径
                    String realPath = request.getSession().getServletContext().getRealPath("/");
                    // 自定义的文件名称
                    String trueFileName = String.valueOf(System.currentTimeMillis()) + originalFilename;
                    // 设置存放图片文件的路径
                    path = realPath + "/uploadImg/" + trueFileName;
//                    postMapper.insert(path);
                    file.transferTo(new File(path));
                }
                else
                {
                    return ServerResponse.createByErrorMessage("上传图片失败, 文件类型错误");
                }
            }
            else
            {
                return ServerResponse.createByErrorMessage("上传图片失败, 请重新上传");
            }
        }
        return ServerResponse.createBySuccess("文件上传成功");

      /*  <!-- 文件上传配置 -->
    <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <!-- 默认编码 -->
        <property name="defaultEncoding" value="UTF-8"/>
        <!-- 上传文件大小限制为31M，31*1024*1024 -->
        <property name="maxUploadSize" value="32505856"/>
        <!-- 内存中的最大值 -->
        <property name="maxInMemorySize" value="4096"/>
    </bean>*/

    }


}
