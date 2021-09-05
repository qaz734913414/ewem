package com.ewem.code.service;

import com.ewem.code.domain.Apply;

import java.util.List;

/**
 * 码申请Service接口
 *
 * @author ewem
 * @date 2021-08-08
 */
public interface IApplyService {
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
     * 批量删除码申请
     *
     * @param ids 需要删除的码申请主键集合
     * @return 结果
     */
    public int deleteApplyByIds(Long[] ids);

    /**
     * 删除码申请信息
     *
     * @param id 码申请主键
     * @return 结果
     */
    public int deleteApplyById(Long id);

    /**
     * 生成状态查询码申请
     *
     * @param applyStatus 生成状态
     * @return
     */
    Apply selectOneByApplyStatus(String applyStatus);


}
