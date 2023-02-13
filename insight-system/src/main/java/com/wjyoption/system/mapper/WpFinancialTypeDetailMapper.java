package com.wjyoption.system.mapper;

import com.wjyoption.system.domain.WpFinancialTypeDetail;
import com.wjyoption.system.vo.resp.FinancialTypeDetailVo;

import java.util.List;

/**
 * 理财类型详情Mapper接口
 * 
 * @author wjyoption
 * @date 2021-06-04
 */
public interface WpFinancialTypeDetailMapper 
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
     *  获取详情
     * @param typeid
     * @return
     */
    public FinancialTypeDetailVo selectDetailVo(Integer typeid);

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
