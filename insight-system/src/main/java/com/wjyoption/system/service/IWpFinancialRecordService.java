package com.wjyoption.system.service;

import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.core.domain.Response;
import com.wjyoption.system.domain.WpFinancialRecord;
import com.wjyoption.system.vo.resp.FinancialRecordResp;

import java.util.List;

/**
 * 理财记录Service接口
 * 
 * @author wjyoption
 * @date 2021-06-05
 */
public interface IWpFinancialRecordService 
{
    /**
     * 查询理财记录
     * 
     * @param id 理财记录ID
     * @return 理财记录
     */
    public WpFinancialRecord selectWpFinancialRecordById(Integer id);

    /**
     * 查询理财记录列表
     * 
     * @param wpFinancialRecord 理财记录
     * @return 理财记录集合
     */
    public List<WpFinancialRecord> selectWpFinancialRecordList(WpFinancialRecord wpFinancialRecord);

    /**
     * 新增理财记录
     * 
     * @param wpFinancialRecord 理财记录
     * @return 结果
     */
    public int insertWpFinancialRecord(WpFinancialRecord wpFinancialRecord);

    /**
     * 修改理财记录
     * 
     * @param wpFinancialRecord 理财记录
     * @return 结果
     */
    public int updateWpFinancialRecord(WpFinancialRecord wpFinancialRecord);

    /**
     * 批量删除理财记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpFinancialRecordByIds(String ids);

    /**
     * 删除理财记录信息
     * 
     * @param id 理财记录ID
     * @return 结果
     */
    public int deleteWpFinancialRecordById(Integer id);

    
    
    
    
    /*************************api*****************************/
    
    
    /**
     * 购买理财产品
     * @param typeid
     * @param money
     * @param response
     */
	public void buy(Integer typeid, Integer money,Integer comenewtask, Response<Object> response);
	
	
	/**
	 * 获取理财记录
	 * @param wpFinancialRecord
	 * @return
	 */
	public List<FinancialRecordResp> selectFinancialRecordList(WpFinancialRecord wpFinancialRecord);

	/**
	 * 赎回理财
	 * @param id
	 * @param response
	 */
	public void redemption(Integer id, Response<Object> response);

	/**
	 * 手动结束理财
	 * @param id
	 * @param response
	 */
	public void overBySelf(Integer id, Response<Object> response);

	/**
	 * 购买体验产品
	 * @param response
	 */
	public void freebuy(Integer typeid,Response<Object> response);

	/**
	 * 结束订单
	 * @param id
	 * @return
	 */
	public AjaxResult over(Integer id);
	
	public void financialOver(WpFinancialRecord record);

	/**
	 * 查询满足ib身份的记录
	 * @param wpFinancialRecord
	 */
	public List<WpFinancialRecord> selectIbRecordList(WpFinancialRecord wpFinancialRecord);

	/**
	 * 定时任务处理理财信息
	 * @return
	 */
	public int updatefinancial();

	/**
	 * 更新收益
	 * @return
	 */
	public int updateProfit();
	/**
	 * 更新指定日期收益
	 * @param date yyyyMMdd
	 * @return
	 */
	public int updateProfit(int date);

	public AjaxResult createdata(WpFinancialRecord wpFinancialRecord);
}
