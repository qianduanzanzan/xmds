package com.atxiaoming.service.impl;

import com.atxiaoming.entity.Menu;
import com.atxiaoming.entity.ProdSku;
import com.atxiaoming.entity.ShopCart;
import com.atxiaoming.mapper.ProdSkuMapper;
import com.atxiaoming.mapper.ShopCartMapper;
import com.atxiaoming.service.IShopCartService;
import com.atxiaoming.vo.RespBean;
import com.atxiaoming.vo.RespBeanEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 小明
 * @since 2021-01-29
 */
@Service
public class ShopCartServiceImpl extends ServiceImpl<ShopCartMapper, ShopCart> implements IShopCartService {

    @Autowired
    private ShopCartMapper shopCartMapper;

    @Autowired
    private ProdSkuMapper prodSkuMapper;

    public RespBean addShopCart(String phone, Integer prodId, Integer prodSkuId, Integer quantity) {
        try{
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("phone",phone);
            wrapper.eq("prod_sku_id",prodSkuId);
            ShopCart oldShopCart = shopCartMapper.selectOne(wrapper);
            if(!StringUtils.isEmpty(oldShopCart)){
                return RespBean.error(RespBeanEnum.PROD_IN_CART);
            }
            ShopCart shopCart = new ShopCart();
            ProdSku prodSku = prodSkuMapper.selectById(prodSkuId);
            if(quantity > prodSku.getSku()){
                return RespBean.error(RespBeanEnum.SKU_INSUFFICIENT);
            }
            shopCart.setPhone(phone);
            shopCart.setProdId(prodId);
            shopCart.setProdSkuId(prodSkuId);
            shopCart.setQuantity(quantity);
            shopCartMapper.insert(shopCart);
            return RespBean.success();
        }catch (Exception e){
            System.out.println(e);
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }
}
