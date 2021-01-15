package com.atxiaoming.service.impl;

import com.atxiaoming.entity.Menu;
import com.atxiaoming.entity.User;
import com.atxiaoming.entity.UserMenu;
import com.atxiaoming.mapper.MenuMapper;
import com.atxiaoming.mapper.UserMenuMapper;
import com.atxiaoming.service.IMenuService;
import com.atxiaoming.vo.EditMenuVo;
import com.atxiaoming.vo.MenuPagenitionVo;
import com.atxiaoming.vo.RespBean;
import com.atxiaoming.vo.RespBeanEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 小明
 * @since 2021-01-14
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private UserMenuMapper userMenuMapper;

    @Transactional
    public RespBean addMenu(String meunName,String routeName,String filePath) {
        try{
            Menu menu = new Menu();
            menu.setMenuName(meunName);
            menu.setRouteName(routeName);
            menu.setFilePath(filePath);
            menuMapper.insert(menu);
            return RespBean.success();
        }catch (Exception e){
            System.out.println(e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

    @Transactional
    public RespBean editMenu(EditMenuVo editMenuVo) {
        try{
            Menu menu = new Menu();
            menu.setId(editMenuVo.getId());
            if(!editMenuVo.getMenuName().isEmpty()){
                menu.setMenuName(editMenuVo.getMenuName());
            }
            if(!editMenuVo.getRouteName().isEmpty()){
                menu.setRouteName(editMenuVo.getRouteName());
            }
            if(!editMenuVo.getFileName().isEmpty()){
                menu.setFilePath(editMenuVo.getFileName());
            }
            menuMapper.updateById(menu);
            return RespBean.success();
        }catch (Exception e){
            System.out.println(e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

    @Transactional
    public RespBean changeMenuStatus(Integer id) {
        try{
            Menu menu = menuMapper.selectById(id);
            Menu newMenu = new Menu();
            newMenu.setId(id);
            if(menu.getStopFlag().equals(0)){
                newMenu.setStopFlag(1);
            }else{
                newMenu.setStopFlag(0);
            }
            menuMapper.updateById(menu);
            return RespBean.success();
        }catch (Exception e){
            System.out.println(e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

    public RespBean getMenuPage(MenuPagenitionVo menuPagenitionVo) {
        try{
            QueryWrapper wrapper = new QueryWrapper();
            if(!StringUtils.isEmpty(menuPagenitionVo.getStopFlag())){
                wrapper.eq("stop_flag",menuPagenitionVo.getStopFlag());
            }
            if(!menuPagenitionVo.getMenuName().isEmpty()){
                wrapper.like("menu_name",menuPagenitionVo.getMenuName());
            }
            Page<Menu> menuIPage = new Page<>(menuPagenitionVo.getCurrent(), menuPagenitionVo.getSize());
            IPage<Menu> menuPage = menuMapper.selectPage(menuIPage, wrapper);
            return RespBean.success(menuPage);
        }catch (Exception e){
            System.out.println(e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

    public RespBean getMenuListByUserId(Integer id) {
        try{
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("user_id",id);
            List<UserMenu> userMenuList = userMenuMapper.selectList(wrapper);
            List<Menu> menuList = new ArrayList<>();
            QueryWrapper stopWrapper = new QueryWrapper();
            stopWrapper.eq("stop_flag",0);
            for (UserMenu userMenu : userMenuList){
                stopWrapper.eq("id",userMenu.getMenuId());
                Menu menu = menuMapper.selectOne(stopWrapper);
                menuList.add(menu);
            }
            return RespBean.success(menuList);
        }catch (Exception e){
            System.out.println(e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

}
