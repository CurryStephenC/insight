package com.wjyoption.system.mapper;

import com.wjyoption.system.domain.WpCashFlow;
import com.wjyoption.system.vo.resp.CashFlowResp;

import java.util.List;

/**
 * 流水Mapper接口
 * 
 * @author wjyoption
 * @date 2021-06-04
 */
public interface WpCashFlowMapper 
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
     * 修改流水
     * 
     * @param wpCashFlow 流水
     * @return 结果
     */
    public int updateWpCashFlow(WpCashFlow wpCashFlow);

    /**
     * 删除流水
     * 
     * @param id 流水ID
     * @return 结果
     */
    public int deleteWpCashFlowById(Integer id);

    /**
     * 批量删除流水
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpCashFlowByIds(String[] ids);
    
    
    
    
    
    
    
    
    
    /********************************API**********************************/
    
    
    
    
    
    public List<CashFlowResp> selectCashFlowList(CashFlowResp wpCashFlow);
    
    /**
     * 查询money
     * @param flow
     * @return
     */
    public String selectTotalMoney(WpCashFlow flow);
}
