package com.wjyoption.common.core.page;

import org.apache.commons.lang3.StringUtils;

import com.wjyoption.common.constant.Constants;
import com.wjyoption.common.utils.ServletUtils;

/**
 * 表格数据处理
 * 
 * @author hs
 */
public class TableSupport
{
    /**
     * 封装分页对象
     */
    public static PageDomain getPageDomain()
    {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(ServletUtils.getParameterToInt(Constants.PAGE_NUM));
        pageDomain.setPageSize(ServletUtils.getParameterToInt(Constants.PAGE_SIZE));
        pageDomain.setOrderByColumn(ServletUtils.getParameter(Constants.ORDER_BY_COLUMN));
        pageDomain.setIsAsc(ServletUtils.getParameter(Constants.IS_ASC));
        return pageDomain;
    }
    
    /**
     * 封装分页对象
     */
    public static PageDomain getPageDomain2()
    {
    	PageDomain pageDomain = new PageDomain();
    	pageDomain.setPageNum(ServletUtils.getParameterToInt(Constants.PAGE_NUM));
    	pageDomain.setPageSize(ServletUtils.getParameterToInt(Constants.PAGE_SIZE));
    	pageDomain.setOrderByColumn(ServletUtils.getParameter(Constants.ORDER_BY_COLUMN));
    	if(StringUtils.isNotBlank(pageDomain.getOrderByColumn()) && !StringUtils.startsWith(pageDomain.getOrderByColumn(), "t.")){
    		pageDomain.setOrderByColumn("t."+pageDomain.getOrderByColumn());
    	}
    	pageDomain.setIsAsc(ServletUtils.getParameter(Constants.IS_ASC));
    	return pageDomain;
    }

    public static PageDomain buildPageRequest()
    {
        return getPageDomain();
    }
    
    public static PageDomain buildPageRequestApi()
    {
    	return getPageDomain2();
    }
}
