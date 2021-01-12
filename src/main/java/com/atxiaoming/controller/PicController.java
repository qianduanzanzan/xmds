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

//import static com.atxiaoming.service.impl.TbUserServiceImpl.getRespBean;

@Api(description = "图片上传接口")
@RestController
@RequestMapping("/pic")
@Slf4j
public class PicController {

//    @Autowired
//    private OssTemplate ossTemplate;
//
//    @ApiOperation(value = "单个上传" ,  notes="单个文件上传")
//    @RequestMapping(value="/upload",method= RequestMethod.POST)
//    public RespBean upload(@RequestParam("filename") MultipartFile filename) throws IOException {
//
//        try {
//            String uploadFilePath = getRespBean(filename, ossTemplate);
//            return RespBean.success(uploadFilePath);
//        } catch (Exception e) {
//
////            e.printStackTrace();
//            System.out.println(e);
//            return RespBean.error(null);
//        }
//    }
//
//    @ApiOperation(value = "删除图片" ,  notes="删除服务器图片")
//    @RequestMapping(value="/delete",method= RequestMethod.POST)
//    public RespBean delete(@RequestBody String filename) throws IOException {
//        try {
//            ossTemplate.delete(filename);
//            return RespBean.success();
//        } catch (Exception e) {
//            System.out.println(e);
//            return RespBean.error(null);
//        }
//    }
}
