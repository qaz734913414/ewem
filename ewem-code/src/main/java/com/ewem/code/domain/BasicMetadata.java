package com.ewem.code.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ewem.common.annotation.Excel;
import com.ewem.common.core.domain.BaseEntityPlus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 基础元数据对象 ewem_basic_metadata
 *
 * @author ewem
 * @date 2021-08-28
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Accessors(chain = true)
@TableName("ewem_basic_metadata")
@Data
public class BasicMetadata extends BaseEntityPlus implements Serializable {


    private static final long serialVersionUID = 1797768735538899079L;

    /**
     * 元数据编码
     */
    @Excel(name = "元数据编码")
    @NotBlank(message = "编码不能为空")
    private String code;

    /**
     * 元数据名称
     */
    @Excel(name = "元数据名称")
    @NotBlank(message = "名称不能为空")
    private String name;

    /**
     * 备注
     */
    private String remark;

}
