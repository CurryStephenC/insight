package com.wjyoption.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;

/**
 * 短信发送记录对象 sys_sms_record
 * 
 * @author hs
 * @date 2020-04-29
 */
public class SysSmsRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Integer id;

    /** 短信模板 */
    @Excel(name = "短信模板")
    private String content;

    /** 电话号码 */
    @Excel(name = "电话号码")
    private String phonenum;

    /** 敏感数据 */
    private String sensitive;

    /** 短信通道 */
    @Excel(name = "短信通道")
    private String channel;

    /** 返回数据 */
    @Excel(name = "返回数据")
    private String resultdata;

    /** 发送状态 */
    @Excel(name = "发送状态")
    private Integer sendstate;

    /** 短信类型 */
    @Excel(name = "短信类型")
    private Integer smstype;
    
    /** 来源 **/
    private String source;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setPhonenum(String phonenum) 
    {
        this.phonenum = phonenum;
    }

    public String getPhonenum() 
    {
        return phonenum;
    }
    /**
     * JSON格式数组
     * @param sensitive
     */
    public void setSensitive(String sensitive) 
    {
        this.sensitive = sensitive;
    }

    public String getSensitive() 
    {
        return sensitive;
    }
    public void setChannel(String channel) 
    {
        this.channel = channel;
    }

    public String getChannel() 
    {
        return channel;
    }
    public void setResultdata(String resultdata) 
    {
        this.resultdata = resultdata;
    }

    public String getResultdata() 
    {
        return resultdata;
    }
    public void setSendstate(Integer sendstate) 
    {
        this.sendstate = sendstate;
    }

    public Integer getSendstate() 
    {
        return sendstate;
    }
    public void setSmstype(Integer smstype) 
    {
        this.smstype = smstype;
    }

    public Integer getSmstype() 
    {
        return smstype;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("content", getContent())
            .append("phonenum", getPhonenum())
            .append("sensitive", getSensitive())
            .append("channel", getChannel())
            .append("resultdata", getResultdata())
            .append("sendstate", getSendstate())
            .append("smstype", getSmstype())
            .append("createTime", getCreateTime())
            .toString();
    }

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}
