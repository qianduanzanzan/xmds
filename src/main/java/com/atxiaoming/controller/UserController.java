package com.atxiaoming.controller;


import com.atxiaoming.entity.User;
import com.atxiaoming.service.impl.UserServiceImpl;
import com.atxiaoming.vo.ChangePasswordVo;
import com.atxiaoming.vo.RespBean;
import com.atxiaoming.vo.UpdateUserVo;
import com.atxiaoming.vo.UserPageNationVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 小明
 * @since 2021-01-13
 */
@Api(description = "用户相关接口")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @ApiOperation(value = "添加用户" ,  notes="添加用户")
    @RequestMapping(value="/addUser",method= RequestMethod.POST)
    public RespBean doLogin(@RequestParam("username") String username,
                            @RequestParam("account") String account,
                            @RequestParam("password") String password,
                            @RequestPart("avatar") MultipartFile avatar
    ){
        return userServiceImpl.addUser(username,account,password,avatar);
    }

    @ApiOperation(value = "修改用户信息" ,  notes="修改用户信息")
    @RequestMapping(value="/edit",method= RequestMethod.POST)
    public RespBean editUser(@RequestBody UpdateUserVo updateUserVo){
        return userServiceImpl.editUser(updateUserVo);
    }

    @ApiOperation(value = "修改用户状态" ,  notes="修改用户状态")
    @RequestMapping(value="/changeFlag",method= RequestMethod.POST)
    public RespBean changeFlag(@RequestParam("id") Integer id){
        return userServiceImpl.changeUserFlag(id);
    }

    @ApiOperation(value = "修改用户密码" ,  notes="修改用户密码")
    @RequestMapping(value="/changePassword",method= RequestMethod.POST)
    public RespBean changePassword(@RequestBody ChangePasswordVo changePasswordVo){
        return userServiceImpl.changePassword(changePasswordVo);
    }

    @ApiOperation(value = "分页获取用户列表" ,  notes="分页获取用户列表")
    @RequestMapping(value="/getUserList",method= RequestMethod.POST)
    public RespBean getUserList(@RequestBody UserPageNationVo userPageNationVo){
        return userServiceImpl.getUserList(userPageNationVo);
    }

    @ApiOperation(value = "获取单个用户信息" ,  notes="获取单个用户信息")
    @RequestMapping(value="/getUserInfo",method= RequestMethod.POST)
    public RespBean getUserInfo(@RequestParam("id") Integer id){
        return userServiceImpl.getUserInfo(id);
    }
}
