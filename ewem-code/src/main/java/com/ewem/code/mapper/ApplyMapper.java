package com.ewem.code.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ewem.code.domain.Apply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 码申请Mapper接口
 *
 * @author ewem
 * @date 2021-08-08
 */
@Mapper
public interface ApplyMapper extends BaseMapper<Apply> {
    /**
     * 查询码申请
     *
     * @param id 码申请主键
     * @return 码申请
     */
    public Apply selectApplyById(Long id);

    /**
     * 查询码申请列表
     *
     * @param apply 码申请
     * @return 码申请集合
     */
    public List<Apply> selectApplyList(Apply apply);

    /**
     * 新增码申请
     *
     * @param apply 码申请
     * @return 结果
     */
    public int insertApply(Apply apply);

    /**
     * 修改码申请
     *
     * @param apply 码申请
     * @return 结果
     */
    public int updateApply(Apply apply);

    /**
     * 删除码申请
     *
     * @param id 码申请主键
     * @return 结果
     */
    public int deleteApplyById(Long id);

    /**
     * 批量删除码申请
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteApplyByIds(Long[] ids);

    /**
     * 生成状态查询码申请
     *
     * @param applyStatus 生成状态
     * @return
     */
    @Select("select * from ewem_apply where apply_status = #{applyStatus} order by id limit 1")
    Apply selectOneByApplyStatus(String applyStatus);
}
