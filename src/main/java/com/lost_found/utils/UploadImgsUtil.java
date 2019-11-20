package com.lost_found.utils;

import com.lost_found.common.ServerResponse;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public class UploadImgsUtil
{
    public static ServerResponse uploadImg(HttpServletRequest request, MultipartFile file) throws IOException
    {
        //检查图片的大小
        boolean flag = FileUtil.checkFileSize(file.getSize(), 10, "M");
        if (!flag)
        {
            //图片大小超限
            return ServerResponse.createByErrorMessage("图片大小超过限制(10M), 请重新上传");
        }

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
                    // 新的图片的名称
                    String trueFileName = System.currentTimeMillis() + originalFilename;
                    // 设置存放图片文件的路径
                    path = realPath + "/uploadImg/" + trueFileName;
                    File file1 = new File(realPath + "/uploadImg");
                    if (!file1.exists())
                    {
                        file1.mkdirs();
                    }
                    //把图片存储到服务器中
                    file.transferTo(new File(path));
                    return ServerResponse.createBySuccessMessage("上传成功!", path);
                }
                return ServerResponse.createByErrorMessage("上传图片失败, 文件类型错误");
            }
            else
            {
                return ServerResponse.createByErrorMessage("上传图片失败, 请重新上传");
            }
        }
        return ServerResponse.createByErrorMessage("上传图片失败, 请重新上传");
    }
}
