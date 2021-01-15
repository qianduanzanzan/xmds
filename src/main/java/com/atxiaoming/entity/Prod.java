package com.atxiaoming.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 小明
 * @since 2021-01-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_prod")
public class Prod implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 产品分类ID
     */
    private Integer categoryId;

    /**
     * 产品分类名称
     */
    private String categoryName;

    /**
     * 产品图片
     */
    private String imgs;

    /**
     * 产品描述
     */
    private String description;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createAt;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateAt;

    /**
     * 停用标志（0启用1停用）
     */
    private Integer stopFlag;


}
