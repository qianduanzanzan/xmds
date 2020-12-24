package com.atxiaoming.controller;


import com.atxiaoming.entity.TbRole;
import com.atxiaoming.mapper.TbRoleMapper;
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
    public List<TbRole> roleGetAll(){
        List<TbRole> roles = tbRoleMapper.selectList(null);
        System.out.println(roles);
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
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        role.setCreateAt();
        role.setRoleName(roleName);
        int insert = tbRoleMapper.insert(role);
        return "添加成功";
    }
}
