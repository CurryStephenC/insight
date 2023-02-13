package com.wjyoption.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wjyoption.system.mapper.WpYieldRateMapper;
import com.wjyoption.system.domain.WpYieldRate;
import com.wjyoption.system.service.IWpYieldRateService;
import com.wjyoption.common.core.text.Convert;

/**
 * 收益利率Service业务层处理
 * 
 * @author wjyoption
 * @date 2021-06-06
 */
@Service
public class WpYieldRateServiceImpl implements IWpYieldRateService 
{
    @Autowired
    private WpYieldRateMapper wpYieldRateMapper;

    /**
     * 查询收益利率
     * 
     * @param id 收益利率ID
     * @return 收益利率
     */
    @Override
    public WpYieldRate selectWpYieldRateById(Integer id)
    {
        return wpYieldRateMapper.selectWpYieldRateById(id);
    }

    /**
     * 查询收益利率列表
     * 
     * @param wpYieldRate 收益利率
     * @return 收益利率
     */
    @Override
    public List<WpYieldRate> selectWpYieldRateList(WpYieldRate wpYieldRate)
    {
        return wpYieldRateMapper.selectWpYieldRateList(wpYieldRate);
    }

    /**
     * 新增收益利率
     * 
     * @param wpYieldRate 收益利率
     * @return 结果
     */
    @Override
    public int insertWpYieldRate(WpYieldRate wpYieldRate)
    {
        return wpYieldRateMapper.insertWpYieldRate(wpYieldRate);
    }

    /**
     * 修改收益利率
     * 
     * @param wpYieldRate 收益利率
     * @return 结果
     */
    @Override
    public int updateWpYieldRate(WpYieldRate wpYieldRate)
    {
        return wpYieldRateMapper.updateWpYieldRate(wpYieldRate);
    }

    /**
     * 删除收益利率对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWpYieldRateByIds(String ids)
    {
        return wpYieldRateMapper.deleteWpYieldRateByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除收益利率信息
     * 
     * @param id 收益利率ID
     * @return 结果
     */
    public int deleteWpYieldRateById(Integer id)
    {
        return wpYieldRateMapper.deleteWpYieldRateById(id);
    }
}
