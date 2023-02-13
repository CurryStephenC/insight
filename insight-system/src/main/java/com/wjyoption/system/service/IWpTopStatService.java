package com.wjyoption.system.service;

import com.wjyoption.system.domain.WpTopStat;
import java.util.List;

/**
 * 销售统计Service接口
 * 
 * @author wjyoption
 * @date 2021-09-15
 */
public interface IWpTopStatService 
{
    /**
     * 查询销售统计
     * 
     * @param id 销售统计ID
     * @return 销售统计
     */
    public WpTopStat selectWpTopStatById(Integer id);

    /**
     * 查询销售统计列表
     * 
     * @param wpTopStat 销售统计
     * @return 销售统计集合
     */
    public List<WpTopStat> selectWpTopStatList(WpTopStat wpTopStat);

    /**
     * 新增销售统计
     * 
     * @param wpTopStat 销售统计
     * @return 结果
     */
    public int insertWpTopStat(WpTopStat wpTopStat);

    /**
     * 修改销售统计
     * 
     * @param wpTopStat 销售统计
     * @return 结果
     */
    public int updateWpTopStat(WpTopStat wpTopStat);

    /**
     * 批量删除销售统计
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpTopStatByIds(String ids);

    /**
     * 删除销售统计信息
     * 
     * @param id 销售统计ID
     * @return 结果
     */
    public int deleteWpTopStatById(Integer id);
    
    /**
     * 获取统计列表
     * @param wpTopStat
     * @return
     */
    public List<WpTopStat> selectTotalList(WpTopStat wpTopStat);

	public void batchAdd(List<WpTopStat> topList);
}
