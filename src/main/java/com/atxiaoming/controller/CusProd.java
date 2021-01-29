package com.atxiaoming.controller;


import com.atxiaoming.service.impl.ProdCategoryServiceImpl;
import com.atxiaoming.service.impl.ProdServiceImpl;
import com.atxiaoming.vo.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "用户产品相关接口")
@RestController
@RequestMapping("/cus/prod")
@Slf4j
public class CusProd {

    @Autowired
    private ProdServiceImpl prodService;

    @ApiOperation(value = "根据分类获取产品信息" ,  notes="根据分类获取产品信息")
    @RequestMapping(value="/getProdByCate",method= RequestMethod.POST)
    public RespBean getAllCategory(@RequestParam("cateId") Integer cateId){
        return prodService.getProdByCate(cateId);
    }

    @ApiOperation(value = "获取产品信息" ,  notes="获取产品信息")
    @RequestMapping(value="/getProdDetail",method= RequestMethod.POST)
    public RespBean getProdDetail(@RequestParam("prodId") Integer prodId){
        return prodService.getProdDetail(prodId);
    }
}
