package com.ewem.code.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ewem.common.core.domain.BaseEntityPlus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("ewem_acquisition_template")
public class AcquisitionTemplate extends BaseEntityPlus {

    private static final long serialVersionUID = 2799259416360062912L;


    private String name;


    private String remark;

}
