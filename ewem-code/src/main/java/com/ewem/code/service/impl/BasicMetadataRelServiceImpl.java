package com.ewem.code.service.impl;

import com.ewem.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import com.ewem.common.core.mybatisplus.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ewem.code.domain.BasicMetadataRel;
import com.ewem.code.mapper.BasicMetadataRelMapper;
import com.ewem.code.service.IBasicMetadataRelService;

import java.util.List;
import java.util.Collection;

/**
 * 基础-元数据关联Service业务层处理
 *
 * @author ewem
 * @date 2021-08-29
 */
@Service
public class BasicMetadataRelServiceImpl extends ServicePlusImpl<BasicMetadataRelMapper, BasicMetadataRel> implements IBasicMetadataRelService {

    @Override
    public BasicMetadataRel queryById(Long id){
        return getById(id);
    }

    @Override
    public List<BasicMetadataRel> queryList(BasicMetadataRel basicMetadataRel) {
        return list(buildQueryWrapper(basicMetadataRel));
    }

    private LambdaQueryWrapper<BasicMetadataRel> buildQueryWrapper(BasicMetadataRel basicMetadataRel) {
        //Map<String, Object> params = basicMetadataRel.getParams();
        LambdaQueryWrapper<BasicMetadataRel> lqw = Wrappers.lambdaQuery();
        lqw.eq(basicMetadataRel.getBasicId() != null, BasicMetadataRel::getBasicId, basicMetadataRel.getBasicId());
        lqw.eq(basicMetadataRel.getMetadataId() != null, BasicMetadataRel::getMetadataId, basicMetadataRel.getMetadataId());
        lqw.eq(basicMetadataRel.getIsShow() != null, BasicMetadataRel::getIsShow, basicMetadataRel.getIsShow());
        lqw.eq(StringUtils.isNotBlank(basicMetadataRel.getValue()), BasicMetadataRel::getValue, basicMetadataRel.getValue());
        return lqw;
    }

    @Override
    public Boolean insertBy(BasicMetadataRel basicMetadataRel) {
        validEntityBeforeSave(basicMetadataRel);
        return save(basicMetadataRel);
    }

    @Override
    public Boolean updateBy(BasicMetadataRel basicMetadataRel) {
        validEntityBeforeUpdate(basicMetadataRel);
        return updateById(basicMetadataRel);
    }

    /**
     * 保存前的数据校验
     *
     * @param basicMetadataRel 实体类数据
     */
    private void validEntityBeforeSave(BasicMetadataRel basicMetadataRel){
    }

    /**
     * 修改前的数据校验
     *
     * @param basicMetadataRel 实体类数据
     */
    private void validEntityBeforeUpdate(BasicMetadataRel basicMetadataRel) {
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
        }
        return removeByIds(ids);
    }
}
