package com.atxiaoming.service.impl;

import com.atxiaoming.entity.TbUser;
import com.atxiaoming.mapper.TbUserMapper;
import com.atxiaoming.service.ITbUserService;
import com.atxiaoming.utils.TokenUtil;
import com.atxiaoming.vo.LoginVo;
import com.atxiaoming.vo.RespBean;
import com.atxiaoming.vo.RespBeanEnum;
import com.atxiaoming.vo.UserInfoVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 小明
 * @since 2020-12-24
 */
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements ITbUserService {
    @Autowired
    private TbUserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private TokenUtil tokenUtil;

    public RespBean doLogin(LoginVo loginVo) {
        String account = loginVo.getAccount();
        String password = loginVo.getPassword();
        if(StringUtils.isEmpty(account) || StringUtils.isEmpty(password)){
            return RespBean.error(RespBeanEnum.ACCPWD_NOT_EMPTY);
        }
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("account",account);
        TbUser user = userMapper.selectOne(wrapper);
        if(user == null){
            return RespBean.error(RespBeanEnum.ACC_ERROR);
        }
        if(!password.equals(user.getPassword())){
            return RespBean.error(RespBeanEnum.PWD_ERROR);
        }
        UserInfoVo userInfo = new UserInfoVo();
        userInfo.setId(user.getId());
        userInfo.setUserName(user.getUserName());
        userInfo.setAvatar(user.getAvatar());
        userInfo.setRoleId(user.getRoleId());
        userInfo.setCreateAt(user.getCreateAt());
        userInfo.setUpdateAt(user.getUpdateAt());
        userInfo.setAccount(user.getAccount());
        String token = tokenUtil.createToken(user);
        userInfo.setToken(token);
        redisTemplate.opsForValue().set(token, userInfo,60 * 60 * 6, TimeUnit.SECONDS);
        return RespBean.success(userInfo);
    }

    public RespBean logOut(String token){
        try{
            redisTemplate.delete(token);
            return RespBean.success();
        }catch (Exception e){
            return RespBean.error(RespBeanEnum.ERROR);
        }
    }

    public RespBean addUser(TbUser user){
        if(StringUtils.isEmpty(user.getUserName())){
            return RespBean.error(RespBeanEnum.NAME_NOT_EMPTY);
        }
        if(StringUtils.isEmpty(user.getAccount())){
            return RespBean.error(RespBeanEnum.ACC_NOT_EMPTY);
        }
        if(StringUtils.isEmpty(user.getPassword())){
            return RespBean.error(RespBeanEnum.PWD_NOT_EMPTY);
        }
        try{
            int insert = userMapper.insert(user);
            return RespBean.success();
        }catch (Exception e){
            System.out.println(e);
            return RespBean.error(RespBeanEnum.ERROR);
        }
    }

    public RespBean checkLogin(String token){
        try {
            Object loginUser = redisTemplate.opsForValue().get(token);
            if(StringUtils.isEmpty(loginUser)){
                return RespBean.error(RespBeanEnum.NOT_LOGIN);
            }else{
                return RespBean.success(loginUser);
            }
        }catch (Exception e){
            return RespBean.error(RespBeanEnum.ERROR);
        }
    }
}
