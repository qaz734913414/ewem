package com.ewem.code.mapper;

import com.ewem.code.domain.BasicMetadataRel;
import com.ewem.common.core.mybatisplus.BaseMapperPlus;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 基础-元数据关联Mapper接口
 *
 * @author ewem
 * @date 2021-08-29
 */
public interface BasicMetadataRelMapper extends BaseMapperPlus<BasicMetadataRel> {


    @Select("SELECT * FROM ewem_basic_metadata_rel WHERE basic_id = #{basicId}")
    List<BasicMetadataRel> selectByAreaId(Long basicId);
}
