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
@TableName("tb_menu")
public class Menu implements Serializable {

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
     * 菜单类型(0菜单1按钮)
     */
    private Boolean status;

    /**
     * 创建时间
     */
    private String createAt;

    /**
     * 更新时间
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
