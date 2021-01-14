package com.atxiaoming.service.impl;

import com.atxiaoming.entity.User;
import com.atxiaoming.mapper.UserMapper;
import com.atxiaoming.service.IUserService;
import com.atxiaoming.vo.LoginVo;
import com.atxiaoming.vo.RespBean;
import com.atxiaoming.vo.RespBeanEnum;
import com.atxiaoming.vo.UserInfoVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 小明
 * @since 2021-01-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

//    @Autowired
//    private OssTemplate ossTemplate;

    public RespBean doLogin(LoginVo loginVo) {
        loginVo.getAccount();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("account",loginVo.getAccount());
        User user = userMapper.selectOne(wrapper);
        if(StringUtils.isEmpty(user)){
            return RespBean.error(RespBeanEnum.ACC_ERROR);
        }else if(user.getPassword() != loginVo.getPassword()){
            return RespBean.error(RespBeanEnum.PWD_ERROR);
        }
        UserInfoVo userInfo = userInfo(user);
        String token = tokenUtil.createToken(user);
        userInfo.setToken(token);
        redisTemplate.opsForValue().set(token, userInfo,60 * 60 * 6, TimeUnit.SECONDS);
        return RespBean.success(userInfo);
        return RespBean.success();
    }

    private UserInfoVo userInfo(User user) {
        UserInfoVo userInfo = new UserInfoVo();
        userInfo.setId(user.getId());
        userInfo.setUserName(user.getUserName());
        userInfo.setAvatar(user.getAvatar());
        userInfo.setCreateAt(user.getCreateAt());
        userInfo.setUpdateAt(user.getUpdateAt());
        userInfo.setAccount(user.getAccount());
        return userInfo;
    }
}
