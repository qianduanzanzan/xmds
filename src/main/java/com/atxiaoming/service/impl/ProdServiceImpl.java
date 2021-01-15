package com.atxiaoming.service.impl;

import com.atxiaoming.entity.OssTemplate;
import com.atxiaoming.entity.Prod;
import com.atxiaoming.entity.ProdCategory;
import com.atxiaoming.mapper.ProdMapper;
import com.atxiaoming.service.IProdService;
import com.atxiaoming.vo.ProdEditVo;
import com.atxiaoming.vo.ProdPaginationVo;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 小明
 * @since 2021-01-14
 */
@Service
public class ProdServiceImpl extends ServiceImpl<ProdMapper, Prod> implements IProdService {

    @Autowired
    private ProdMapper prodMapper;

    @Autowired
    private OssTemplate ossTemplate;

    public RespBean addProd(String prodName, Integer categoryId,String categoryName, MultipartFile[] imgs,String description) {
        try{
            imgs_str(imgs);
            Prod prod = new Prod();
            prod.setProdName(prodName);
            String imgs_str = imgs_str(imgs);
            prod.setImgs(imgs_str);
            prod.setCategoryName(categoryName);
            prod.setCategoryId(categoryId);
            prod.setDescription(description);
            prodMapper.insert(prod);
            return RespBean.success();
        }catch (Exception e){
            System.out.println(e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

    public RespBean editProd(ProdEditVo prodEditVo) {
        try{
            Prod prod = new Prod();
            prod.setId(prodEditVo.getId());
            if(prodEditVo.getImgs().length > 0){
                Prod oldProd = prodMapper.selectById(prodEditVo.getId());
                String oldImgs = oldProd.getImgs();
                String[] imgList = oldImgs.split(",");
                for (String oldImg:imgList){
                    ossTemplate.delete(oldImg.substring(47));
                }
                MultipartFile[] imgs = prodEditVo.getImgs();
                String imgs_str = imgs_str(imgs);
                prod.setImgs(imgs_str);
            }
            if(!prodEditVo.getProdName().isEmpty()){
                prod.setProdName(prodEditVo.getProdName());
            }
            if(!prodEditVo.getCategoryName().isEmpty()){
                prod.setCategoryName(prodEditVo.getCategoryName());
                prod.setCategoryId(prodEditVo.getCategoryId());
            }
            if(!prodEditVo.getDescription().isEmpty()){
                prod.setDescription(prodEditVo.getDescription());
            }
            prodMapper.updateById(prod);
            return RespBean.success();
        }catch (Exception e){
            System.out.println(e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

    public RespBean getProdPage(ProdPaginationVo prodPaginationVo) {
        try{
            QueryWrapper wrapper = new QueryWrapper();
            if(!prodPaginationVo.getProdName().isEmpty()){
                wrapper.like("prod_name",prodPaginationVo.getProdName());
            }
            if(!StringUtils.isEmpty(prodPaginationVo.getCategoryId())){
                wrapper.like("category_id",prodPaginationVo.getCategoryId());
            }
            if(!StringUtils.isEmpty(prodPaginationVo.getStopFlag())){
                wrapper.like("prod_name",prodPaginationVo.getStopFlag());
            }
            Page<Prod> prodIPage = new Page<>(prodPaginationVo.getCurrent(), prodPaginationVo.getSize());
            IPage<Prod> prodPage = prodMapper.selectPage(prodIPage, wrapper);
            return RespBean.success(prodPage);
        }catch (Exception e){
            System.out.println(e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

    public RespBean getProdInfo(Integer id) {
        try{
            Prod prod = prodMapper.selectById(id);
            return RespBean.success(prod);
        }catch (Exception e){
            System.out.println(e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

    public RespBean changeProdStatus(Integer id) {
        try{
            Prod oldProd = prodMapper.selectById(id);
            Prod newProd = new Prod();
            newProd.setId(id);
            if(oldProd.getStopFlag().equals(0)){
                newProd.setStopFlag(1);
            }else{
                newProd.setStopFlag(0);
            }
            prodMapper.updateById(newProd);
            return RespBean.success();
        }catch (Exception e){
            System.out.println(e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RespBean.error(RespBeanEnum.ERROR,e);
        }
    }

    private String imgs_str(MultipartFile[] imgs) throws IOException {
        String imgs_str = "";
        for( MultipartFile img: imgs){
            String oldName = img.getOriginalFilename();
            InputStream inputStream = img.getInputStream();
            String newFileName = UUID.randomUUID().toString().replace("-", "").substring(4, 16)+"_"+oldName;
            String uploadFilePath = ossTemplate.upload(inputStream, newFileName);
            imgs_str += "," + uploadFilePath;
        }
        return imgs_str;
    }
}
