package com.atxiaoming.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 小明
 * @since 2021-01-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TbMenu implements Serializable {

    @TableId(type = IdType.AUTO)
    private long id;
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
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT) //添加自动填充，在插入时使用
    private String createAt;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE) //添加自动填充，在插入和修改时使用
    private String updateAt;

    /**
     * 删除标志
     */
    @TableLogic //mp自带的逻辑删除注解
    private Integer delFlag;

    /**
     * 删除时间
     */
    private String deleteAt;

    /**
     * 删除人
     */
    private Long deleteFrom;


}
