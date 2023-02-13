package com.wjyoption.system.service;

import com.wjyoption.system.domain.WpYieldRate;
import java.util.List;

/**
 * 收益利率Service接口
 * 
 * @author wjyoption
 * @date 2021-06-06
 */
public interface IWpYieldRateService 
{
    /**
     * 查询收益利率
     * 
     * @param id 收益利率ID
     * @return 收益利率
     */
    public WpYieldRate selectWpYieldRateById(Integer id);

    /**
     * 查询收益利率列表
     * 
     * @param wpYieldRate 收益利率
     * @return 收益利率集合
     */
    public List<WpYieldRate> selectWpYieldRateList(WpYieldRate wpYieldRate);

    /**
     * 新增收益利率
     * 
     * @param wpYieldRate 收益利率
     * @return 结果
     */
    public int insertWpYieldRate(WpYieldRate wpYieldRate);

    /**
     * 修改收益利率
     * 
     * @param wpYieldRate 收益利率
     * @return 结果
     */
    public int updateWpYieldRate(WpYieldRate wpYieldRate);

    /**
     * 批量删除收益利率
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpYieldRateByIds(String ids);

    /**
     * 删除收益利率信息
     * 
     * @param id 收益利率ID
     * @return 结果
     */
    public int deleteWpYieldRateById(Integer id);
}
