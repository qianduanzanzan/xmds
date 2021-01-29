package com.atxiaoming.vo;

import com.atxiaoming.entity.Customer;
import lombok.Data;

@Data
public class CustomerInfoVo {
    private String phone;
    private String alias;
    private String avatar;
    private String token;

    public static CustomerInfoVo getCustomerInfo(Customer customer){
        CustomerInfoVo customerInfoVo = new CustomerInfoVo();
        customerInfoVo.setPhone(customer.getPhone());
        customerInfoVo.setAlias(customer.getAlias());
        customerInfoVo.setAvatar(customer.getAvatar());
        return customerInfoVo;
    }
}
