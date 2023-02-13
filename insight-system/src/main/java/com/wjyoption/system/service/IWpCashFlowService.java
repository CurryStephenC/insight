package com.wjyoption.system.service;

import com.wjyoption.system.domain.WpCashFlow;
import com.wjyoption.system.vo.resp.CashFlowResp;

import java.math.BigDecimal;
import java.util.List;

/**
 * 流水Service接口
 * 
 * @author wjyoption
 * @date 2021-06-04
 */
public interface IWpCashFlowService 
{
    /**
     * 查询流水
     * 
     * @param id 流水ID
     * @return 流水
     */
    public WpCashFlow selectWpCashFlowById(Integer id);

    /**
     * 查询流水列表
     * 
     * @param wpCashFlow 流水
     * @return 流水集合
     */
    public List<WpCashFlow> selectWpCashFlowList(WpCashFlow wpCashFlow);

    /**
     * 新增流水
     * 
     * @param wpCashFlow 流水
     * @return 结果
     */
    public int insertWpCashFlow(WpCashFlow wpCashFlow);
    /**
     * 添加
     * @param uid		用户ID
     * @param typeid	类型ID
     * @param money		变动金额
     * @param content	内容
     * @param usermoney	此刻余额
     * @return
     */
    public int insertWpCashFlow(Integer uid,Integer typeid,String money,String content,BigDecimal usermoney);

    /**
     * 修改流水
     * 
     * @param wpCashFlow 流水
     * @return 结果
     */
    public int updateWpCashFlow(WpCashFlow wpCashFlow);

    /**
     * 批量删除流水
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpCashFlowByIds(String ids);

    /**
     * 删除流水信息
     * 
     * @param id 流水ID
     * @return 结果
     */
    public int deleteWpCashFlowById(Integer id);
    
    
    
    
    
    
    
    
    
    
    
    public List<CashFlowResp> selectCashFlowList(CashFlowResp wpCashFlow);
    
    /**
     * 查询money
     * @param flow
     * @return
     */
    public String selectTotalMoney(WpCashFlow flow);
}
