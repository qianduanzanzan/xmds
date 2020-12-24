package com.atxiaoming.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(description = "用户接口")
@RestController
@RequestMapping("/tbRole")
public class TbRoleController {
    @ApiOperation(value = "新增用户" ,  notes="新增注册")
    @RequestMapping(value="/createUser",method= RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public String createUser(@RequestBody String user){
        return String.format("用户%s创建成功", user);
    }
}
