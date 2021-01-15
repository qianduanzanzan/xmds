package com.atxiaoming.vo;

import lombok.Data;

@Data
public class CategoryPagenationVo {
    private String categoryName;
    private Integer stopFlag;
    private Integer current;
    private Integer size;
}
