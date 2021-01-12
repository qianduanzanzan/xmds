package com.atxiaoming.entity;

import java.math.BigDecimal;
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
 * @since 2021-01-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_prod_sku")
public class ProdSku implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 属性1的ID
     */
    private Integer attr1Id;

    /**
     * 属性2的ID
     */
    private Integer attr2Id;

    /**
     * 产品的价格
     */
    private BigDecimal prodPrice;

    /**
     * 产品库存
     */
    private Integer prodSku;


}
