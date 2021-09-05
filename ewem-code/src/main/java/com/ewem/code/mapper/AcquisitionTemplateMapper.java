package com.ewem.code.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ewem.code.domain.AcquisitionTemplate;
import com.ewem.code.domain.Apply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 采集模板 mapper
 *
 * @author ewem
 * @date 2021-08-08
 */
@Mapper
public interface AcquisitionTemplateMapper extends BaseMapper<AcquisitionTemplate> {

}
