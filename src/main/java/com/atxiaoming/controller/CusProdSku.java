package com.atxiaoming.controller;

import com.atxiaoming.entity.ProdSku;
import com.atxiaoming.service.impl.ProdSkuServiceImpl;
import com.atxiaoming.vo.CusProdSkuRequestVo;
import com.atxiaoming.vo.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Api(value = "用户产品库存相关接口")
@RestController
@RequestMapping("/cus/prodSku")
@Slf4j
public class CusProdSku {
    @Autowired
    private ProdSkuServiceImpl prodSkuServiceImpl;

    @ApiOperation(value = "通过属性值获取库存" ,  notes="通过属性值获取库存")
    @RequestMapping(value="/getSkuByAttrs",method= RequestMethod.POST)
    public RespBean addProdSku(@RequestParam(value="attr1Id",required=true) Integer attr1Id,
                               @RequestParam(value="attr2Id",required=false) Integer attr2Id,
                               @RequestParam(value="attr3Id",required=false) Integer attr3Id,
                               @RequestParam(value="prodId",required=true) Integer prodId){
        return prodSkuServiceImpl.getSkuByAttrs(attr1Id,attr2Id,attr3Id,prodId);
    }
}
