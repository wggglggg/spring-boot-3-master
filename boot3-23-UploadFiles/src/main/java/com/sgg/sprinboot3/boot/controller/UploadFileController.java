package com.sgg.sprinboot3.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * ClassName: UploadFileController
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-07 13:49
 * @Version 1.0
 */
@Controller
public class UploadFileController {

    @PostMapping("/upload")
    public String uploadFile(@RequestParam( "upfile") MultipartFile multipartFile) {
        try {
            System.out.println("开始处理上传文件");
            //判断上传了文件
            if (!multipartFile.isEmpty()) {
                //上传文件的参数名称,也就是xxxx=xxxxxx.jpg
                String name = multipartFile.getName();
                //内容类型
                String contentType = multipartFile.getContentType();
                String fileSuffix = null;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
                Date date = new Date();
                ////原始文件名称，例如a.gif
                String filename = multipartFile.getOriginalFilename();
                if (filename.contains(".")) {
                    fileSuffix = filename.substring(filename.lastIndexOf("."));

                }
                //生成服务器使用的文件名称
                String newFileName = sdf.format(date) + "_" + UUID.randomUUID() + fileSuffix;
                //将文件写入到某文件夹下
                String path = "F://迅雷下载//尚硅谷SGG//动力节点//springboot3 2023//代码//JavaProject//upload//" + newFileName;

                multipartFile.transferTo(new File(path));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }finally {

            System.out.println("multipartFile = " + multipartFile);
        }
        return "redirect:index.html";
    }

    @PostMapping("/upload/more")
    public String uploadMore(@RequestParam("upfile") MultipartFile[] multipartFiles ){
        Map<String , Object> info = new HashMap<>();
        MultipartFile finalMultipartFile = null;
        try {
            System.out.println("开始处理上传more文件");

            for(MultipartFile multipartFile : multipartFiles){
                finalMultipartFile = multipartFile;
                String fileSuffix = null;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
                String date = sdf.format(new Date());
                //判断上传了文件
                if (StringUtils.hasText(multipartFile.getOriginalFilename())){
                    //原始文件名称，例如a.gif
                    String filename = multipartFile.getOriginalFilename();
                    //upfile=xxxxx.gif,
                    info.put("上传文件的参数名称", multipartFile.getName());
                    info.put("内容类型", multipartFile.getContentType());
                    if (filename.contains(".")){
                        fileSuffix = filename.substring(filename.lastIndexOf("."));
                    }
                    //生成服务器使用的文件名称
                    String newFileName = date + "_" + UUID.randomUUID() + fileSuffix;

                    String path = "F://迅雷下载//尚硅谷SGG//动力节点//springboot3 2023//代码//JavaProject//upload//" + newFileName;
                    //把文件保存到path目录
                    multipartFile.transferTo(new File(path));
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {

            System.out.println("finalMultipartFile = " + finalMultipartFile);
        }

        return "redirect:/index.html";
    }
}
