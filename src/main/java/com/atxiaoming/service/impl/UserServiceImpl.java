package com.atxiaoming.service.impl;

import com.atxiaoming.entity.User;
import com.atxiaoming.mapper.UserMapper;
import com.atxiaoming.service.IUserService;
import com.atxiaoming.vo.LoginVo;
import com.atxiaoming.vo.RespBean;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 小明
 * @since 2021-01-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    public RespBean doLogin(LoginVo loginVo) {
        loginVo.getAccount();
        QueryWrapper wrapper
        User user = userMapper.selectById();
        return RespBean.success();
    }
}
