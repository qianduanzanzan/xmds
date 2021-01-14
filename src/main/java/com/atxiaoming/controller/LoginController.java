package com.atxiaoming.controller;


import com.atxiaoming.service.impl.UserServiceImpl;
import com.atxiaoming.vo.LoginVo;
import com.atxiaoming.vo.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "登录接口")
@RestController
@RequestMapping("")
@Slf4j
public class LoginController {

    @Autowired
    private UserServiceImpl userServiceI;

    @ApiOperation(value = "登录" ,  notes="登录接口")
    @RequestMapping(value="/login",method= RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public RespBean doLogin(@RequestBody LoginVo loginVo){
        return userServiceI.doLogin(loginVo);
    }


}
