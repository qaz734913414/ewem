package com.ewem.common.core.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * mybatis plus Entity基类
 *
 * @author ewem
 */
@Data
public class BaseEntityPlus implements Serializable {

    private static final long serialVersionUID = -4331503211072086709L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "status", fill = FieldFill.INSERT)
    private String status;

    @TableLogic(value = "0", delval = "2")
    private String delFlag;

    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}
