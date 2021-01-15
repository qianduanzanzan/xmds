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
 * @since 2021-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_prod_attr")
public class ProdAttr implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 对应产品的id
     */
    private Integer prodId;

    /**
     * 属性名
     */
    private String attrKey;

    /**
     * 属性值
     */
    private String attrVal;


}
