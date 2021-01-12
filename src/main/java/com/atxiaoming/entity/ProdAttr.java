package com.atxiaoming.entity;

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
@TableName("tb_prod_attr")
public class ProdAttr implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品ID
     */
    private Integer prodId;

    /**
     * 产品属性key
     */
    private String attrKey;

    /**
     * 产品属性值
     */
    private String attrValue;


}
