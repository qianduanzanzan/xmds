package com.atxiaoming.controller;


import com.atxiaoming.service.impl.CusAddressServiceImpl;
import com.atxiaoming.vo.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 小明
 * @since 2021-02-01
 */
@Api(value = "用户地址相关接口")
@RestController
@RequestMapping("/cus/address")
@Slf4j
public class CusAddressController {

    @Autowired
    private CusAddressServiceImpl cusAddressServiceImpl;

    @ApiOperation(value = "添加地址" ,  notes="添加地址")
    @RequestMapping(value="/addAddress",method= RequestMethod.POST)
    public RespBean addAddress(@RequestParam("province") String province, @RequestParam("phone") String phone,@RequestParam("city") String city,@RequestParam("area") String area,@RequestParam("detail") String detail){
        return cusAddressServiceImpl.addAddress(province,phone,city,area,detail);
    }
}
