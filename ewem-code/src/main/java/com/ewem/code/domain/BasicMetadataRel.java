package com.ewem.code.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 基础-元数据关联对象 ewem_basic_metadata_rel
 *
 * @author ewem
 * @date 2021-08-29
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("ewem_basic_metadata_rel")
public class BasicMetadataRel implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * $column.columnComment
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 基础数据ID
     */
    private Long basicId;

    /**
     * 基础元数据ID
     */
    private Long metadataId;

    /**
     * 是否显示
     */
    private Integer isShow;

    /**
     * 数据值
     */
    private String value;

}
