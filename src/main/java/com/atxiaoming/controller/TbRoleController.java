package com.atxiaoming.controller;


import com.atxiaoming.entity.ResultEntity;
import com.atxiaoming.entity.TbRole;
import com.atxiaoming.mapper.TbRoleMapper;
import com.atxiaoming.service.impl.TbMenuServiceImpl;
import com.atxiaoming.service.impl.TbRoleServiceImpl;
import com.atxiaoming.vo.RespBean;
import com.atxiaoming.vo.RoleRequireVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 小明
 * @since 2020-12-24
 */
@Api(description = "角色管理接口")
@RestController
@RequestMapping("/role")
public class TbRoleController {

    @Autowired
    private TbRoleMapper tbRoleMapper;
    @Autowired
    private TbRoleServiceImpl roleServiceI;

    @ApiOperation(value = "全部角色" ,  notes="获取全部角色信息")
    @RequestMapping(value="/getAll",method= RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public RespBean roleGetAll(@RequestBody(required = false) RoleRequireVo role){
        return roleServiceI.getList(role);
    }

    @ApiOperation(value = "修改角色信息" ,  notes="修改角色信息")
    @RequestMapping(value="/update",method= RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public RespBean editRole(@RequestBody TbRole role){
        return roleServiceI.update(role);
    }

    @ApiOperation(value = "新增角色" ,  notes="新增角色")
    @RequestMapping(value="/add",method= RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public RespBean add(@RequestBody RoleRequireVo roleVo){
        return roleServiceI.add(roleVo);
    }

    @ApiOperation(value = "删除角色" ,  notes="删除角色")
    @RequestMapping(value="/del",method= RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public RespBean del(@RequestBody int id){
        return roleServiceI.del(id);
    }
}
