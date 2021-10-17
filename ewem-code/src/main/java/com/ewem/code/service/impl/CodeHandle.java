package com.ewem.code.service.impl;

import com.ewem.code.domain.Apply;
import com.ewem.code.domain.Code;
import com.ewem.code.service.IApplyService;
import com.ewem.code.service.ICodeService;
import com.ewem.common.enums.ApplyStatus;
import com.ewem.common.qrcode.impl.SnowflakeClient;
import com.ewem.common.utils.StringUtils;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 码处理任务Service业务层处理
 *
 * @author ewem
 * @date 2021-08-14
 */
@Service
public class CodeHandle {

    @Autowired
    IApplyService applyService;

    @Autowired
    ICodeService codeService;

    @Autowired
    SnowflakeClient snowflakeClient;


    /**
     * 处理码申请
     */
    @Transactional(rollbackFor = Exception.class)
    public void handle() {
        Apply apply = applyService.selectOneByApplyStatus(ApplyStatus.INIT);
        if (StringUtils.isNull(apply)) {
            return;
        }
        apply.setUpdateBy(apply.getCreateBy());
        apply.setApplyStatus(ApplyStatus.EXECUTING.getCode());
        applyService.updateById(apply);
        List<Code> codeList = Lists.newArrayList();
        for (int i = 0; i < apply.getQuantity(); i++) {
            Code code = new Code();
            code.setCode(snowflakeClient.code(apply.getRule(), apply.getLength()));
            code.setBatchId(apply.getBatchId());
            code.setCreateBy(apply.getCreateBy());
            code.setUpdateBy(apply.getCreateBy());
            codeList.add(code);
        }
        apply.setApplyStatus(ApplyStatus.SUCCESS.getCode());
        codeService.saveBatch(codeList);
        applyService.updateById(apply);
    }
}
