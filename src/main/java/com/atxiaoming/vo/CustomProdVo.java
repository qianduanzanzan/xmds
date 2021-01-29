package com.atxiaoming.vo;


import com.atxiaoming.entity.Prod;
import com.atxiaoming.entity.ProdAttr;
import lombok.Data;

import java.util.List;

@Data
public class CustomProdVo {
    private Prod prod;
    private List<ProdAttr> prodAttrs;
}
