package com.atxiaoming.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class UserInfoVo {
    private Long id;
    private String userName;
    private String avatar;
    private Integer roleId;
    private String createAt;
    private String updateAt;
    private String token;
}
