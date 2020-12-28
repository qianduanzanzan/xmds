package com.atxiaoming.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
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
    @TableId(type = IdType.AUTO)
    private int id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT) //添加自动填充，在插入时使用
    private String createAt;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE) //添加自动填充，在插入和修改时使用
    private String updateAt;

    /**
     * 删除时间
     */
    private String deleteAt;
    @TableLogic //mp自带的逻辑删除注解
    private Integer delFlag; //删除标志


}
