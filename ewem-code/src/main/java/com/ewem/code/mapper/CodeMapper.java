package com.ewem.code.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ewem.code.domain.Code;

import java.util.List;

/**
 * 码管理Mapper接口
 *
 * @author ewem
 * @date 2021-08-14
 */
public interface CodeMapper extends BaseMapper<Code> {
    /**
     * 查询码管理
     *
     * @param id 码管理主键
     * @return 码管理
     */
    public Code selectCodeById(Long id);

    /**
     * 查询码管理列表
     *
     * @param code 码管理
     * @return 码管理集合
     */
    public List<Code> selectCodeList(Code code);

    /**
     * 新增码管理
     *
     * @param code 码管理
     * @return 结果
     */
    public int insertCode(Code code);

    /**
     * 修改码管理
     *
     * @param code 码管理
     * @return 结果
     */
    public int updateCode(Code code);

    /**
     * 删除码管理
     *
     * @param id 码管理主键
     * @return 结果
     */
    public int deleteCodeById(Long id);

    /**
     * 批量删除码管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCodeByIds(Long[] ids);
}
