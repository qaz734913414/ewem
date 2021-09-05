package com.ewem.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ewem.code.domain.BasicMetadata;
import com.ewem.code.mapper.BasicMetadataMapper;
import com.ewem.code.service.IBasicMetadataService;
import com.ewem.common.core.mybatisplus.ServicePlusImpl;
import com.ewem.common.exception.CustomException;
import com.ewem.common.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * 基础元数据Service业务层处理
 *
 * @author ewem
 * @date 2021-08-28
 */
@Service
public class BasicMetadataServiceImpl extends ServicePlusImpl<BasicMetadataMapper, BasicMetadata> implements IBasicMetadataService {

    @Override
    public BasicMetadata queryById(Long id) {
        return getById(id);
    }

    @Override
    public List<BasicMetadata> queryList(BasicMetadata basicMetadata) {
        return list(buildQueryWrapper(basicMetadata));
    }

    private LambdaQueryWrapper<BasicMetadata> buildQueryWrapper(BasicMetadata basicMetadata) {
        //Map<String, Object> params = basicMetadata.getParams();
        LambdaQueryWrapper<BasicMetadata> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(basicMetadata.getCode()), BasicMetadata::getCode, basicMetadata.getCode());
        lqw.like(StringUtils.isNotBlank(basicMetadata.getName()), BasicMetadata::getName, basicMetadata.getName());
        return lqw;
    }

    @Override
    public Boolean insertBy(BasicMetadata basicMetadata) {
        validEntityBeforeSave(basicMetadata);
        return save(basicMetadata);
    }

    @Override
    public Boolean updateBy(BasicMetadata basicMetadata) {
        validEntityBeforeUpdate(basicMetadata);
        return updateById(basicMetadata);
    }

    /**
     * 保存前的数据校验
     *
     * @param basicMetadata 实体类数据
     */
    private void validEntityBeforeSave(BasicMetadata basicMetadata) {
        BasicMetadata basicDataEntity = queryBy(basicMetadata);
        if (null != basicDataEntity) {
            throw new CustomException("数据编码[" + basicMetadata.getCode() + "]已存在");
        }
    }

    /**
     * 修改前的数据校验
     *
     * @param basicMetadata 实体类数据
     */
    private void validEntityBeforeUpdate(BasicMetadata basicMetadata) {
        BasicMetadata basicDataEntity = queryBy(basicMetadata);
        if (null != basicDataEntity) {
            throw new CustomException("数据编码[" + basicMetadata.getCode() + "]已存在");
        }
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
        }
        return removeByIds(ids);
    }

    @Override
    public BasicMetadata queryBy(BasicMetadata basicMetadata) {
        LambdaQueryWrapper<BasicMetadata> lqw = Wrappers.lambdaQuery();
        lqw.eq(BasicMetadata::getCode, basicMetadata.getCode());
        return this.getOne(lqw);
    }
}
