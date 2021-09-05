package com.ewem.code.domain;

import com.ewem.common.annotation.Excel;
import com.ewem.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 码申请对象 ewem_apply
 *
 * @author ewem
 * @date 2021-08-08
 */
public class Apply extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 码申请数量
     */
    @Excel(name = "码申请数量")
    private Long quantity;

    /**
     * 申请名称
     */
    @Excel(name = "申请名称")
    private String name;

    /**
     * 码类型
     */
    @Excel(name = "码类型")
    private String type;

    /**
     * 生成规则
     */
    @Excel(name = "生成规则")
    private String rule;

    /**
     * 码长度
     */
    @Excel(name = "码长度")
    private Integer length;

    /**
     * 生成状态
     */
    @Excel(name = "生成状态")
    private String applyStatus;

    /**
     * 状态（0正常 1停用）
     */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getRule() {
        return rule;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getLength() {
        return length;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("quantity", getQuantity())
                .append("name", getName())
                .append("type", getType())
                .append("rule", getRule())
                .append("length", getLength())
                .append("applyStatus", getApplyStatus())
                .append("status", getStatus())
                .append("delFlag", getDelFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
