package com.atxiaoming.controller;


import com.atxiaoming.entity.ProdSku;
import com.atxiaoming.service.impl.ShopCartServiceImpl;
import com.atxiaoming.vo.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 小明
 * @since 2021-01-29
 */
@Api(description = "购物车相关")
@RestController
@RequestMapping("/cus/shopCart")
@Slf4j
public class ShopCartController {
    @Autowired
    private ShopCartServiceImpl shopCartServiceImpl;

    @ApiOperation(value = "添加购物车" ,  notes="添加购物车")
    @RequestMapping(value="/add",method= RequestMethod.POST)
    public RespBean addProdSku(@RequestParam("phone") String phone,
                               @RequestParam("prodId") Integer prodId,
                               @RequestParam("prodSkuId") Integer prodSkuId,
                               @RequestParam("quantity") Integer quantity
                               ){
        return shopCartServiceImpl.addShopCart(phone,prodId,prodSkuId,quantity);
    }
}
