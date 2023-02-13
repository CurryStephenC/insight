package com.wjyoption.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;

/**
 * 设备版本信息对象 sys_device_version
 * 
 * @author wjyoption
 * @date 2021-06-11
 */
public class SysDeviceVersion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Integer id;

    /** 设备ID */
    @Excel(name = "设备ID")
    private String deviceid;

    /** 用户id */
    private Integer uid;
    
    @Excel(name = "用户")
    private String utel;

    /** 版本号 */
    @Excel(name = "版本号")
    private String version;

    /** 设备其他信息 */
    @Excel(name = "设备其他信息")
    private String res;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setDeviceid(String deviceid) 
    {
        this.deviceid = deviceid;
    }

    public String getDeviceid() 
    {
        return deviceid;
    }
    public void setUid(Integer uid) 
    {
        this.uid = uid;
    }

    public Integer getUid() 
    {
        return uid;
    }
    public void setVersion(String version) 
    {
        this.version = version;
    }

    public String getVersion() 
    {
        return version;
    }
    public void setRes(String res) 
    {
        this.res = res;
    }

    public String getRes() 
    {
        return res;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("deviceid", getDeviceid())
            .append("uid", getUid())
            .append("version", getVersion())
            .append("res", getRes())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }

	public String getUtel() {
		return utel;
	}

	public void setUtel(String utel) {
		this.utel = utel;
	}
}
