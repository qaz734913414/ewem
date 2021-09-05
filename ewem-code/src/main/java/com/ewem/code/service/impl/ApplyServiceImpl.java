package com.ewem.code.service.impl;

import com.ewem.code.domain.Apply;
import com.ewem.code.mapper.ApplyMapper;
import com.ewem.code.service.IApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 码申请Service业务层处理
 *
 * @author ewem
 * @date 2021-08-08
 */
@Service
public class ApplyServiceImpl implements IApplyService {

    @Autowired
    private ApplyMapper applyMapper;

    /**
     * 查询码申请
     *
     * @param id 码申请主键
     * @return 码申请
     */
    @Override
    public Apply selectApplyById(Long id) {
        return applyMapper.selectApplyById(id);
    }

    /**
     * 查询码申请列表
     *
     * @param apply 码申请
     * @return 码申请
     */
    @Override
    public List<Apply> selectApplyList(Apply apply) {
        return applyMapper.selectApplyList(apply);
    }

    /**
     * 新增码申请
     *
     * @param apply 码申请
     * @return 结果
     */
    @Override
    public int insertApply(Apply apply) {
        apply.setApplyStatus("1");
        return applyMapper.insertApply(apply);
    }

    /**
     * 修改码申请
     *
     * @param apply 码申请
     * @return 结果
     */
    @Override
    public int updateApply(Apply apply) {
        return applyMapper.updateApply(apply);
    }

    /**
     * 批量删除码申请
     *
     * @param ids 需要删除的码申请主键
     * @return 结果
     */
    @Override
    public int deleteApplyByIds(Long[] ids) {
        return applyMapper.deleteApplyByIds(ids);
    }

    /**
     * 删除码申请信息
     *
     * @param id 码申请主键
     * @return 结果
     */
    @Override
    public int deleteApplyById(Long id) {
        return applyMapper.deleteApplyById(id);
    }

    /**
     * 生成状态查询码申请
     *
     * @param applyStatus 生成状态
     * @return
     */
    @Override
    public Apply selectOneByApplyStatus(String applyStatus) {
        return applyMapper.selectOneByApplyStatus(applyStatus);
    }

}
