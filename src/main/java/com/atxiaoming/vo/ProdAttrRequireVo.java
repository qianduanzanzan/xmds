package com.atxiaoming.vo;

import lombok.Data;

@Data
public class ProdAttrRequireVo {
    private Integer prodId;
    private String attrKey;
    private String[] attrVals;
}
