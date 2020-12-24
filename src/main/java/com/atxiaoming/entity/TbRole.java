package com.atxiaoming.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 小明
 * @since 2020-12-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TbRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT) //添加自动填充，在插入时使用
    private LocalDateTime createAt;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE) //添加自动填充，在插入和修改时使用
    private LocalDateTime updateAt;

    /**
     * 删除时间
     */
    private LocalDateTime deleteAt;


}
