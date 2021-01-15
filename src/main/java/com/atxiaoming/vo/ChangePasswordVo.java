package com.atxiaoming.vo;

import lombok.Data;

@Data
public class ChangePasswordVo {
    private Integer id;
    private String oldPassword;
    private String newPassword;
}
