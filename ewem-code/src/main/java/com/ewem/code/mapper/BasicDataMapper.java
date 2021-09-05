package com.ewem.code.mapper;

import com.ewem.code.domain.BasicData;
import com.ewem.common.core.mybatisplus.BaseMapperPlus;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * 基础数据Mapper接口
 *
 * @author ewem
 * @date 2021-08-28
 */
public interface BasicDataMapper extends BaseMapperPlus<BasicData> {

    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "id", property = "basicMetadataRelList",
                    many = @Many(select = "com.ewem.code.mapper.BasicMetadataRelMapper.selectByAreaId"))
    })
    @Select("SELECT * FROM ewem_basic_data WHERE id = #{id}")
    BasicData getById(Long id);

}
