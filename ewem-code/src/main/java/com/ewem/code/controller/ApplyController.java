package com.ewem.code.controller;

import com.ewem.code.domain.Apply;
import com.ewem.code.service.IApplyService;
import com.ewem.common.annotation.Log;
import com.ewem.common.core.controller.BaseController;
import com.ewem.common.core.domain.AjaxResult;
import com.ewem.common.core.page.TableDataInfo;
import com.ewem.common.enums.BusinessType;
import com.ewem.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 码申请Controller
 *
 * @author ewem
 * @date 2021-08-08
 */
@RestController
@RequestMapping("/code/apply")
public class ApplyController extends BaseController {
    @Autowired
    private IApplyService applyService;

    /**
     * 查询码申请列表
     */
    @PreAuthorize("@ss.hasPermi('code:apply:list')")
    @GetMapping("/list")
    public TableDataInfo list(Apply apply) {
        startPage();
        List<Apply> list = applyService.selectApplyList(apply);
        return getDataTable(list);
    }

    /**
     * 导出码申请列表
     */
    @PreAuthorize("@ss.hasPermi('code:apply:export')")
    @Log(title = "码申请", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Apply apply) {
        List<Apply> list = applyService.selectApplyList(apply);
        ExcelUtil<Apply> util = new ExcelUtil<Apply>(Apply.class);
        return util.exportExcel(list, "码申请数据");
    }

    /**
     * 获取码申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('code:apply:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(applyService.selectApplyById(id));
    }

    /**
     * 新增码申请
     */
    @PreAuthorize("@ss.hasPermi('code:apply:add')")
    @Log(title = "码申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Apply apply) {
        return toAjax(applyService.insertApply(apply));
    }

    /**
     * 修改码申请
     */
    @PreAuthorize("@ss.hasPermi('code:apply:edit')")
    @Log(title = "码申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Apply apply) {
        return toAjax(applyService.updateApply(apply));
    }

    /**
     * 删除码申请
     */
    @PreAuthorize("@ss.hasPermi('code:apply:remove')")
    @Log(title = "码申请", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(applyService.deleteApplyByIds(ids));
    }
}
