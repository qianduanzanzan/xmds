package com.atxiaoming.controller;


import com.atxiaoming.mapper.CustomerMapper;
import com.atxiaoming.service.impl.CustomerServiceImpl;
import com.atxiaoming.vo.CusLoginVo;
import com.atxiaoming.vo.LoginVo;
import com.atxiaoming.vo.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 小明
 * @since 2021-01-23
 */
@Api(description = "客户登录相关接口")
@RestController
@RequestMapping("")
@Slf4j
public class CustomerController {
    @Autowired
    private CustomerServiceImpl customerServiceI;

    @ApiOperation(value = "登录" ,  notes="登录接口")
    @RequestMapping(value="/cus/login",method= RequestMethod.POST)
    public RespBean login(@RequestParam("phone") String phone,@RequestParam("password") String password){
        return customerServiceI.login(phone,password);
    }

    @ApiOperation(value = "注册" ,  notes="注册接口")
    @RequestMapping(value="/cus/register",method= RequestMethod.POST)
    public RespBean login(@RequestParam("phone") String phone,@RequestParam("alias") String alias,@RequestParam("password") String password,@RequestParam("avatar") String avatar){
        return customerServiceI.register(alias,phone,password,avatar);
    }

    @ApiOperation(value = "通过token获取用户信息" ,  notes="通过token获取用户信息")
    @RequestMapping(value="/cus/getCusInfoByToken",method= RequestMethod.POST)
    public RespBean getCusInfoByToken(@RequestHeader("token") String token){
        return customerServiceI.getCusInfoByToken(token);
    }

    @ApiOperation(value = "添加地址" ,  notes="添加地址")
    @RequestMapping(value="/cus/addAddress",method= RequestMethod.POST)
    public RespBean addAddress(@RequestParam("address") String address,@RequestParam("cusId") String cusId){
        return customerServiceI.addAddress(address,cusId);
    }
}
