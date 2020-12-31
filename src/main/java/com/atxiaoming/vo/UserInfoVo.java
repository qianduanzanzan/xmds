package com.atxiaoming.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfoVo implements Serializable {
    private Long id;
    private String account;
    private String userName;
    private String avatar;
    private Integer roleId;
    private String createAt;
    private String updateAt;
    private String token;
}
