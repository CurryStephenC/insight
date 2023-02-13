package com.wjyoption.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;

/**
 * 广告对象 sys_advert
 * 
 * @author hs
 * @date 2019-09-19
 */
public class SysAdvert extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 部门ID */
    private Long deptId;
    
    /** 部门ID(查询时使用，往上查询) **/
    private Long deptIdUp;
    
    @Excel(name = "部门")
    private String deptname;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 图片 */
    @Excel(name = "图片")
    private String picurl;

    /** 详情 */
    @Excel(name = "详情")
    private String detailurl;

    /** 状态 */
    @Excel(name = "状态",readConverterExp = "1=正常,2=禁用")
    private Integer adstatus;

    /** 描述 */
    @Excel(name = "描述")
    private String desction;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setPicurl(String picurl) 
    {
        this.picurl = picurl;
    }

    public String getPicurl() 
    {
        return picurl;
    }
    public void setDetailurl(String detailurl) 
    {
        this.detailurl = detailurl;
    }

    public String getDetailurl() 
    {
        return detailurl;
    }
    public void setAdstatus(Integer adstatus) 
    {
        this.adstatus = adstatus;
    }

    public Integer getAdstatus() 
    {
        return adstatus;
    }
    public void setDesction(String desction) 
    {
        this.desction = desction;
    }

    public String getDesction() 
    {
        return desction;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("deptId", getDeptId())
            .append("title", getTitle())
            .append("picurl", getPicurl())
            .append("detailurl", getDetailurl())
            .append("adstatus", getAdstatus())
            .append("desction", getDesction())
            .append("createTime", getCreateTime())
            .toString();
    }

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public Long getDeptIdUp() {
		return deptIdUp;
	}

	public void setDeptIdUp(Long deptIdUp) {
		this.deptIdUp = deptIdUp;
	}
}
