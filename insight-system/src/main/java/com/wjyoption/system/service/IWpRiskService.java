package com.wjyoption.system.service;

import com.wjyoption.system.domain.WpRisk;
import java.util.List;

/**
 * 风控Service接口
 * 
 * @author wjyoption
 * @date 2021-07-08
 */
public interface IWpRiskService 
{
    /**
     * 查询风控
     * 
     * @param id 风控ID
     * @return 风控
     */
    public WpRisk selectWpRiskById(Integer id);

    /**
     * 查询风控列表
     * 
     * @param wpRisk 风控
     * @return 风控集合
     */
    public List<WpRisk> selectWpRiskList(WpRisk wpRisk);

    /**
     * 新增风控
     * 
     * @param wpRisk 风控
     * @return 结果
     */
    public int insertWpRisk(WpRisk wpRisk);

    /**
     * 修改风控
     * 
     * @param wpRisk 风控
     * @return 结果
     */
    public int updateWpRisk(WpRisk wpRisk);

    /**
     * 批量删除风控
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpRiskByIds(String ids);

    /**
     * 删除风控信息
     * 
     * @param id 风控ID
     * @return 结果
     */
    public int deleteWpRiskById(Integer id);
}
