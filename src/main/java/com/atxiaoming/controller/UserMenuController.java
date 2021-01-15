package com.atxiaoming.controller;


import com.atxiaoming.service.impl.UserMenuServiceImpl;
import com.atxiaoming.service.impl.UserServiceImpl;
import com.atxiaoming.vo.RespBean;
import com.atxiaoming.vo.UpdateUserVo;
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
 * @since 2021-01-14
 */
@Api(description = "用户授权菜单相关接口")
@RestController
@RequestMapping("/userMenu")
@Slf4j
public class UserMenuController {

    @Autowired
    private UserMenuServiceImpl userMenuService;

    @ApiOperation(value = "给用户添加菜单权限" ,  notes="给用户添加菜单权限")
    @RequestMapping(value="/add",method= RequestMethod.POST)
    public RespBean add(@RequestParam("userId") Integer userId,
                             @RequestParam("menuId") Integer menuId){
        return userMenuService.addUserMenu(userId,menuId);
    }

    @ApiOperation(value = "给用户删除菜单权限" ,  notes="给用户删除菜单权限")
    @RequestMapping(value="/del",method= RequestMethod.POST)
    public RespBean del(@RequestParam("userId") Integer userId,
                             @RequestParam("menuId") Integer menuId){
        return userMenuService.delUserMenu(userId,menuId);
    }
}
