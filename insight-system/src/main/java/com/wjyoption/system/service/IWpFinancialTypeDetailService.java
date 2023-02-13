package com.wjyoption.system.service;

import com.wjyoption.system.domain.WpFinancialTypeDetail;

import java.util.List;

/**
 * 理财类型详情Service接口
 * 
 * @author wjyoption
 * @date 2021-06-04
 */
public interface IWpFinancialTypeDetailService 
{
    /**
     * 查询理财类型详情
     * 
     * @param id 理财类型详情ID
     * @return 理财类型详情
     */
    public WpFinancialTypeDetail selectWpFinancialTypeDetailById(Integer id);
    
    /**
     * 查询理财类型id
     * @param typeid
     * @return
     */
    public WpFinancialTypeDetail selectDetailBytypeId(Integer typeid);

    /**
     * 查询理财类型详情列表
     * 
     * @param wpFinancialTypeDetail 理财类型详情
     * @return 理财类型详情集合
     */
    public List<WpFinancialTypeDetail> selectWpFinancialTypeDetailList(WpFinancialTypeDetail wpFinancialTypeDetail);

    /**
     * 新增理财类型详情
     * 
     * @param wpFinancialTypeDetail 理财类型详情
     * @return 结果
     */
    public int insertWpFinancialTypeDetail(WpFinancialTypeDetail wpFinancialTypeDetail);

    /**
     * 修改理财类型详情
     * 
     * @param wpFinancialTypeDetail 理财类型详情
     * @return 结果
     */
    public int updateWpFinancialTypeDetail(WpFinancialTypeDetail wpFinancialTypeDetail);
}
