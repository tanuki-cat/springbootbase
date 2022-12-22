package com.lichi.springbootbase.auth.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description: 用户角色关联对象
 * @author: lychee
 * @Date: 2022/12/19 21:46
 * @Version: 1.0
 * @Since: 2022/12/19
 */
@Data
public class UserRoleLink implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 角色Id
     */
    private Long roleId;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    /**
     * 逻辑删除字段 0:未删除 1:已删除
     */
    @TableLogic
    private Integer isDeleted;
}
