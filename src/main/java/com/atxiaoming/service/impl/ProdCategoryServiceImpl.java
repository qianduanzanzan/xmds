package com.atxiaoming.service.impl;

import com.atxiaoming.entity.Menu;
import com.atxiaoming.entity.ProdCategory;
import com.atxiaoming.mapper.ProdCategoryMapper;
import com.atxiaoming.service.IProdCategoryService;
import com.atxiaoming.vo.CategoryPagenationVo;
import com.atxiaoming.vo.MenuPagenitionVo;
import com.atxiaoming.vo.RespBean;
import com.atxiaoming.vo.RespBeanEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 小明
 * @since 2021-01-14
 */
@Service
public class ProdCategoryServiceImpl extends ServiceImpl<ProdCategoryMapper, ProdCategory> implements IProdCategoryService {

    @Autowired
    private ProdCategoryMapper prodCategoryMapper;

    public RespBean add(String categoryName,String img) {
        try{
            ProdCategory prodCategory = new ProdCategory();
            prodCategory.setCategoryName(categoryName);
            prodCategory.setImg(img);
            prodCategoryMapper.insert(prodCategory);
            return RespBean.success();
        }catch (Exception e){
            System.out.println(e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

    public RespBean changeStatus(Integer id) {
        try{
            ProdCategory prodCategory = prodCategoryMapper.selectById(id);
            ProdCategory newProdCategory = new ProdCategory();
            newProdCategory.setId(id);
            if(prodCategory.getStopFlag().equals(0)){
                newProdCategory.setStopFlag(1);
            }else{
                newProdCategory.setStopFlag(0);
            }
            prodCategoryMapper.updateById(newProdCategory);
            return RespBean.success();
        }catch (Exception e){
            System.out.println(e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

    public RespBean edit(Integer id,String categoryName,String img) {
        try{
            ProdCategory newProdCategory = new ProdCategory();
            newProdCategory.setId(id);
            if(!StringUtils.isEmpty(categoryName)){
                newProdCategory.setCategoryName(categoryName);
            }
            if(!StringUtils.isEmpty(img)){
                newProdCategory.setImg(img);
            }
            prodCategoryMapper.updateById(newProdCategory);
            return RespBean.success();
        }catch (Exception e){
            System.out.println(e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

    public RespBean getCategoryPage(CategoryPagenationVo categoryPagenationVo) {
        try{
            QueryWrapper wrapper = new QueryWrapper();
            if(!StringUtils.isEmpty(categoryPagenationVo.getStopFlag())){
                wrapper.eq("stop_flag",categoryPagenationVo.getStopFlag());
            }
            if(!StringUtils.isEmpty(categoryPagenationVo.getCategoryName())){
                wrapper.like("category_name",categoryPagenationVo.getCategoryName());
            }
            Page<ProdCategory> categoryIPage = new Page<>(categoryPagenationVo.getCurrent(), categoryPagenationVo.getSize());
            IPage<ProdCategory> categoryPage = prodCategoryMapper.selectPage(categoryIPage, wrapper);
            return RespBean.success(categoryPage);
        }catch (Exception e){
            System.out.println(e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

    public RespBean geAlltCategory() {
        try{
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("stop_flag",0);
            List<ProdCategory> prodCategories = prodCategoryMapper.selectList(wrapper);
            return RespBean.success(prodCategories);
        }catch (Exception e){
            System.out.println(e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }
}
