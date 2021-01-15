package com.atxiaoming.controller;


import com.atxiaoming.service.impl.UserServiceImpl;
import com.atxiaoming.vo.LoginVo;
import com.atxiaoming.vo.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(description = "登录相关接口")
@RestController
@RequestMapping("")
@Slf4j
public class LoginController {

    @Autowired
    private UserServiceImpl userServiceImpl;


    @ApiOperation(value = "登录" ,  notes="登录接口")
    @RequestMapping(value="/login",method= RequestMethod.POST)
    public RespBean login(@RequestBody LoginVo loginVo){
        return userServiceImpl.login(loginVo);
    }

    @ApiOperation(value = "退出登录" ,  notes="退出登录接口")
    @RequestMapping(value="/logout",method= RequestMethod.POST)
    public RespBean login(@RequestHeader("token") String token){
        return userServiceImpl.logout(token);
    }
}
