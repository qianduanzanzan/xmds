package com.atxiaoming.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 小明
 * @since 2021-01-13
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
     * 创建时间
     */
    private Timestamp createAt;

    /**
     * 更新时间
     */
    private Timestamp updateAt;

    /**
     * 停用标志(0启用，1停用)
     */
    private Boolean stopFlag;


}
