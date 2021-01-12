package com.atxiaoming.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2021-01-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_prod_brand")
public class ProdBrand implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;

    /**
     * 更新时间
     */
    private LocalDateTime updateAt;

    /**
     * 停用标志（0启用1停用）
     */
    private Boolean stopFlag;


}
