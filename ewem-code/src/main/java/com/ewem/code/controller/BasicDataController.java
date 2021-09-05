package com.ewem.code.controller;

import com.ewem.code.domain.BasicData;
import com.ewem.code.service.IBasicDataService;
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
 * 基础数据Controller
 *
 * @author ewem
 * @date 2021-08-28
 */
@Validated
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/basicdata/basicData")
public class BasicDataController extends BaseController {

    private final IBasicDataService iBasicDataService;

    /**
     * 查询基础数据列表
     */
    @PreAuthorize("@ss.hasPermi('basicdata:basicData:list')")
    @GetMapping("/list")
    public TableDataInfo list(BasicData basicData) {
        startPage();
        List<BasicData> basicDataList = iBasicDataService.queryList(basicData);
        return getDataTable(basicDataList);
    }

    /**
     * 导出基础数据列表
     */
    @PreAuthorize("@ss.hasPermi('basicdata:basicData:export')")
    @Log(title = "基础数据" , businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasicData basicData) {
        List<BasicData> list = iBasicDataService.queryList(basicData);
        return new ExcelUtil<>(BasicData.class).exportExcel(list, "基础数据");

    }

    /**
     * 获取基础数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('basicdata:basicData:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@NotNull(message = "主键不能为空")
                              @PathVariable("id") Long id) {
        return AjaxResult.success(iBasicDataService.queryById(id));
    }

    /**
     * 新增基础数据
     */
    @PreAuthorize("@ss.hasPermi('basicdata:basicData:add')")
    @Log(title = "基础数据" , businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public AjaxResult add(@Validated @RequestBody BasicData basicData) {
        return toAjax(iBasicDataService.insertBy(basicData) ? 1 : 0);
    }

    /**
     * 修改基础数据
     */
    @PreAuthorize("@ss.hasPermi('basicdata:basicData:edit')")
    @Log(title = "基础数据" , businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public AjaxResult edit(@Validated @RequestBody BasicData basicData) {
        return toAjax(iBasicDataService.updateBy(basicData) ? 1 : 0);
    }

    /**
     * 删除基础数据
     */
    @PreAuthorize("@ss.hasPermi('basicdata:basicData:remove')")
    @Log(title = "基础数据" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@NotEmpty(message = "主键不能为空")
                             @PathVariable Long[] ids) {
        return toAjax(iBasicDataService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
