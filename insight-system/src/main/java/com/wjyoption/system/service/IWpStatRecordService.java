package com.wjyoption.system.service;

import com.wjyoption.system.domain.WpStatRecord;
import com.wjyoption.system.vo.report.StatRecordTotal;

import java.util.List;

/**
 * 统计记录Service接口
 * 
 * @author wjyoption
 * @date 2021-06-21
 */
public interface IWpStatRecordService 
{
    /**
     * 查询统计记录
     * 
     * @param id 统计记录ID
     * @return 统计记录
     */
    public WpStatRecord selectWpStatRecordById(Integer id);

    /**
     * 查询统计记录列表
     * 
     * @param wpStatRecord 统计记录
     * @return 统计记录集合
     */
    public List<WpStatRecord> selectWpStatRecordList(WpStatRecord wpStatRecord);

    /**
     * 新增统计记录
     * 
     * @param wpStatRecord 统计记录
     * @return 结果
     */
    public int insertWpStatRecord(WpStatRecord wpStatRecord);

    /**
     * 修改统计记录
     * 
     * @param wpStatRecord 统计记录
     * @return 结果
     */
    public int updateWpStatRecord(WpStatRecord wpStatRecord);

    /**
     * 批量删除统计记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpStatRecordByIds(String ids);

    /**
     * 删除统计记录信息
     * 
     * @param id 统计记录ID
     * @return 结果
     */
    public int deleteWpStatRecordById(Integer id);

    /**
     * 获取指定日期的记录
     * @param daily
     * @return
     */
	public WpStatRecord selectWpStatRecordByDaily(int daily);
	
	public StatRecordTotal selectWpStatRecordTotal(WpStatRecord wpStatRecord);
}
