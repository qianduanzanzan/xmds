package com.atxiaoming.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Timestamp;
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
 * @since 2021-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_prod_category")
public class ProdCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品分类名称
     */
    private String categoryName;

    /**
     * 创建时间
     */
    private Timestamp createAt;

    /**
     * 更改时间
     */
    private Timestamp updateAt;

    /**
     * 停用标志（0启用1停用）
     */
    private Boolean stopFlag;


}
