package com.atxiaoming.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 小明
 * @since 2021-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 用户加密过后的密码
     */
    private String password;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户头像的地址
     */
    private String avatar;

    /**
     * 用户创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createAt;

    /**
     * 用户信息更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateAt;

    /**
     * 用户启用停用标志（0启用，1停用）
     */
    private Integer stopFlag;

    public void deletePassword(){
        this.password = "";
    }
}
