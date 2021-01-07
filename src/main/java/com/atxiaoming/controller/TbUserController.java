package com.atxiaoming.controller;


import com.atxiaoming.entity.TbUser;
import com.atxiaoming.service.impl.TbUserServiceImpl;
import com.atxiaoming.vo.RespBean;
import com.atxiaoming.vo.UserIdVo;
import com.atxiaoming.vo.UserInfoVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 小明
 * @since 2020-12-24
 */
@RestController
@RequestMapping("/user")
public class TbUserController {

    @Autowired
    private TbUserServiceImpl userServiceI;

    @ApiOperation(value = "添加用户" ,  notes="添加用户")
    @RequestMapping(value="/add",method= RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public RespBean addUser(@RequestBody TbUser user){
        return userServiceI.addUser(user);
    }

    @ApiOperation(value = "获取用户信息" ,  notes="用ID获取用户信息")
    @RequestMapping(value="/getInfo",method= RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public RespBean getUserInfo(@RequestBody UserIdVo userIdVo){
        return userServiceI.getUser(userIdVo);
    }
}
