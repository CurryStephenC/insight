package com.wjyoption.system.domain;

import java.math.BigDecimal;

import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;

/**
 * 产品列表对象 wp_productinfo
 * 
 * @author wjyoption
 * @date 2021-06-19
 */
public class WpProductinfo extends BaseEntity
{

	private static final long serialVersionUID = -4612207593139315926L;

	/** 主键ID */
    private Integer pid;

    /** 标题 */
    @Excel(name = "标题")
    private String ptitle;

    /** 产品分类id */
    @Excel(name = "产品分类id")
    private Long cid;

    /** 开市方案id */
    @Excel(name = "开市方案id")
    private Long otid;

    /** 开市 */
    @Excel(name = "开市",readConverterExp="0=休市,1=开市")
    private Integer isopen;

    /** 波动最小值 */
    @Excel(name = "波动最小值")
    private String pointLow;

    /** 波动最大值 */
    @Excel(name = "波动最大值")
    private String pointTop;

    /** 随机波动范围 */
    @Excel(name = "随机波动范围")
    private String rands;

    /** 备注 */
    @Excel(name = "备注")
    private String content;

    /** 添加时间 */
    @Excel(name = "添加时间")
    private Long time;

    /** 0 */
    @Excel(name = "0")
    private Integer isdelete;

    /** 产品代码 */
    @Excel(name = "产品代码")
    private String procode;

    /** 除汇率以外的算法 */
    @Excel(name = "除汇率以外的算法")
    private BigDecimal addData;

    /** 时间玩法间隔 */
    @Excel(name = "时间玩法间隔")
    private String protime;

    /** 点位玩法间隔 */
    @Excel(name = "点位玩法间隔")
    private String propoint;

    /** 盈亏比例 */
    @Excel(name = "盈亏比例")
    private String proscale;

    /** 排序 */
    @Excel(name = "排序")
    private Long proorder;
    private String icon;
    
    /**
     * 产品开市时间
     * 00:30~18:00|18:30~23:00-18:30~23:00
     */
    private String opentime;
    private String opentime1;
    private String opentime2;
    private String opentime3;
    private String opentime4;
    private String opentime5;
    private String opentime6;
    private String opentime7;

    public void setPid(Integer pid) 
    {
        this.pid = pid;
    }

    public Integer getPid() 
    {
        return pid;
    }
    public void setPtitle(String ptitle) 
    {
        this.ptitle = ptitle;
    }

    public String getPtitle() 
    {
        return ptitle;
    }
    public void setCid(Long cid) 
    {
        this.cid = cid;
    }

    public Long getCid() 
    {
        return cid;
    }
    public void setOtid(Long otid) 
    {
        this.otid = otid;
    }

    public Long getOtid() 
    {
        return otid;
    }
    public void setIsopen(Integer isopen) 
    {
        this.isopen = isopen;
    }

    public Integer getIsopen() 
    {
        return isopen;
    }
    public void setPointLow(String pointLow) 
    {
        this.pointLow = pointLow;
    }

    public String getPointLow() 
    {
        return pointLow;
    }
    public void setPointTop(String pointTop) 
    {
        this.pointTop = pointTop;
    }

    public String getPointTop() 
    {
        return pointTop;
    }
    public void setRands(String rands) 
    {
        this.rands = rands;
    }

    public String getRands() 
    {
        return rands;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setTime(Long time) 
    {
        this.time = time;
    }

    public Long getTime() 
    {
        return time;
    }
    public void setIsdelete(Integer isdelete) 
    {
        this.isdelete = isdelete;
    }

    public Integer getIsdelete() 
    {
        return isdelete;
    }
    public void setProcode(String procode) 
    {
        this.procode = procode;
    }

    public String getProcode() 
    {
        return procode;
    }
    public void setAddData(BigDecimal addData) 
    {
        this.addData = addData;
    }

    public BigDecimal getAddData() 
    {
        return addData;
    }
    public void setProtime(String protime) 
    {
        this.protime = protime;
    }

    public String getProtime() 
    {
        return protime;
    }
    public void setPropoint(String propoint) 
    {
        this.propoint = propoint;
    }

    public String getPropoint() 
    {
        return propoint;
    }
    public void setProscale(String proscale) 
    {
        this.proscale = proscale;
    }

    public String getProscale() 
    {
        return proscale;
    }
    public void setProorder(Long proorder) 
    {
        this.proorder = proorder;
    }

    public Long getProorder() 
    {
        return proorder;
    }

	public String getOpentime() {
		return opentime;
	}

	public String getOpentime1() {
		if(opentime1 != null && opentime1 != ""){
			return opentime1;
		}
		if(opentime != null && opentime != ""){
			opentime1 = opentime.split("-",7)[0];
		}
		return opentime1;
	}

	public String getOpentime2() {
		if(opentime2 != null && opentime2 != ""){
			return opentime2;
		}
		if(opentime != null && opentime != "" && opentime.split("-").length > 1){
			opentime2 = opentime.split("-",7)[1];
		}
		return opentime2;
	}

	public String getOpentime3() {
		if(opentime3 != null && opentime3 != ""){
			return opentime3;
		}
		if(opentime != null && opentime != "" && opentime.split("-").length > 2){
			opentime3 = opentime.split("-",7)[2];
		}
		return opentime3;
	}

	public String getOpentime4() {
		if(opentime4 != null && opentime4 != ""){
			return opentime4;
		}
		if(opentime != null && opentime != "" && opentime.split("-").length > 3){
			opentime4 = opentime.split("-",7)[3];
		}
		return opentime4;
	}

	public String getOpentime5() {
		if(opentime5 != null && opentime5 != ""){
			return opentime5;
		}
		if(opentime != null && opentime != "" && opentime.split("-").length > 4){
			opentime5 = opentime.split("-",7)[4];
		}
		return opentime5;
	}

	public String getOpentime6() {
		if(opentime6 != null && opentime6 != ""){
			return opentime6;
		}
		if(opentime != null && opentime != "" && opentime.split("-").length > 5){
			opentime6 = opentime.split("-",7)[5];
		}
		return opentime6;
	}

	public String getOpentime7() {
		if(opentime7 != null && opentime7 != ""){
			return opentime7;
		}
		if(opentime != null && opentime != "" && opentime.split("-").length > 6){
			opentime7 = opentime.split("-",7)[6];
		}
		return opentime7;
	}

	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}

	public void setOpentime1(String opentime1) {
		this.opentime1 = opentime1;
	}

	public void setOpentime2(String opentime2) {
		this.opentime2 = opentime2;
	}

	public void setOpentime3(String opentime3) {
		this.opentime3 = opentime3;
	}

	public void setOpentime4(String opentime4) {
		this.opentime4 = opentime4;
	}

	public void setOpentime5(String opentime5) {
		this.opentime5 = opentime5;
	}

	public void setOpentime6(String opentime6) {
		this.opentime6 = opentime6;
	}

	public void setOpentime7(String opentime7) {
		this.opentime7 = opentime7;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
