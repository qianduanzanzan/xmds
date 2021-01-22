package com.atxiaoming.service.impl;

import com.atxiaoming.entity.OssTemplate;
import com.atxiaoming.entity.User;
import com.atxiaoming.mapper.UserMapper;
import com.atxiaoming.service.IUserService;
import com.atxiaoming.utils.TokenUtil;
import com.atxiaoming.vo.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;
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

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private OssTemplate ossTemplate;

    @Autowired
    private TokenUtil tokenUtil;

//    @Transactional
//    public RespBean addUser(String userName, String account, String password, MultipartFile avatar) {
//        try{
//            QueryWrapper nameWrapper = new QueryWrapper();
//            nameWrapper.eq("user_name",userName);
//            if(!StringUtils.isEmpty(userMapper.selectOne(nameWrapper))){
//                return RespBean.error(RespBeanEnum.NAME_REPEAT);
//            }
//            QueryWrapper accWrapper = new QueryWrapper();
//            accWrapper.eq("account",account);
//            if(!StringUtils.isEmpty(userMapper.selectOne(accWrapper))){
//                return RespBean.error(RespBeanEnum.ACCOUNT_REPEAT);
//            }
//            String oldName = avatar.getOriginalFilename();
//            InputStream inputStream = avatar.getInputStream();
//            String newFileName = UUID.randomUUID().toString().replace("-", "").substring(4, 16)+"_"+oldName;
//            String uploadFilePath = ossTemplate.upload(inputStream, newFileName);
//            User user = new User();
//            String md5_password = DigestUtils.md5DigestAsHex(password.getBytes());
//            user.setPassword(md5_password);
//            user.setUserName(userName);
//            user.setAccount(account);
//            user.setAvatar(uploadFilePath);
//            userMapper.insert(user);
//            return RespBean.success();
//        }catch (Exception e){
//            System.out.println(e);
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//            return RespBean.error(RespBeanEnum.ERROR,e);
//        }
//    }

    @Transactional
    public RespBean addUser(String userName, String account, String password, String avatar) {
        try{
            QueryWrapper nameWrapper = new QueryWrapper();
            nameWrapper.eq("user_name",userName);
            if(!StringUtils.isEmpty(userMapper.selectOne(nameWrapper))){
                return RespBean.error(RespBeanEnum.NAME_REPEAT);
            }
            QueryWrapper accWrapper = new QueryWrapper();
            accWrapper.eq("account",account);
            if(!StringUtils.isEmpty(userMapper.selectOne(accWrapper))){
                return RespBean.error(RespBeanEnum.ACCOUNT_REPEAT);
            }
//            String oldName = avatar.getOriginalFilename();
//            InputStream inputStream = avatar.getInputStream();
//            String newFileName = UUID.randomUUID().toString().replace("-", "").substring(4, 16)+"_"+oldName;
//            String uploadFilePath = ossTemplate.upload(inputStream, newFileName);
            User user = new User();
            String md5_password = DigestUtils.md5DigestAsHex(password.getBytes());
            user.setPassword(md5_password);
            user.setUserName(userName);
            user.setAccount(account);
            user.setAvatar(avatar);
            userMapper.insert(user);
            return RespBean.success();
        }catch (Exception e){
            System.out.println(e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

    @Transactional
    public RespBean editUser(UpdateUserVo updateUserVo) {
        try{
            QueryWrapper nameWrapper = new QueryWrapper();
            nameWrapper.eq("user_name",updateUserVo.getUserName());
            if(!StringUtils.isEmpty(userMapper.selectOne(nameWrapper))){
                return RespBean.error(RespBeanEnum.NAME_REPEAT);
            }
            User user = new User();
            if(!StringUtils.isEmpty(updateUserVo.getAvatar())){
                user.setAvatar(updateUserVo.getAvatar());
            }
            user.setId(updateUserVo.getId());
            user.setUserName(updateUserVo.getUserName());
            userMapper.updateById(user);
            return RespBean.success();
        }catch (Exception e){
            System.out.println(e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

    @Transactional
    public RespBean changeUserFlag(Integer id) {
        try{
            User user = userMapper.selectById(id);
            User newUser = new User();
            newUser.setId(id);
            if(user.getStopFlag().equals(0)){
                newUser.setStopFlag(1);
            }else{
                newUser.setStopFlag(0);
            }
            userMapper.updateById(newUser);
            return RespBean.success();
        }catch (Exception e){
            System.out.println(e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

    @Transactional
    public RespBean changePassword(ChangePasswordVo changePasswordVo) {
        try{
            User user = userMapper.selectById(changePasswordVo.getId());
            String old_md5Password = DigestUtils.md5DigestAsHex(changePasswordVo.getOldPassword().getBytes());
            if(!user.getPassword().equals(old_md5Password)){
                return RespBean.error(RespBeanEnum.PWD_ERROR);
            }
            User newUser = new User();
            newUser.setId(changePasswordVo.getId());
            String new_md5Password = DigestUtils.md5DigestAsHex(changePasswordVo.getNewPassword().getBytes());
            newUser.setPassword(new_md5Password);
            userMapper.updateById(newUser);
            return RespBean.success();
        }catch (Exception e){
            System.out.println(e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

    public RespBean getUserList(UserPageNationVo userPageNationVo) {
        try{
            QueryWrapper wrapper = new QueryWrapper();
            if(!StringUtils.isEmpty(userPageNationVo.getStopFlag())){
                wrapper.eq("stop_flag",userPageNationVo.getStopFlag());
            }
            System.out.println(userPageNationVo.getUserName());
            if(!StringUtils.isEmpty(userPageNationVo.getUserName())){
                wrapper.like("user_name",userPageNationVo.getUserName());
            }
            Page<User> userIPage = new Page<>(userPageNationVo.getCurrent(), userPageNationVo.getSize());
            IPage<User> userPage = userMapper.selectPage(userIPage, wrapper);
            for (User item : userPage.getRecords()){
                item.deletePassword();
            }
            return RespBean.success(userPage);
        }catch (Exception e){
            System.out.println(e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

    public RespBean getUserInfo(Integer id) {
        try{
            User user = userMapper.selectById(id);
            user.deletePassword();
            return RespBean.success(user);
        }catch (Exception e){
            System.out.println(e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

    public RespBean login(LoginVo loginVo) {
        try{
            String account = loginVo.getAccount();
            String password = loginVo.getPassword();
            if(StringUtils.isEmpty(account) || StringUtils.isEmpty(password)){
                return RespBean.error(RespBeanEnum.ACCPWD_NOT_EMPTY);
            }
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("account",account);
            User user = userMapper.selectOne(wrapper);
            if(StringUtils.isEmpty(user)){
                return RespBean.error(RespBeanEnum.ACC_ERROR);
            }
            if(!user.getPassword().equals(password)){
                return RespBean.error(RespBeanEnum.PWD_ERROR);
            }
            String token = tokenUtil.createToken(user);
            redisTemplate.opsForValue().set(token, user.getId(),60 * 60 * 6, TimeUnit.SECONDS);
            UserInfoVo userInfoVo = userInfo(user);
            userInfoVo.setToken(token);
            return RespBean.success(userInfoVo);
        }catch (Exception e){
            System.out.println(e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

    public RespBean logout(String token) {
        try{
            redisTemplate.delete(token);
            return RespBean.success();
        }catch (Exception e){
            System.out.println(e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
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

    public RespBean getUserInfoByToken(String token) {
        try{
            Integer id = tokenUtil.getIdFromToken(token);
            User user = userMapper.selectById(id);
            UserInfoVo userInfoVo = userInfo(user);
            userInfoVo.setToken(token);
            return RespBean.success(userInfoVo);
        }catch (Exception e){
            System.out.println(e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }
}
