package com.atxiaoming.service.impl;

import com.atxiaoming.entity.ProdSku;
import com.atxiaoming.entity.UserMenu;
import com.atxiaoming.mapper.ProdSkuMapper;
import com.atxiaoming.service.IProdSkuService;
import com.atxiaoming.vo.CusProdSkuRequestVo;
import com.atxiaoming.vo.RespBean;
import com.atxiaoming.vo.RespBeanEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 小明
 * @since 2021-01-15
 */
@Service
public class ProdSkuServiceImpl extends ServiceImpl<ProdSkuMapper, ProdSku> implements IProdSkuService {

    @Autowired
    private ProdSkuMapper prodSkuMapper;

    public RespBean addProdSku(List<ProdSku> prodSkus) {
        try{
            for(ProdSku prodSku : prodSkus){
                prodSkuMapper.insert(prodSku);
            }
            return RespBean.success();
        }catch (Exception e){
            System.out.println(e);
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

    public RespBean getProdSku(Integer prodId) {
        try{
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("prod_id",prodId);
            List<ProdSku> prodSkus = prodSkuMapper.selectList(wrapper);
            return RespBean.success(prodSkus);
        }catch (Exception e){
            System.out.println(e);
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

    public RespBean editProdSku(List<ProdSku> prodSkus) {
        try{
            for(ProdSku prodSku : prodSkus){
                prodSkuMapper.updateById(prodSku);
            }
            return RespBean.success();
        }catch (Exception e){
            System.out.println(e);
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

    public RespBean getSkuByAttrs(Integer attr1Id, Integer attr2Id, Integer attr3Id, Integer prodId) {
        try{
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("attr1_id",attr1Id);
            if(!StringUtils.isEmpty((attr2Id))){
                wrapper.eq("attr2_id",attr2Id);
            }
            if(!StringUtils.isEmpty((attr3Id))){
                wrapper.eq("attr3_id",attr3Id);
            }
            wrapper.eq("prod_id",prodId);
            ProdSku prodSku = prodSkuMapper.selectOne(wrapper);
            return RespBean.success(prodSku);
        }catch (Exception e){
            System.out.println(e);
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }
}
