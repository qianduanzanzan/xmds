package com.atxiaoming.controller;


import com.atxiaoming.entity.ProdAttr;
import com.atxiaoming.service.impl.MenuServiceImpl;
import com.atxiaoming.service.impl.ProdAttrServiceImpl;
import com.atxiaoming.service.impl.ProdServiceImpl;
import com.atxiaoming.vo.EditMenuVo;
import com.atxiaoming.vo.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 小明
 * @since 2021-01-15
 */
@Api(description = "产品属性关接口")
@RestController
@RequestMapping("/prodAttr")
@Slf4j
public class ProdAttrController {

    @Autowired
    private ProdAttrServiceImpl prodAttrServiceImpl;

    @ApiOperation(value = "新增产品属性" ,  notes="新增产品属性")
    @RequestMapping(value="/add",method= RequestMethod.POST)
    public RespBean editMenu(@RequestBody List<ProdAttr> prodAttrs){
        return prodAttrServiceImpl.addProdAttr(prodAttrs);
    }
}
