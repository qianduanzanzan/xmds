package com.atxiaoming.vo;

import com.atxiaoming.entity.Prod;
import com.atxiaoming.entity.ProdSku;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProdResVo {
    private Integer id;
    private String prodName;
    private Integer categoryId;
    private String imgs;
    private String description;
    private BigDecimal price;
    private Integer attr1Id;
    private Integer attr2Id;
    private Integer attr3Id;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Integer stopFlag;

    public static ProdResVo toProdResVo(Prod prod, ProdSku prodSku){
        ProdResVo prodResVo = new ProdResVo();
        prodResVo.setId(prod.getId());
        prodResVo.setProdName(prod.getProdName());
        prodResVo.setCategoryId(prod.getCategoryId());
        prodResVo.setImgs(prod.getImgs());
        prodResVo.setDescription(prod.getDescription());
        prodResVo.setPrice(prodSku.getPrice());
        prodResVo.setAttr1Id(prodSku.getAttr1Id());
        prodResVo.setAttr2Id(prodSku.getAttr2Id());
        prodResVo.setAttr3Id(prodSku.getAttr3Id());
        return prodResVo;
    }
}
