package com.wjyoption.system.service;

import com.wjyoption.system.domain.WpPriceLog;
import com.wjyoption.system.vo.resp.PriceLogResp;

import java.math.BigDecimal;
import java.util.List;

/**
 * 资金日志Service接口
 * 
 * @author wjyoption
 * @date 2021-06-05
 */
public interface IWpPriceLogService 
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
     * 添加资金日志
     * @param uid 用户ID
     * @param money 变动金额
     * @param type 1增加2减少
     * @param title 标题
     * @param content 内容
     * @param oid 关联ID
     * @param usermoney 用户此刻余额
     * @return
     */
    public int insertWpPriceLog(Integer uid,String money,Integer type,String title,String content,Integer oid,BigDecimal usermoney);

    /**
     * 修改资金日志
     * 
     * @param wpPriceLog 资金日志
     * @return 结果
     */
    public int updateWpPriceLog(WpPriceLog wpPriceLog);

    /**
     * 批量删除资金日志
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpPriceLogByIds(String ids);

    /**
     * 删除资金日志信息
     * 
     * @param id 资金日志ID
     * @return 结果
     */
    public int deleteWpPriceLogById(Integer id);
    
    
    
    
    
    
    
    
    
    public List<PriceLogResp> selectPriceLogList(WpPriceLog wpPriceLog);
}
