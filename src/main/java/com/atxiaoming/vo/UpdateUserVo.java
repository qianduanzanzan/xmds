package com.atxiaoming.vo;

import lombok.Data;

@Data
public class UpdateUserVo {
    private Long id;
    private String account;
    private String userName;
    private Integer roleId;
}
