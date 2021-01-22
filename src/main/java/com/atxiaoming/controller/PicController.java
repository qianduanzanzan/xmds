package com.atxiaoming.controller;


import com.atxiaoming.entity.OssTemplate;
import com.atxiaoming.entity.ProdAttr;
import com.atxiaoming.utils.CommonUtils;
import com.atxiaoming.vo.RespBean;
import com.atxiaoming.vo.RespBeanEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Api(description = "图片相关接口")
@RestController
@RequestMapping("/pic")
@Slf4j
public class PicController {

    @Autowired
    private OssTemplate ossTemplate;

    @ApiOperation(value = "上传图片" ,  notes="上传图片")
    @RequestMapping(value="/upload",method= RequestMethod.POST)
    public RespBean upload(@RequestParam(value = "file") MultipartFile file){
        try{
            String oldName = file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();
            String newFileName = UUID.randomUUID().toString().replace("-", "").substring(4, 16)+"_"+oldName;
            String uploadFilePath = ossTemplate.upload(inputStream, newFileName);
            return  RespBean.success(uploadFilePath);
        }catch (Exception e){
            return  RespBean.error(RespBeanEnum.ERROR);
        }
    }

    @ApiOperation(value = "批量上传图片" ,  notes="批量上传图片")
    @RequestMapping(value="/uploads",method= RequestMethod.POST)
    public RespBean uploads(@RequestParam(value = "files") MultipartFile[] files){
        try{
            List<String> uploadFilePathList = new ArrayList<>();
            for(MultipartFile file: files){
                String oldName = file.getOriginalFilename();
                InputStream inputStream = file.getInputStream();
                String newFileName = UUID.randomUUID().toString().replace("-", "").substring(4, 16)+"_"+oldName;
                String uploadFilePath = ossTemplate.upload(inputStream, newFileName);
                uploadFilePathList.add(uploadFilePath);
            }
            return  RespBean.success(uploadFilePathList);
        }catch (Exception e){
            return  RespBean.error(RespBeanEnum.ERROR);
        }
    }

    @ApiOperation(value = "删除图片" ,  notes="删除图片")
    @RequestMapping(value="/del",method= RequestMethod.POST)
    public RespBean del(@RequestParam(value = "fileUrl") String fileUrl){
        try{
            String fileName = CommonUtils.getFileName(fileUrl);
            ossTemplate.delete(fileName);
            return  RespBean.success();
        }catch (Exception e){
            return  RespBean.error(RespBeanEnum.ERROR);
        }
    }
}
