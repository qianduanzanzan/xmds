package com.atxiaoming.controller;


import com.atxiaoming.service.impl.ProdCategoryServiceImpl;
import com.atxiaoming.vo.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "产品分类相关接口")
@RestController
@RequestMapping("/cus/prodCategory")
@Slf4j
public class CusProdCate {

    @Autowired
    private ProdCategoryServiceImpl prodCategoryService;

    @ApiOperation(value = "获取全部分类" ,  notes="获取全部分类")
    @RequestMapping(value="/getAllCategory",method= RequestMethod.POST)
    public RespBean getAllCategory(){
        return prodCategoryService.getAllCategory();
    }
}
