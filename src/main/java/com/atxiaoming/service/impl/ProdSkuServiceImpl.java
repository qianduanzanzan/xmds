package com.atxiaoming.service.impl;

import com.atxiaoming.entity.ProdSku;
import com.atxiaoming.entity.UserMenu;
import com.atxiaoming.mapper.ProdSkuMapper;
import com.atxiaoming.service.IProdSkuService;
import com.atxiaoming.vo.RespBean;
import com.atxiaoming.vo.RespBeanEnum;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

}
