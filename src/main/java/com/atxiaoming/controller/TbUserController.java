package com.atxiaoming.controller;


import com.atxiaoming.entity.TbUser;
import com.atxiaoming.service.impl.TbUserServiceImpl;
import com.atxiaoming.vo.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "修改头像" ,  notes="修改用户头像")
    @RequestMapping(value="/editAvatar",method= RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public RespBean editAvatar(@RequestHeader("token") String token, @RequestBody ChangeAvatarVo avatarVo){
        return userServiceI.changeAvatar(token,avatarVo);
    }

    @ApiOperation(value = "修改用户信息" ,  notes="修改用户信息")
    @RequestMapping(value="/edit",method= RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public RespBean editUserInfo(@RequestHeader("token") String token,@RequestBody UpdateUserVo user){
        return userServiceI.updateUserInfo(token,user);
    }

    @ApiOperation(value = "用户列表" ,  notes="获取用户列表")
    @RequestMapping(value="/getList",method= RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public RespBean editUserInfo(@RequestBody UserPageNationVo userPageNationVo){
        return userServiceI.getList(userPageNationVo);
    }
}
