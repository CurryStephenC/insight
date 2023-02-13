package com.wjyoption.system.mapper;

import com.wjyoption.system.domain.WpFinancialBuy;
import com.wjyoption.system.vo.resp.FinancialBuyResp;

import java.util.List;

/**
 * 老师购买记录Mapper接口
 * 
 * @author wjyoption
 * @date 2021-06-07
 */
public interface WpFinancialBuyMapper 
{
    /**
     * 查询老师购买记录
     * 
     * @param id 老师购买记录ID
     * @return 老师购买记录
     */
    public WpFinancialBuy selectWpFinancialBuyById(Integer id);

    /**
     * 查询老师购买记录列表
     * 
     * @param wpFinancialBuy 老师购买记录
     * @return 老师购买记录集合
     */
    public List<WpFinancialBuy> selectWpFinancialBuyList(WpFinancialBuy wpFinancialBuy);

    /**
     * 新增老师购买记录
     * 
     * @param wpFinancialBuy 老师购买记录
     * @return 结果
     */
    public int insertWpFinancialBuy(WpFinancialBuy wpFinancialBuy);

    /**
     * 修改老师购买记录
     * 
     * @param wpFinancialBuy 老师购买记录
     * @return 结果
     */
    public int updateWpFinancialBuy(WpFinancialBuy wpFinancialBuy);

    /**
     * 删除老师购买记录
     * 
     * @param id 老师购买记录ID
     * @return 结果
     */
    public int deleteWpFinancialBuyById(Integer id);

    /**
     * 批量删除老师购买记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpFinancialBuyByIds(String[] ids);
    
    
    
    
    
    /******************************************API****************************************************/
    
    public List<FinancialBuyResp> selectFinancialBuyList(Integer detailid);
}
