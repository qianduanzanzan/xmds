package com.atxiaoming.entity;

import com.baomidou.mybatisplus.annotation.*;

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
 * @since 2021-01-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;
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
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createAt;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateAt;

    /**
     * 删除标志
     */
    private Integer stopFlag;


}
