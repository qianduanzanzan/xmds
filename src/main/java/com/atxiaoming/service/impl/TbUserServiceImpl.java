package com.atxiaoming.service.impl;

import com.atxiaoming.entity.TbUser;
import com.atxiaoming.mapper.TbUserMapper;
import com.atxiaoming.service.ITbUserService;
import com.atxiaoming.vo.LoginVo;
import com.atxiaoming.vo.RespBean;
import com.atxiaoming.vo.RespBeanEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.tomcat.util.http.parser.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public RespBean doLogin(LoginVo loginVo) {
        String account = loginVo.getAccount();
        String password = loginVo.getPassword();
        if(!StringUtils.isNotBlank(account) || !StringUtils.isNotBlank(password)){
            return RespBean.error(RespBeanEnum.ACCPWD_NOT_EMPTY);
        }
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("account",account);
        TbUser user = userMapper.selectOne(wrapper);
        if(user == null){
            return RespBean.error(RespBeanEnum.ACC_ERROR);
        }
        if(user.getPassword() != password){
            return RespBean.error(RespBeanEnum.PWD_ERROR);
        }
//        Cookie cookie = new
        return RespBean.success();
    }
}
