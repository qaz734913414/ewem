package com.ewem.code.service.impl;

import com.ewem.code.domain.Apply;
import com.ewem.code.domain.Code;
import com.ewem.code.service.IApplyService;
import com.ewem.code.service.ICodeService;
import com.ewem.common.enums.ApplyStatus;
import com.ewem.common.qrcode.impl.SnowflakeClient;
import com.ewem.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 码处理任务Service业务层处理
 *
 * @author ewem
 * @date 2021-08-14
 */
@Service
public class CodeHandle {

    private static final Logger log = LoggerFactory.getLogger(CodeHandle.class);

    @Autowired
    IApplyService applyService;

    @Autowired
    ICodeService codeService;

    @Autowired
    SnowflakeClient snowflakeClient;


    /**
     * 处理码申请
     */
    public void handle() {
        Apply apply = applyService.selectOneByApplyStatus(ApplyStatus.INIT.getCode());
        if (StringUtils.isNull(apply)) {
            return;
        }
        apply.setApplyStatus(ApplyStatus.EXECUTING.getCode());
        applyService.updateApply(apply);
        for (int i = 0; i < apply.getQuantity(); i++) {
            Code code = new Code();
            code.setCode(snowflakeClient.getCodeUpperCase());
            codeService.insertCode(code);
        }
        apply.setApplyStatus(ApplyStatus.EXECUTING.getCode());
        applyService.updateApply(apply);
    }
}
