package com.atxiaoming.controller;


import com.atxiaoming.entity.TbUserMenu;
import com.atxiaoming.mapper.TbMenuMapper;
import com.atxiaoming.mapper.TbUserMapper;
import com.atxiaoming.service.impl.TbMenuServiceImpl;
import com.atxiaoming.service.impl.TbUserMenuServiceImpl;
import com.atxiaoming.vo.AddUserMenuVo;
import com.atxiaoming.vo.MenuRequireVo;
import com.atxiaoming.vo.RespBean;
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
 * @since 2021-01-11
 */
@RestController
@RequestMapping("/tbUserMenu")
public class TbUserMenuController {

    @Autowired
    private TbUserMenuServiceImpl userMenuServiceI;

    @ApiOperation(value = "通过用户id获取菜单" ,  notes="获取菜单")
    @RequestMapping(value="/getMenu",method= RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public RespBean getList(@RequestBody Long userId){
        return userMenuServiceI.getMenu(userId);
    }

    @ApiOperation(value = "菜单权限" ,  notes="关联菜单的权限给个人")
    @RequestMapping(value="/add",method= RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public RespBean add(@RequestBody AddUserMenuVo userMenuVo){
        return userMenuServiceI.add(userMenuVo);
    }

    @ApiOperation(value = "取消菜单的权限" ,  notes="取消菜单的权限")
    @RequestMapping(value="/del",method= RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public RespBean del(@RequestBody AddUserMenuVo userMenuVo){
        return userMenuServiceI.del(userMenuVo);
    }

//    @ApiOperation(value = "修改" ,  notes="获取菜单")
//    @RequestMapping(value="/edit",method= RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
//    public RespBean edit(@RequestBody TbUserMenu userMenu){
//        return userMenuServiceI.edit(userMenu);
//    }

}
