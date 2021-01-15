package com.atxiaoming.vo;

import lombok.Data;

@Data
public class MenuPagenitionVo {
    private String MenuName;
    private Integer stopFlag;
    private Integer current;
    private Integer size;
}
