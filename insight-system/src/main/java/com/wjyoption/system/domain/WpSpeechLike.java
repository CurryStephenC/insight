package com.wjyoption.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;

/**
 * 言论模块点赞对象 wp_speech_like
 * 
 * @author wjyoption
 * @date 2021-06-17
 */
public class WpSpeechLike extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Integer id;

    /** 用户 */
    @Excel(name = "用户")
    private Integer uid;

    /** 关联 */
    @Excel(name = "关联")
    private Integer refid;

    /** 类型：1、言论，2、言论评论，3、言论评论回复 */
    @Excel(name = "类型：1、言论，2、言论评论，3、言论评论回复")
    private Integer type;
    
    private Long createtime;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setUid(Integer uid) 
    {
        this.uid = uid;
    }

    public Integer getUid() 
    {
        return uid;
    }
    public void setRefid(Integer refid) 
    {
        this.refid = refid;
    }

    public Integer getRefid() 
    {
        return refid;
    }
    /**
     * 类型：1、言论，2、言论评论，3、言论评论回复
     * @param type
     */
    public void setType(Integer type) 
    {
        this.type = type;
    }

    /**
     * 类型：1、言论，2、言论评论，3、言论评论回复
     * @return
     */
    public Integer getType() 
    {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("uid", getUid())
            .append("refid", getRefid())
            .append("type", getType())
            .append("createtime", getCreatetime())
            .toString();
    }

	public Long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}
}
