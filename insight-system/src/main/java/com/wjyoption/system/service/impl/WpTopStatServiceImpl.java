package com.wjyoption.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wjyoption.system.mapper.WpTopStatMapper;
import com.wjyoption.system.domain.WpTopStat;
import com.wjyoption.system.service.IWpTopStatService;
import com.wjyoption.common.core.text.Convert;

/**
 * 销售统计Service业务层处理
 * 
 * @author wjyoption
 * @date 2021-09-15
 */
@Service
public class WpTopStatServiceImpl implements IWpTopStatService 
{
    @Autowired
    private WpTopStatMapper wpTopStatMapper;

    /**
     * 查询销售统计
     * 
     * @param id 销售统计ID
     * @return 销售统计
     */
    @Override
    public WpTopStat selectWpTopStatById(Integer id)
    {
        return wpTopStatMapper.selectWpTopStatById(id);
    }

    /**
     * 查询销售统计列表
     * 
     * @param wpTopStat 销售统计
     * @return 销售统计
     */
    @Override
    public List<WpTopStat> selectWpTopStatList(WpTopStat wpTopStat)
    {
        return wpTopStatMapper.selectWpTopStatList(wpTopStat);
    }

    /**
     * 新增销售统计
     * 
     * @param wpTopStat 销售统计
     * @return 结果
     */
    @Override
    public int insertWpTopStat(WpTopStat wpTopStat)
    {
        return wpTopStatMapper.insertWpTopStat(wpTopStat);
    }

    /**
     * 修改销售统计
     * 
     * @param wpTopStat 销售统计
     * @return 结果
     */
    @Override
    public int updateWpTopStat(WpTopStat wpTopStat)
    {
        return wpTopStatMapper.updateWpTopStat(wpTopStat);
    }

    /**
     * 删除销售统计对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWpTopStatByIds(String ids)
    {
        return wpTopStatMapper.deleteWpTopStatByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除销售统计信息
     * 
     * @param id 销售统计ID
     * @return 结果
     */
    public int deleteWpTopStatById(Integer id)
    {
        return wpTopStatMapper.deleteWpTopStatById(id);
    }
    
    public List<WpTopStat> selectTotalList(WpTopStat wpTopStat){
    	return this.wpTopStatMapper.selectTotalList(wpTopStat);
    }

	@Override
	public void batchAdd(List<WpTopStat> topList) {
		for(WpTopStat stat : topList){
			this.wpTopStatMapper.insertWpTopStat(stat);
		}
//		this.wpTopStatMapper.batchAdd(topList);
	}
}
