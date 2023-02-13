package com.wjyoption.system.service;

import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.core.domain.Response;
import com.wjyoption.common.vo.CashNotifyVo;
import com.wjyoption.system.domain.WpBalance;

import java.util.List;
import java.util.Map;

/**
 * 提现手动充值Service接口
 * 
 * @author wjyoption
 * @date 2021-06-08
 */
public interface IWpBalanceService 
{
    /**
     * 查询提现手动充值
     * 
     * @param bpid 提现手动充值ID
     * @return 提现手动充值
     */
    public WpBalance selectWpBalanceById(Integer bpid);

    /**
     * 查询提现手动充值列表
     * 
     * @param wpBalance 提现手动充值
     * @return 提现手动充值集合
     */
    public List<WpBalance> selectWpBalanceList(WpBalance wpBalance);

    /**
     * 新增提现手动充值
     * 
     * @param wpBalance 提现手动充值
     * @return 结果
     */
    public int insertWpBalance(WpBalance wpBalance);

    /**
     * 修改提现手动充值
     * 
     * @param wpBalance 提现手动充值
     * @return 结果
     */
    public int updateWpBalance(WpBalance wpBalance);

    /**
     * 批量删除提现手动充值
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpBalanceByIds(String ids);

    /**
     * 删除提现手动充值信息
     * 
     * @param bpid 提现手动充值ID
     * @return 结果
     */
    public int deleteWpBalanceById(Integer bpid);

	public void withdraw(String userremark, Integer price,String withdrawpsd,String banktype,Response<String> response);

	/**
	 * 提交打款
	 * @param vo
	 * @return
	 */
	public AjaxResult submit(WpBalance vo);

	/**
	 * 回调
	 * @param vo
	 * @param response
	 */
	public void cashNotify(CashNotifyVo vo, Response<String> response);

	/**
	 * 获取统计数据
	 * @param wpBalance
	 * @return
	 */
	public Map<String, String> selectBalanceTotal(WpBalance wpBalance);

	/**
	 * 获取指定日期统计
	 * @param wpBalance
	 * @return
	 */
	public Map<String, String> selectDailyTotal(WpBalance wpBalance);
}
