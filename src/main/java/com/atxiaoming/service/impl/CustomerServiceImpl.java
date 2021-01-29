package com.atxiaoming.service.impl;

import com.atxiaoming.entity.Customer;
import com.atxiaoming.entity.User;
import com.atxiaoming.mapper.CustomerMapper;
import com.atxiaoming.service.ICustomerService;
import com.atxiaoming.utils.TokenUtil;
import com.atxiaoming.vo.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 小明
 * @since 2021-01-23
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private TokenUtil tokenUtil;

    public RespBean login(String phone,String password) {
        try{
            if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(password)){
                return RespBean.error(RespBeanEnum.ACCPWD_NOT_EMPTY);
            }
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("phone",phone);
            Customer customer = customerMapper.selectOne(wrapper);
            if(StringUtils.isEmpty(customer)){
                return RespBean.error(RespBeanEnum.ACC_ERROR);
            }
            if(!customer.getPassword().equals(password)){
                return RespBean.error(RespBeanEnum.PWD_ERROR);
            }
            String cusToken = tokenUtil.createCusToken(customer);
            redisTemplate.opsForValue().set(cusToken, customer.getPhone(),60 * 60 * 6, TimeUnit.SECONDS);
            CustomerInfoVo customerInfoVo = CustomerInfoVo.getCustomerInfo(customer);
            customerInfoVo.setToken(cusToken);
            return RespBean.success(customerInfoVo);
//            return RespBean.success();
        }catch (Exception e){
            System.out.println(e);
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }
    public RespBean register(String alias, String phone, String password, String avatar) {
        try{
            Customer customerById = customerMapper.selectById(phone);
            if(StringUtils.isEmpty(customerById)){
                return RespBean.error(RespBeanEnum.ACCOUNT_REPEAT);
            }
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("alias",alias);
            Customer customerByAlias = customerMapper.selectOne(wrapper);
            if(StringUtils.isEmpty(customerByAlias)){
                return RespBean.error(RespBeanEnum.NAME_REPEAT);
            }
            Customer customer = new Customer();
            customer.setAlias(alias);
            customer.setPhone(phone);
            customer.setAvatar(avatar);
            String md5_password = DigestUtils.md5DigestAsHex(password.getBytes());
            customer.setPassword(md5_password);
            customerMapper.insert(customer);
            return RespBean.success();
        }catch (Exception e){
            System.out.println(e);
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

    public RespBean getCusInfoByToken(String token) {
        try{
            String phone = tokenUtil.getCusIdFromToken(token);
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("phone",phone);
            System.out.println(phone);
            Customer customer = customerMapper.selectOne(wrapper);
            if(StringUtils.isEmpty(customer)){
                return RespBean.error(RespBeanEnum.CUS_INFO_ERROR);
            }
            CustomerInfoVo customerInfoVo = CustomerInfoVo.getCustomerInfo(customer);
            customerInfoVo.setToken(token);
            return RespBean.success(customerInfoVo);
        }catch (Exception e){
            System.out.println(e);
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }
}
