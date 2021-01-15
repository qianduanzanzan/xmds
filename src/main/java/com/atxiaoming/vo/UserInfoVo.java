package com.atxiaoming.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserInfoVo implements Serializable {
    private Integer id;
    private String account;
    private String userName;
    private String avatar;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String token;
}
