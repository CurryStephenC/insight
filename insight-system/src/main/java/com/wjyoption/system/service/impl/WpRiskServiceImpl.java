package com.wjyoption.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wjyoption.system.mapper.WpRiskMapper;
import com.wjyoption.system.domain.WpRisk;
import com.wjyoption.system.service.IWpRiskService;
import com.wjyoption.common.core.text.Convert;

/**
 * 风控Service业务层处理
 * 
 * @author wjyoption
 * @date 2021-07-08
 */
@Service
public class WpRiskServiceImpl implements IWpRiskService 
{
    @Autowired
    private WpRiskMapper wpRiskMapper;

    /**
     * 查询风控
     * 
     * @param id 风控ID
     * @return 风控
     */
    @Override
    public WpRisk selectWpRiskById(Integer id)
    {
        return wpRiskMapper.selectWpRiskById(id);
    }

    /**
     * 查询风控列表
     * 
     * @param wpRisk 风控
     * @return 风控
     */
    @Override
    public List<WpRisk> selectWpRiskList(WpRisk wpRisk)
    {
        return wpRiskMapper.selectWpRiskList(wpRisk);
    }

    /**
     * 新增风控
     * 
     * @param wpRisk 风控
     * @return 结果
     */
    @Override
    public int insertWpRisk(WpRisk wpRisk)
    {
        return wpRiskMapper.insertWpRisk(wpRisk);
    }

    /**
     * 修改风控
     * 
     * @param wpRisk 风控
     * @return 结果
     */
    @Override
    public int updateWpRisk(WpRisk wpRisk)
    {
        return wpRiskMapper.updateWpRisk(wpRisk);
    }

    /**
     * 删除风控对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWpRiskByIds(String ids)
    {
        return wpRiskMapper.deleteWpRiskByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除风控信息
     * 
     * @param id 风控ID
     * @return 结果
     */
    public int deleteWpRiskById(Integer id)
    {
        return wpRiskMapper.deleteWpRiskById(id);
    }
}
