package com.lichi.springbootbase.auth.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @description: 权限对象
 * @author: lichi
 * @date: 2022/12/19 10:42
 * @version: 1.0
 * @since: 2022/12/19
 */
@Data
public class PermissionInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    @TableId
    private Long id;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限uri
     */
    private String permissionUri;

    /**
     * 权限描述
     */
    private String permissionMethod;

    /**
     * logical删除标识 0:未删除 1:已删除
     */
    @TableLogic
    private Integer isDeleted;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
