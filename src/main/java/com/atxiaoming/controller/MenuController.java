package com.atxiaoming.controller;


import com.atxiaoming.service.impl.MenuServiceImpl;
import com.atxiaoming.service.impl.UserServiceImpl;
import com.atxiaoming.vo.EditMenuVo;
import com.atxiaoming.vo.MenuPagenitionVo;
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
@Api(description = "菜单相关接口")
@RestController
@RequestMapping("/menu")
@Slf4j
public class MenuController {
    @Autowired
    private MenuServiceImpl menuServiceImpl;

    @ApiOperation(value = "添加菜单" ,  notes="添加菜单")
    @RequestMapping(value="/add",method= RequestMethod.POST)
    public RespBean addMenu(@RequestParam("menuName") String menuName,
                             @RequestParam("routeName") String routeName,
                             @RequestParam("filePath") String filePath){
        return menuServiceImpl.addMenu(menuName,routeName,filePath);
    }

    @ApiOperation(value = "修改菜单" ,  notes="修改菜单")
    @RequestMapping(value="/edit",method= RequestMethod.POST)
    public RespBean editMenu(@RequestBody EditMenuVo editMenuVo){
        return menuServiceImpl.editMenu(editMenuVo);
    }

    @ApiOperation(value = "修改菜单状态" ,  notes="修改菜单状态")
    @RequestMapping(value="/changeMenuStatus",method= RequestMethod.POST)
    public RespBean changeMenuStatus(@RequestParam Integer id){
        return menuServiceImpl.changeMenuStatus(id);
    }

    @ApiOperation(value = "菜单分页" ,  notes="获取菜单分页")
    @RequestMapping(value="/getMenuPage",method= RequestMethod.POST)
    public RespBean getMenuPage(@RequestBody MenuPagenitionVo menuPagenitionVo){
        return menuServiceImpl.getMenuPage(menuPagenitionVo);
    }

    @ApiOperation(value = "获取用户菜单" ,  notes="根据用户获取菜单列表")
    @RequestMapping(value="/getMenuListByUserId",method= RequestMethod.POST)
    public RespBean getMenuListByUserId(@RequestParam("id") Integer id){
        return menuServiceImpl.getMenuListByUserId(id);
    }

    @ApiOperation(value = "获取全部菜单" ,  notes="获取全部菜单")
    @RequestMapping(value="/getMenus",method= RequestMethod.POST)
    public RespBean getMenus(){
        return menuServiceImpl.getMenus();
    }

    @ApiOperation(value = "获取菜单信息" ,  notes="获取菜单信息")
    @RequestMapping(value="/getDetail",method= RequestMethod.POST)
    public RespBean getDetail(@RequestParam("id") Integer id){
        return menuServiceImpl.getDetail(id);
    }
}
