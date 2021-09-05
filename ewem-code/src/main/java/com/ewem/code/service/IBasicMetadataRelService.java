package com.ewem.code.service;

import com.ewem.code.domain.BasicMetadataRel;
import com.ewem.common.core.mybatisplus.IServicePlus;


import java.util.Collection;
import java.util.List;

/**
 * 基础-元数据关联Service接口
 *
 * @author ewem
 * @date 2021-08-29
 */
public interface IBasicMetadataRelService extends IServicePlus<BasicMetadataRel> {

	/**
	 * 查询单个
	 * @return
	 */
	BasicMetadataRel queryById(Long id);


	/**
	 * 查询列表
	 */
	List<BasicMetadataRel> queryList(BasicMetadataRel basicMetadataRel);

	/**
	 * 根据新增业务对象插入基础-元数据关联
	 * @param basicMetadataRel 基础-元数据关联新增业务对象
	 * @return
	 */
	Boolean insertBy(BasicMetadataRel basicMetadataRel);

	/**
	 * 根据编辑业务对象修改基础-元数据关联
	 * @param basicMetadataRel 基础-元数据关联编辑业务对象
	 * @return
	 */
	Boolean updateBy(BasicMetadataRel basicMetadataRel);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
