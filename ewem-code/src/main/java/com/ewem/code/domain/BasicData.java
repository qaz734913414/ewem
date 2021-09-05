package com.ewem.code.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ewem.common.annotation.Excel;
import com.ewem.common.core.domain.BaseEntityPlus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

/**
 * 基础数据对象 ewem_basic_data
 *
 * @author ewem
 * @date 2021-08-28
 */
@EqualsAndHashCode(callSuper = true)
@TableName("ewem_basic_data")
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class BasicData extends BaseEntityPlus {
    private static final long serialVersionUID = 1L;

    /**
     * 数据编码
     */
    @Excel(name = "数据编码")
    @NotBlank(message = "数据编码不能为空")
    private String code;

    /**
     * 数据名称
     */
    @Excel(name = "数据名称")
    @NotBlank(message = "数据名称不能为空")
    private String name;

    /**
     * 数据类型
     */
    @Excel(name = "数据类型")
    @NotBlank(message = "数据类型不能为空")
    private String type;

    @Excel(name = "备注")
    private String remark;

    @TableField(exist = false)
    private Set<Long> addMetadataIds;

    @TableField(exist = false)
    private List<BasicMetadataRel> basicMetadataRelList;


}
