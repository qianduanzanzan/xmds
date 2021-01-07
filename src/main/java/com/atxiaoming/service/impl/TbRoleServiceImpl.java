package com.atxiaoming.service.impl;

import com.atxiaoming.entity.TbMenu;
import com.atxiaoming.entity.TbRole;
import com.atxiaoming.mapper.TbRoleMapper;
import com.atxiaoming.service.ITbRoleService;
import com.atxiaoming.vo.MenuRequireVo;
import com.atxiaoming.vo.RespBean;
import com.atxiaoming.vo.RespBeanEnum;
import com.atxiaoming.vo.RoleRequireVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 小明
 * @since 2020-12-24
 */
@Service
public class TbRoleServiceImpl extends ServiceImpl<TbRoleMapper, TbRole> implements ITbRoleService {

    @Autowired
    private TbRoleMapper tbRoleMapper;

    public RespBean getList(RoleRequireVo role) {
        try{
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.like("role_name",role.getRoleName());
            List<TbRole> roles = tbRoleMapper.selectList(wrapper);
            return  RespBean.success(roles);
        }catch (Exception e){
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

    public RespBean update(TbRole role) {
        try{
            TbRole newRole = new TbRole();
            newRole.setId(role.getId());
            newRole.setRoleName(role.getRoleName());
            tbRoleMapper.updateById(newRole);
            return  RespBean.success();
        }catch (Exception e){
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

    public RespBean add(RoleRequireVo roleVo) {
        try{
            TbRole role = new TbRole();
            role.setRoleName(roleVo.getRoleName());
            int insert = tbRoleMapper.insert(role);
            return  RespBean.success();
        }catch (Exception e){
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

    public RespBean del(int id) {
        try{
            TbRole role = new TbRole();
            role.setId(id);
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            role.setDeleteAt(dateFormat.format(new Date()));
            tbRoleMapper.updateById(role);
            tbRoleMapper.deleteById(id);
            return  RespBean.success();
        }catch (Exception e){
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }
}
