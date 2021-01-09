package com.atxiaoming.service.impl;

import com.atxiaoming.entity.OssTemplate;
import com.atxiaoming.entity.TbUser;
import com.atxiaoming.mapper.TbUserMapper;
import com.atxiaoming.service.ITbUserService;
import com.atxiaoming.utils.TokenUtil;
import com.atxiaoming.vo.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;
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

    @Autowired
    private OssTemplate ossTemplate;

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
        UserInfoVo userInfo = userInfo(user);
        String token = tokenUtil.createToken(user);
        userInfo.setToken(token);
        redisTemplate.opsForValue().set(token, userInfo,60 * 60 * 6, TimeUnit.SECONDS);
        return RespBean.success(userInfo);
    }

    private UserInfoVo userInfo(TbUser user) {
        UserInfoVo userInfo = new UserInfoVo();
        userInfo.setId(user.getId());
        userInfo.setUserName(user.getUserName());
        userInfo.setAvatar(user.getAvatar());
        userInfo.setRoleId(user.getRoleId());
        userInfo.setCreateAt(user.getCreateAt());
        userInfo.setUpdateAt(user.getUpdateAt());
        userInfo.setAccount(user.getAccount());
        return userInfo;
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
        }else{
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("user_name",user.getUserName());
            TbUser user1 = userMapper.selectOne(wrapper);
            if(StringUtils.isEmpty(user1)){}else{
                return RespBean.error(RespBeanEnum.NAME_REPEAT);
            }
        }
        if(StringUtils.isEmpty(user.getAccount())){
            return RespBean.error(RespBeanEnum.ACC_NOT_EMPTY);
        }else{
            QueryWrapper wrapper1 = new QueryWrapper();
            wrapper1.eq("account",user.getAccount());
            TbUser user1 = userMapper.selectOne(wrapper1);
            if(StringUtils.isEmpty(user1)){}else{
                return RespBean.error(RespBeanEnum.ACCOUNT_REPEAT);
            }
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

    public RespBean checkLogin(TokenVo token){
        if(StringUtils.isEmpty(token.getToken())){
            return RespBean.error(RespBeanEnum.NOT_LOGIN);
        }
        try {
            Object loginUser = redisTemplate.opsForValue().get(token.getToken());
            Field[] field = loginUser.getClass().getDeclaredFields();
            System.out.println(field);
//            Map<String, Object> returnMap = BeanUtils.describe(obj);
            if(StringUtils.isEmpty(loginUser)){
                return RespBean.error(RespBeanEnum.NOT_LOGIN);
            }else{
//                Class User = loginUser.getClass();
//                Long id = User.getDeclaredAnnotation();
//                TbUser user = userMapper.selectById(id);
                return RespBean.success(loginUser);
            }
        }catch (Exception e){
            return RespBean.error(RespBeanEnum.ERROR);
        }
    }

    public RespBean getUser(UserIdVo userId) {
        System.out.println(userId.getId());
        TbUser user1 = userMapper.selectById(userId.getId());
        System.out.println(user1);
        try{
            TbUser user = userMapper.selectById(userId.getId());
            UserInfoVo userInfo = userInfo(user);
            return RespBean.success(userInfo);
        }catch (Exception e){
            System.out.println(e);
            return RespBean.error(RespBeanEnum.ERROR);
        }
    }

    public RespBean changeAvatar(String token, ChangeAvatarVo avatarVo) {
        try{
            TbUser user = new TbUser();
            user.setAvatar(avatarVo.getAvatar());
            user.setId(avatarVo.getId());
            userMapper.updateById(user);
            TbUser Tbuser = userMapper.selectById(avatarVo.getId());
            UserInfoVo userInfo = userInfo(Tbuser);
            userInfo.setToken(token);
            redisTemplate.opsForValue().set(token, userInfo,60 * 60 * 6, TimeUnit.SECONDS);
            return RespBean.success();
        }catch (Exception e){
            return RespBean.error(RespBeanEnum.ERROR);
        }
    }

    public RespBean updateUserInfo(String token, UpdateUserVo user) {
        try{
            TbUser newUser = new TbUser();
            newUser.setId(user.getId());
            newUser.setUserName(user.getUserName());
            newUser.setAccount(user.getAccount());
            newUser.setRoleId(user.getRoleId());
            userMapper.updateById(newUser);
            Long id = tokenUtil.getIdFromToken(token);
            if(id == user.getId()){
                TbUser Tbuser = userMapper.selectById(user.getId());
                UserInfoVo userInfo = userInfo(Tbuser);
                userInfo.setToken(token);
                redisTemplate.opsForValue().set(token, userInfo,60 * 60 * 6, TimeUnit.SECONDS);
            }
            return RespBean.success();
        }catch (Exception e){
            return RespBean.error(RespBeanEnum.ERROR);
        }
    }

    public static String getRespBean(MultipartFile filename, OssTemplate ossTemplate) throws IOException {
        String oldName = filename.getOriginalFilename();
        InputStream inputStream = filename.getInputStream();
        String newFileName = UUID.randomUUID().toString().replace("-", "").substring(4, 16)+"_"+oldName;
        String uploadFilePath = ossTemplate.upload(inputStream, newFileName);
        return  uploadFilePath;
    }

    public RespBean getList(UserPageNationVo userPageNationVo) {
        try{
            Integer page = userPageNationVo.getCurrent();
            Integer size = userPageNationVo.getSize();
            UserInfoVo userInfoVo = new UserInfoVo();
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.like("user_name",StringUtils.isEmpty(userPageNationVo.getUserName()) ? "":userPageNationVo.getUserName());
            Page<TbUser> userIPage = new Page<>(page, size);
            Page<TbUser> userPage = userMapper.selectPage(userIPage, wrapper);
            for (TbUser item : userPage.getRecords()){
                item.deletePassword();
            }
//            System.out.println(userPage.getRecords());
            return RespBean.success(userPage);
        }catch (Exception e){
            System.out.println(e);
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }
}
