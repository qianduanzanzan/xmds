package com.atxiaoming.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 小明
 * @since 2020-12-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TbMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单中文名
     */
    private String menuName;

    /**
     * 路由地址
     */
    private String routeName;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 父菜单ID
     */
    private Long pId;

    /**
     * 创建时间
     */
    private String createdAt;

    /**
     * 创建时间
     */
    private String updateAt;

    /**
     * 删除标志
     */
    private Boolean delFlag;

    /**
     * 删除时间
     */
    private String deleteAt;

    /**
     * 删除人
     */
    private Long deleteFrom;


}
