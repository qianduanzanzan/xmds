package com.atxiaoming.vo;

import lombok.Data;

@Data
public class ProdPaginationVo {
    private String prodName;
    private Integer stopFlag;
    private Integer categoryId;
    private Integer current;
    private Integer size;
}
