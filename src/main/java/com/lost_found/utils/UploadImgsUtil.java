package com.lost_found.utils;

import com.lost_found.common.ServerResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Api(tags = "上传图片接口")
@RestController
@RequestMapping("/upload")
public class UploadImgsUtil
{
    @Value("${upload.picture.path}")
    private String uploadPicturePath;

    @PostMapping("/img")
    public ServerResponse<String[]> uploadImg(MultipartFile[] files) throws IOException
    {
        String data[] = new String[files.length];
        //检查图片的大小
        for (int i = 0; i < files.length; i++)
        {
            System.out.println(i);
            boolean flag = FileUtil.checkFileSize(files[i].getSize(), 2, "M");
            if (!flag)
            {
                //图片大小超限
                return ServerResponse.createByErrorMessage("图片大小超过限制(2M), 请重新上传");
            }

            if (!files[i].isEmpty())
            {
                String originalFilename = files[i].getOriginalFilename();//获取图片文件的名字
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
                        String trueFileName = System.currentTimeMillis() + originalFilename;
                        // 设置存放图片文件的路径
                        path = uploadPicturePath + trueFileName;
                        File file1 = new File(uploadPicturePath);
                        if (!file1.exists())
                        {
                            file1.mkdirs();
                        }
                        //把图片存储到服务器中
                        files[i].transferTo(new File(path));
                        data[i] = trueFileName;
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
            else
            {
                return ServerResponse.createByErrorMessage("上传图片失败, 请重新上传");
            }
        }
        return ServerResponse.createBySuccessMessage("上传成功!", data);
    }
}


//@Component
//public class UploadImgsUtil
//{
////    @Value("${upload.picture.path}")
//    private static String uploadPicturePath = "F:/lostfound/upload/";
//
//    public static String[] uploadImg(MultipartFile[] files) throws IOException
//    {
//        String data[] = new String[files.length];
//        //检查图片的大小
//        for (int i = 0; i < files.length; i++)
//        {
//            boolean flag = FileUtil.checkFileSize(files[i].getSize(), 2, "M");
//            if (!flag)
//            {
//                //图片大小超限
//                data[0] = "-1";
//                return data;
//            }
//
//            if (!files[i].isEmpty())
//            {
//                String originalFilename = files[i].getOriginalFilename();//获取图片文件的名字
//                String path = null;
//                String type = null; //图片类型
//                type = originalFilename.indexOf(".") != -1 ?
//                        originalFilename.substring(originalFilename.lastIndexOf(".") + 1, originalFilename.length())
//                        : null;
//
//                if (type != null)
//                {
//                    if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase()))
//                    {
//                        // 新的图片的名称
//                        String trueFileName = System.currentTimeMillis() + originalFilename;
//                        // 设置存放图片文件的路径
//                        path = uploadPicturePath + trueFileName;
//                        File file1 = new File(uploadPicturePath);
//                        if (!file1.exists())
//                        {
//                            file1.mkdirs();
//                        }
//                        //把图片存储到服务器中
//                        files[i].transferTo(new File(path));
//                        data[i] = trueFileName;
//                    }
//                    else
//                    {
//                        data[0] = "0";
//                        return data;
//                    }
//                }
//                else
//                {
//                    data[0] = "0";
//                    return data;
//                }
//            }
//            else
//            {
//                data[0] = "0";
//                return data;
//            }
//        }
//        return data;
//    }
//}
