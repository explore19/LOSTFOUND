package com.lost_found.controller;

import com.lost_found.common.ServerResponse;
import com.lost_found.utils.FileUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.Random;

@Api(tags = "上传图片接口")
@RestController
@RequestMapping("/upload")
public class UploadController
{
    @Value("${upload.picture.path}")
    private String uploadPicturePath;

    @PostMapping("/image")
    public ServerResponse uploadImg( @RequestParam("img") MultipartFile file) throws IOException
    {
        String url="";
        //检查图片的大小
            boolean flag = FileUtil.checkFileSize(file.getSize(), 5, "M");


            if (!flag)
            {
                //图片大小超限
                return ServerResponse.createByErrorMessage("图片大小超过限制(5M), 请重新上传");
            }
            if (!file.isEmpty())
            {
                String originalFilename = file.getOriginalFilename();//获取图片文件的名字
                String path = null;
                String type = null; //图片类型
                type = originalFilename.indexOf(".") != -1 ?
                        originalFilename.substring(originalFilename.lastIndexOf(".") + 1, originalFilename.length())
                        : null;

                if (type != null)
                {
                    if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase()))
                    {
                        // 新的图片的名称
                        String trueFileName = System.currentTimeMillis() + getRandomString(15);
                        // 设置存放图片文件的路径
                        path = uploadPicturePath + trueFileName;
                        File file1 = new File(uploadPicturePath);
                        if (!file1.exists())
                        {
                            file1.mkdirs();
                        }
                        //把图片存储到服务器中
                        file.transferTo(new File(path));
                        url = trueFileName;
                        return ServerResponse.createBySuccessMessage("上传成功!",url);
                    }
                    return ServerResponse.createByErrorMessage("上传图片失败, 文件类型错误");
                }
            }
            return ServerResponse.createByErrorMessage("上传图片失败, 请重新上传");
    }

    private static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}


