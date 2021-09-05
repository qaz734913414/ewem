package com.ewem.code.service.impl;

import com.ewem.code.domain.Code;
import com.ewem.code.mapper.CodeMapper;
import com.ewem.code.service.ICodeService;
import com.ewem.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 码管理Service业务层处理
 *
 * @author ewem
 * @date 2021-08-14
 */
@Service
public class CodeServiceImpl implements ICodeService {

    @Autowired
    private CodeMapper codeMapper;

    /**
     * 查询码管理
     *
     * @param id 码管理主键
     * @return 码管理
     */
    @Override
    public Code selectCodeById(Long id) {
        return codeMapper.selectCodeById(id);
    }

    /**
     * 查询码管理列表
     *
     * @param code 码管理
     * @return 码管理
     */
    @Override
    public List<Code> selectCodeList(Code code) {
        return codeMapper.selectCodeList(code);
    }

    /**
     * 新增码管理
     *
     * @param code 码管理
     * @return 结果
     */
    @Override
    public int insertCode(Code code) {
        code.setCreateTime(DateUtils.getNowDate());
        return codeMapper.insertCode(code);
    }

    /**
     * 修改码管理
     *
     * @param code 码管理
     * @return 结果
     */
    @Override
    public int updateCode(Code code) {
        code.setUpdateTime(DateUtils.getNowDate());
        return codeMapper.updateCode(code);
    }

    /**
     * 批量删除码管理
     *
     * @param ids 需要删除的码管理主键
     * @return 结果
     */
    @Override
    public int deleteCodeByIds(Long[] ids) {
        return codeMapper.deleteCodeByIds(ids);
    }

    /**
     * 删除码管理信息
     *
     * @param id 码管理主键
     * @return 结果
     */
    @Override
    public int deleteCodeById(Long id) {
        return codeMapper.deleteCodeById(id);
    }
}
