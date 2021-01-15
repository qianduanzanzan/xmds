package com.atxiaoming.service.impl;

import com.atxiaoming.entity.Menu;
import com.atxiaoming.entity.UserMenu;
import com.atxiaoming.mapper.MenuMapper;
import com.atxiaoming.mapper.UserMenuMapper;
import com.atxiaoming.service.IUserMenuService;
import com.atxiaoming.vo.RespBean;
import com.atxiaoming.vo.RespBeanEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 小明
 * @since 2021-01-14
 */
@Service
public class UserMenuServiceImpl extends ServiceImpl<UserMenuMapper, UserMenu> implements IUserMenuService {

    @Autowired
    private UserMenuMapper userMenuMapper;

    public RespBean addUserMenu(Integer userId, Integer menuId) {
        try{
            UserMenu userMenu = new UserMenu();
            userMenu.setMenuId(menuId);
            userMenu.setUserId(userId);
            userMenuMapper.insert(userMenu);
            return RespBean.success();
        }catch (Exception e){
            System.out.println(e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

    public RespBean delUserMenu(Integer userId, Integer menuId) {
        try{
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("user_id",userId);
            wrapper.eq("menu_id",menuId);
            userMenuMapper.delete(wrapper);
            return RespBean.success();
        }catch (Exception e){
            System.out.println(e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }
}
