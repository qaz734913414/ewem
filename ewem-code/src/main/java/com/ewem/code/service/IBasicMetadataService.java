package com.ewem.code.service;

import com.ewem.code.domain.BasicMetadata;
import com.ewem.common.core.mybatisplus.IServicePlus;


import java.util.Collection;
import java.util.List;

/**
 * 基础元数据Service接口
 *
 * @author ewem
 * @date 2021-08-28
 */
public interface IBasicMetadataService extends IServicePlus<BasicMetadata> {

	/**
	 * 查询单个
	 * @return
	 */
	BasicMetadata queryById(Long id);


	/**
	 * 查询列表
	 */
	List<BasicMetadata> queryList(BasicMetadata basicMetadata);

	/**
	 * 根据新增业务对象插入基础元数据
	 * @param basicMetadata 基础元数据新增业务对象
	 * @return
	 */
	Boolean insertBy(BasicMetadata basicMetadata);

	/**
	 * 根据编辑业务对象修改基础元数据
	 * @param basicMetadata 基础元数据编辑业务对象
	 * @return
	 */
	Boolean updateBy(BasicMetadata basicMetadata);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);


	/**
	 * 条件查询
	 * @param basicMetadata
	 * @return
	 */
	BasicMetadata queryBy(BasicMetadata basicMetadata);
}
