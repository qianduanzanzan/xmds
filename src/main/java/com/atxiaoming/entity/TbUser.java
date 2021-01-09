package com.atxiaoming.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 
 * </p>
 *
 * @author 小明
 * @since 2020-12-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbUser implements Serializable {

    /**
     * 用户ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private String createAt;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateAt;

    /**
     * 删除时间
     */
    private String deleteAt;
    private Integer delFlag; //删除标志

    public void deletePassword(){
        this.password = "";
    }

}
