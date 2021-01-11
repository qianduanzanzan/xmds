package com.atxiaoming.service.impl;

import com.atxiaoming.entity.TbMenu;
import com.atxiaoming.entity.TbUserMenu;
import com.atxiaoming.mapper.TbMenuMapper;
import com.atxiaoming.mapper.TbUserMapper;
import com.atxiaoming.mapper.TbUserMenuMapper;
import com.atxiaoming.service.ITbUserMenuService;
import com.atxiaoming.vo.AddUserMenuVo;
import com.atxiaoming.vo.RespBean;
import com.atxiaoming.vo.RespBeanEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 小明
 * @since 2021-01-11
 */
@Service
public class TbUserMenuServiceImpl extends ServiceImpl<TbUserMenuMapper, TbUserMenu> implements ITbUserMenuService {

    @Autowired
    private TbUserMenuMapper userMenuMapper;

    @Autowired
    private TbUserMapper userMapper;

    @Autowired
    private TbMenuMapper menuMapper;

    public RespBean getMenu(Long userId) {
        try{
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("user_id",userId);
            List<TbUserMenu> userMenuList = userMenuMapper.selectList(wrapper);
            List res = new ArrayList();
            for (TbUserMenu userMenu : userMenuList){
                TbMenu menu = menuMapper.selectById(userMenu.getMenuId());
                res.add(menu);
            }
            return RespBean.success(res);
        }catch (Exception e){
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

    public RespBean add(AddUserMenuVo userMenuVo) {
        try{
            TbUserMenu userMenu = new TbUserMenu();
            userMenu.setMenuId(userMenuVo.getMenuId());
            userMenu.setUserId(userMenuVo.getUserId());
            System.out.println(userMenu);
            userMenuMapper.insert(userMenu);
            return RespBean.success();
        }catch (Exception e){
            System.out.println(e);
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

    public RespBean del(AddUserMenuVo userMenuVo) {
        try{
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("user_id",userMenuVo.getUserId());
            wrapper.eq("menu_id",userMenuVo.getMenuId());
            userMenuMapper.delete(wrapper);
            return RespBean.success();
        }catch (Exception e){
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

    public RespBean edit(TbUserMenu userMenu) {
        try{
            userMenuMapper.updateById(userMenu);
            return RespBean.success();
        }catch (Exception e){
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }
}
