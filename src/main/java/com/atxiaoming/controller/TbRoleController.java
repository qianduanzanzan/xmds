package com.atxiaoming.controller;


import com.atxiaoming.entity.ResultEntity;
import com.atxiaoming.entity.TbRole;
import com.atxiaoming.mapper.TbRoleMapper;
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

    @ApiOperation(value = "全部角色" ,  notes="获取全部角色信息")
    @RequestMapping(value="/getAll",method= RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public String roleGetAll(@RequestBody(required = false) String roleName){
        QueryWrapper wrapper = new QueryWrapper();
        try {
            if(StringUtils.isNotBlank(roleName)){
                wrapper.like("role_name",roleName);
            }
            List<TbRole> roles = tbRoleMapper.selectList(wrapper);
//            return ResultEntity<roles>.result("success","",roles);
            return "查询成功";
        }catch (Exception e){
//            return ResultEntity.result("failed",e,null);
            return "查询失败";
        }
    }

    @ApiOperation(value = "修改角色信息" ,  notes="修改角色信息")
    @RequestMapping(value="/update",method= RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public String addRole(@RequestBody TbRole role){
        TbRole newRole = new TbRole();
        newRole.setId(role.getId());
        newRole.setRoleName(role.getRoleName());
        tbRoleMapper.updateById(newRole);
        return "修改成功";
//        int id = role.getId();
//        if(NumberUtil.)
//        return roles;
    }

    @ApiOperation(value = "新增角色" ,  notes="新增角色")
    @RequestMapping(value="/add",method= RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public String add(@RequestBody String roleName){
        TbRole role = new TbRole();
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
        tbRoleMapper.updateById(role);
        tbRoleMapper.deleteById(id);
        return "删除成功";
    }
}
