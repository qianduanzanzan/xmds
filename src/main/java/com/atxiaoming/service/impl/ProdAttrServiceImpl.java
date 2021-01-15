package com.atxiaoming.service.impl;

import com.atxiaoming.entity.ProdAttr;
import com.atxiaoming.mapper.ProdAttrMapper;
import com.atxiaoming.service.IProdAttrService;
import com.atxiaoming.vo.RespBean;
import com.atxiaoming.vo.RespBeanEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
public class ProdAttrServiceImpl extends ServiceImpl<ProdAttrMapper, ProdAttr> implements IProdAttrService {

    @Autowired
    private ProdAttrMapper prodAttrMapper;

    public RespBean addProdAttr(List<ProdAttr> prodAttrs) {
        try{
            for (ProdAttr prodAttr : prodAttrs){
                prodAttrMapper.insert(prodAttr);
            }
            return RespBean.success();
        }catch (Exception e){
            System.out.println(e);
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

//    public RespBean editProdAttr(List<ProdAttr> prodAttrs) {
//        try{
//            QueryWrapper wrapper = new QueryWrapper();
//            wrapper.eq("prod_id",prodAttrs.get(0).getProdId());
//            List<ProdAttr> oldProdAttrs = prodAttrMapper.selectList(wrapper);
//            for (ProdAttr prodAttr : prodAttrs){
//                if(StringUtils.isEmpty(prodAttr.getId())){
//                    prodAttrMapper.insert(prodAttr);
//                }else{
//                    if(check(oldProdAttrs,prodAttr)){
//                        prodAttrMapper.updateById(prodAttr);
//                    }else{
//
//                    }
//                }
//            }
//            return RespBean.success();
//        }catch (Exception e){
//            System.out.println(e);
//            return RespBean.error(RespBeanEnum.ERROR,e);
//        }
//    }
//
//    private Boolean check(List<ProdAttr> prodAttrs,ProdAttr prodAttr){
//        for (ProdAttr oldProdAttr : prodAttrs){
//            if(oldProdAttr.getId() == prodAttr.getProdId()){
//                return false;
//            }
//        }
//        return true;
//    }
}
