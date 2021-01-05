package com.atxiaoming.controller;


import com.atxiaoming.entity.OssTemplate;
import com.atxiaoming.vo.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Api(description = "图片上传接口")
@RestController
@RequestMapping("/pic")
@Slf4j
public class PicController {

    @Autowired
    private OssTemplate ossTemplate;

    @ApiOperation(value = "批量上传" ,  notes="批量上传图片")
    @RequestMapping(value="/uploadList",method= RequestMethod.POST)
    public RespBean uploadList(@RequestParam("filenames") MultipartFile[] filenames) throws IOException {
        List<String> filePathList = new ArrayList<String>();


        try {
            for (MultipartFile filename : filenames) {
                //获取源文件名
                String oldName = filename.getOriginalFilename();
                //获取输入流
                InputStream inputStream = filename.getInputStream();

                //防止文件重名
                String newFileName = UUID.randomUUID().toString().replace("-", "").substring(4, 16)+"_"+oldName;

                String uplosdFilePath = ossTemplate.upload(inputStream, newFileName);

                filePathList.add(uplosdFilePath);

            }

            return RespBean.success(filePathList);
        } catch (Exception e) {

//            e.printStackTrace();
            System.out.println(e);
            return RespBean.error(null);
        }
    }

    @ApiOperation(value = "单个上传" ,  notes="单个文件上传")
    @RequestMapping(value="/upload",method= RequestMethod.POST)
    public RespBean upload(@RequestParam("filename") MultipartFile filename) throws IOException {

        try {
            String oldName = filename.getOriginalFilename();
            InputStream inputStream = filename.getInputStream();
            String newFileName = UUID.randomUUID().toString().replace("-", "").substring(4, 16)+"_"+oldName;
            String uplosdFilePath = ossTemplate.upload(inputStream, newFileName);
            return RespBean.success(uplosdFilePath);
        } catch (Exception e) {

//            e.printStackTrace();
            System.out.println(e);
            return RespBean.error(null);
        }
    }
}
