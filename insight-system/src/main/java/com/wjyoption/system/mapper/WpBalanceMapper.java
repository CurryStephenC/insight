package com.wjyoption.system.mapper;

import com.wjyoption.system.domain.WpBalance;

import java.util.List;
import java.util.Map;

/**
 * 提现手动充值Mapper接口
 * 
 * @author wjyoption
 * @date 2021-06-08
 */
public interface WpBalanceMapper 
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
     * 删除提现手动充值
     * 
     * @param bpid 提现手动充值ID
     * @return 结果
     */
    public int deleteWpBalanceById(Integer bpid);

    /**
     * 批量删除提现手动充值
     * 
     * @param bpids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpBalanceByIds(String[] bpids);

	public WpBalance selectWpBalanceByOrderid(String balance_sn);

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
