package com.atxiaoming.service.impl;

import com.atxiaoming.entity.CusAddress;
import com.atxiaoming.mapper.CusAddressMapper;
import com.atxiaoming.service.ICusAddressService;
import com.atxiaoming.vo.RespBean;
import com.atxiaoming.vo.RespBeanEnum;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 小明
 * @since 2021-02-01
 */
@Service
public class CusAddressServiceImpl extends ServiceImpl<CusAddressMapper, CusAddress> implements ICusAddressService {

    @Autowired
    private CusAddressMapper cusAddressMapper;

    public RespBean addAddress(String province, String phone, String city, String area, String detail) {
        try{

            return RespBean.success();
        }catch (Exception e){
            System.out.println(e);
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }
}
