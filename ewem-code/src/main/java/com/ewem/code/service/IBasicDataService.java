package com.ewem.code.service;

import com.ewem.code.domain.BasicData;
import com.ewem.common.core.mybatisplus.IServicePlus;


import java.util.Collection;
import java.util.List;

/**
 * 基础数据Service接口
 *
 * @author ewem
 * @date 2021-08-28
 */
public interface IBasicDataService extends IServicePlus<BasicData> {

	/**
	 * 查询单个
	 * @return
	 */
	BasicData queryById(Long id);


	/**
	 * 查询列表
	 */
	List<BasicData> queryList(BasicData basicData);

	/**
	 * 根据新增业务对象插入基础数据
	 * @param basicData 基础数据新增业务对象
	 * @return
	 */
	Boolean insertBy(BasicData basicData);

	/**
	 * 根据编辑业务对象修改基础数据
	 * @param basicData 基础数据编辑业务对象
	 * @return
	 */
	Boolean updateBy(BasicData basicData);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

	/**
	 * 条件查询基础数据
	 * @param basicData
	 * @return
	 */
	BasicData queryBy(BasicData basicData);
}
