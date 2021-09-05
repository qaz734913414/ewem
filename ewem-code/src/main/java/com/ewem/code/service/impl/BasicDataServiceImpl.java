package com.ewem.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ewem.code.domain.BasicData;
import com.ewem.code.mapper.BasicDataMapper;
import com.ewem.code.service.IBasicDataService;
import com.ewem.common.core.mybatisplus.ServicePlusImpl;
import com.ewem.common.exception.CustomException;
import com.ewem.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * 基础数据Service业务层处理
 *
 * @author ewem
 * @date 2021-08-28
 */
@Service
public class BasicDataServiceImpl extends ServicePlusImpl<BasicDataMapper, BasicData> implements IBasicDataService {


    @Autowired
    BasicDataMapper basicDataMapper;

    @Override
    public BasicData queryById(Long id) {
        return basicDataMapper.getById(id);
    }

    @Override
    public List<BasicData> queryList(BasicData basicData) {
        return list(buildQueryWrapper(basicData));
    }

    private LambdaQueryWrapper<BasicData> buildQueryWrapper(BasicData basicData) {
        LambdaQueryWrapper<BasicData> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(basicData.getCode()), BasicData::getCode, basicData.getCode());
        lqw.like(StringUtils.isNotBlank(basicData.getName()), BasicData::getName, basicData.getName());
        lqw.eq(StringUtils.isNotBlank(basicData.getType()), BasicData::getType, basicData.getType());
        return lqw;
    }

    @Override
    public Boolean insertBy(BasicData basicData) {
        validEntityBeforeSave(basicData);
        return save(basicData);
    }

    @Override
    public Boolean updateBy(BasicData basicData) {
        validEntityBeforeUpdate(basicData);
        return updateById(basicData);
    }

    /**
     * 保存前的数据校验
     *
     * @param basicData 实体类数据
     */
    private void validEntityBeforeSave(BasicData basicData) {
        BasicData basicDataEntity = queryBy(basicData);
        if (null != basicDataEntity) {
            throw new CustomException("数据编码[" + basicData.getCode() + "]已存在");
        }
    }

    /**
     * 修改前的数据校验
     *
     * @param basicData 实体类数据
     */
    private void validEntityBeforeUpdate(BasicData basicData) {
        BasicData basicDataEntity = queryBy(basicData);
        if (null != basicDataEntity) {
            throw new CustomException("数据编码[" + basicData.getCode() + "]已存在");
        }
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
        }
        return removeByIds(ids);
    }

    @Override
    public BasicData queryBy(BasicData basicData) {
        LambdaQueryWrapper<BasicData> lqw = Wrappers.lambdaQuery();
        lqw.eq(BasicData::getCode, basicData.getCode()).eq(BasicData::getType, basicData.getType());
        return this.getOne(lqw);
    }
}
