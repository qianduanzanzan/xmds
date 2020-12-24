package com.atxiaoming.controller;


import com.atxiaoming.entity.TbRole;
import com.atxiaoming.mapper.TbRoleMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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

    @ApiOperation(value = "全部角色" ,  notes="获取全部角色信息")
    @RequestMapping(value="/getAll",method= RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public List<TbRole> roleGetAll(@RequestBody String roleName){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.like("role_name",roleName);
//        wrapper.eq("delete_at",null);
        List<TbRole> roles = tbRoleMapper.selectList(wrapper);
        return roles;
    }

//    @ApiOperation(value = "" ,  notes="获取全部角色信息")
//    @RequestMapping(value="/addRole",method= RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
//    public List<TbRole> addRole(@RequestBody TbRole role){
//        List<TbRole> roles = tbRoleMapper.selectList(null);
//        return roles;
//    }

    @ApiOperation(value = "新增角色" ,  notes="新增角色")
    @RequestMapping(value="/add",method= RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public String add(@RequestBody String roleName){
        TbRole role = new TbRole();
//        role.setRoleId(2);
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        role.setRoleName(roleName);
        int insert = tbRoleMapper.insert(role);
        System.out.println(role);
        return "添加成功";
    }

    @ApiOperation(value = "删除角色" ,  notes="删除角色")
    @RequestMapping(value="/del",method= RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public String del(@RequestBody int id){
        TbRole role = new TbRole();
        role.setId(id);
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        role.setDeleteAt(dateFormat.format(new Date()));
        int insert = tbRoleMapper.updateById(role);

        return "删除成功";
    }
}
