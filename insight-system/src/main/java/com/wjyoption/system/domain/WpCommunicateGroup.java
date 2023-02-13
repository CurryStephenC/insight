package com.wjyoption.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;

/**
 * 交流推广群对象 wp_communicate_group
 * 
 * @author wjyoption
 * @date 2021-07-28
 */
public class WpCommunicateGroup extends BaseEntity
{

	private static final long serialVersionUID = 6834450461678643082L;

	/** 主键 */
    private Long id;

    /** code */
    @Excel(name = "code")
    private String code;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 群组链接 */
    @Excel(name = "群组链接")
    private String groupurl;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setGroupurl(String groupurl) 
    {
        this.groupurl = groupurl;
    }

    public String getGroupurl() 
    {
        return groupurl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("status", getStatus())
            .append("groupurl", getGroupurl())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
