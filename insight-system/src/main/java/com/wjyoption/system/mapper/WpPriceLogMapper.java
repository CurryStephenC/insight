package com.wjyoption.system.mapper;

import com.wjyoption.system.domain.WpPriceLog;
import com.wjyoption.system.vo.resp.PriceLogResp;

import java.util.List;

/**
 * 资金日志Mapper接口
 * 
 * @author wjyoption
 * @date 2021-06-05
 */
public interface WpPriceLogMapper 
{
    /**
     * 查询资金日志
     * 
     * @param id 资金日志ID
     * @return 资金日志
     */
    public WpPriceLog selectWpPriceLogById(Integer id);

    /**
     * 查询资金日志列表
     * 
     * @param wpPriceLog 资金日志
     * @return 资金日志集合
     */
    public List<WpPriceLog> selectWpPriceLogList(WpPriceLog wpPriceLog);

    /**
     * 新增资金日志
     * 
     * @param wpPriceLog 资金日志
     * @return 结果
     */
    public int insertWpPriceLog(WpPriceLog wpPriceLog);

    /**
     * 修改资金日志
     * 
     * @param wpPriceLog 资金日志
     * @return 结果
     */
    public int updateWpPriceLog(WpPriceLog wpPriceLog);

    /**
     * 删除资金日志
     * 
     * @param id 资金日志ID
     * @return 结果
     */
    public int deleteWpPriceLogById(Integer id);

    /**
     * 批量删除资金日志
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpPriceLogByIds(String[] ids);
    
    
    
    
    
    
    
    
    
    
    
    public List<PriceLogResp> selectPriceLogList(WpPriceLog wpPriceLog);
}
