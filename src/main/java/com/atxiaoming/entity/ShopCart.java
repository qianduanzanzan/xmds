package com.atxiaoming.entity;

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
 * @since 2021-01-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_shop_cart")
public class ShopCart implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 客户ID
     */
    private String phone;

    /**
     * 产品ID
     */
    private Integer prodId;

    /**
     * 产品库存ID
     */
    private Integer prodSkuId;

    /**
     * 产品数量
     */
    private Integer quantity;


}
