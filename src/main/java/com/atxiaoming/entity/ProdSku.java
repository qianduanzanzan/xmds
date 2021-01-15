package com.atxiaoming.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 小明
 * @since 2021-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_prod_sku")
public class ProdSku implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 产品属性1ID
     */
    private Integer attr1Id;

    /**
     * 产品属性2ID
     */
    private Integer attr2Id;

    /**
     * 产品库存
     */
    private Integer sku;

    /**
     * 产品价格
     */
    private BigDecimal price;


}
