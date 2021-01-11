package com.atxiaoming.service.impl;

import com.atxiaoming.entity.TbMenu;
import com.atxiaoming.entity.TbUser;
import com.atxiaoming.mapper.TbMenuMapper;
import com.atxiaoming.service.ITbMenuService;
import com.atxiaoming.vo.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 小明
 * @since 2021-01-04
 */
@Service
public class TbMenuServiceImpl extends ServiceImpl<TbMenuMapper, TbMenu> implements ITbMenuService {
    @Autowired
    private TbMenuMapper tbMenuMapper;

    public RespBean getList(MenuRequireVo menuVo) {
        try{
            QueryWrapper wrapper = new QueryWrapper();
            if(menuVo.getMenuName()==null){ }else{
                wrapper.like("menu_name",menuVo.getMenuName());
            }
//            Integer page = menuVo.getPage();
//            Integer size = menuVo.getSize();
//            Page<TbMenu> menuPage = new Page<>(page, size);
//            Page<TbMenu> userIPage = tbMenuMapper.selectPage(menuPage, wrapper);
            List<TbMenu> Menus = tbMenuMapper.selectList(wrapper);
            return RespBean.success(Menus);
        }catch (Exception e){
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

    public RespBean getMenuPage(MenuPageVo menuPageVo) {
        try{
            QueryWrapper wrapper = new QueryWrapper();
            if(menuPageVo.getMenuName()==null){
            }else{
                wrapper.like("menu_name",menuPageVo.getMenuName());
            }
            Integer page = menuPageVo.getCurrent();
            Integer size = menuPageVo.getSize();
            Page<TbMenu> menuPage = new Page<>(page, size);
            Page<TbMenu> menuIPage = tbMenuMapper.selectPage(menuPage, wrapper);
            return RespBean.success(menuIPage);
        }catch (Exception e){
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

    public RespBean add(TbMenu menu) {
        try{
            tbMenuMapper.insert(menu);
            return RespBean.success();
        }catch (Exception e){
            System.out.println(e);
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

    public RespBean edit(TbMenu menu) {
        try{
            tbMenuMapper.updateById(menu);
            return RespBean.success();
        }catch (Exception e){
            System.out.println(e);
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

    public RespBean getOne(int id) {
        try{
            TbMenu menu = tbMenuMapper.selectById(id);
            return RespBean.success(menu);
        }catch (Exception e){
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

}
