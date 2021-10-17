package com.ewem.code.controller;

import com.ewem.code.service.ITraceService;
import com.ewem.common.core.controller.BaseController;
import com.ewem.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 溯源
 *
 * @author ewem
 * @date 2021-08-14
 */
@RestController
@RequestMapping("/trace")
public class TraceController extends BaseController {
    @Autowired
    private ITraceService traceService;


    /**
     * 查询溯源
     */
    @GetMapping("/{code}")
    public AjaxResult list(@PathVariable("code") String code) {
        return traceService.trace(code);
    }

}
