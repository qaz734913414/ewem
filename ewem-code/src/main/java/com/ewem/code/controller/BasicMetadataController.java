package com.ewem.code.controller;

import com.ewem.code.domain.BasicMetadata;
import com.ewem.code.service.IBasicMetadataService;
import com.ewem.common.annotation.Log;
import com.ewem.common.annotation.RepeatSubmit;
import com.ewem.common.core.controller.BaseController;
import com.ewem.common.core.domain.AjaxResult;
import com.ewem.common.core.page.TableDataInfo;
import com.ewem.common.enums.BusinessType;
import com.ewem.common.utils.poi.ExcelUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 基础元数据Controller
 *
 * @author ewem
 * @date 2021-08-28
 */
@Validated
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/code/metadata")
public class BasicMetadataController extends BaseController {

    private final IBasicMetadataService iBasicMetadataService;

    /**
     * 查询基础元数据列表
     */
    @PreAuthorize("@ss.hasPermi('code:metadata:list')")
    @GetMapping("/list")
    public TableDataInfo list(BasicMetadata basicMetadata) {
        startPage();
        List<BasicMetadata> list = iBasicMetadataService.queryList(basicMetadata);
        return getDataTable(list);
    }

    /**
     * 导出基础元数据列表
     */
    @PreAuthorize("@ss.hasPermi('code:metadata:export')")
    @Log(title = "基础元数据" , businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasicMetadata basicMetadata) {
        List<BasicMetadata> list = iBasicMetadataService.queryList(basicMetadata);
        return new ExcelUtil<>(BasicMetadata.class).exportExcel(list, "基础元数据");

    }

    /**
     * 获取基础元数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('code:metadata:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@NotNull(message = "主键不能为空")
                              @PathVariable("id") Long id) {
        return AjaxResult.success(iBasicMetadataService.queryById(id));
    }

    /**
     * 新增基础元数据
     */
    @PreAuthorize("@ss.hasPermi('code:metadata:add')")
    @Log(title = "基础元数据" , businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public AjaxResult add(@Validated @RequestBody BasicMetadata basicMetadata) {
        return toAjax(iBasicMetadataService.insertBy(basicMetadata) ? 1 : 0);
    }

    /**
     * 修改基础元数据
     */
    @PreAuthorize("@ss.hasPermi('code:metadata:edit')")
    @Log(title = "基础元数据" , businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public AjaxResult edit(@Validated @RequestBody BasicMetadata basicMetadata) {
        return toAjax(iBasicMetadataService.updateBy(basicMetadata) ? 1 : 0);
    }

    /**
     * 删除基础元数据
     */
    @PreAuthorize("@ss.hasPermi('code:metadata:remove')")
    @Log(title = "基础元数据" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@NotEmpty(message = "主键不能为空")
                             @PathVariable Long[] ids) {
        return toAjax(iBasicMetadataService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
