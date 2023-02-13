package com.wjyoption.system.mapper;

import com.wjyoption.system.domain.WpFinancialDetail;
import com.wjyoption.system.vo.resp.FinancialDetailResp;

import java.util.List;

/**
 * 理财收益详情Mapper接口
 * 
 * @author wjyoption
 * @date 2021-06-07
 */
public interface WpFinancialDetailMapper 
{
    /**
     * 查询理财收益详情
     * 
     * @param id 理财收益详情ID
     * @return 理财收益详情
     */
    public WpFinancialDetail selectWpFinancialDetailById(Integer id);

    /**
     * 查询理财收益详情列表
     * 
     * @param wpFinancialDetail 理财收益详情
     * @return 理财收益详情集合
     */
    public List<WpFinancialDetail> selectWpFinancialDetailList(WpFinancialDetail wpFinancialDetail);

    /**
     * 新增理财收益详情
     * 
     * @param wpFinancialDetail 理财收益详情
     * @return 结果
     */
    public int insertWpFinancialDetail(WpFinancialDetail wpFinancialDetail);

    /**
     * 修改理财收益详情
     * 
     * @param wpFinancialDetail 理财收益详情
     * @return 结果
     */
    public int updateWpFinancialDetail(WpFinancialDetail wpFinancialDetail);

    /**
     * 删除理财收益详情
     * 
     * @param id 理财收益详情ID
     * @return 结果
     */
    public int deleteWpFinancialDetailById(Integer id);
    /**
     * 获取上次收益
     * @param refid
     * @return
     */
    public Double selectLastProfit(Integer refid);

    /**
     * 批量删除理财收益详情
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpFinancialDetailByIds(String[] ids);
    
    
    
    
    
    
    /********************************************API******************************************/
    
    
    
    
    
    /**
     * 获取详情列表
     * @param refid
     * @return
     */
    public List<FinancialDetailResp> selectFinancialDetailList(Integer refid);
}
