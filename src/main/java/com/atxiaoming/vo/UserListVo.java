package com.atxiaoming.vo;

import lombok.Data;

@Data
public class UserListVo {
    private Long id;
    private String account;
    private String userName;
    private String avatar;
    private Integer roleId;
    private String createAt;
    private String updateAt;
}
