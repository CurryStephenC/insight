package com.wjyoption.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wjyoption.system.mapper.WpStatRecordMapper;
import com.wjyoption.system.domain.WpStatRecord;
import com.wjyoption.system.service.IWpStatRecordService;
import com.wjyoption.system.vo.report.StatRecordTotal;
import com.wjyoption.common.core.text.Convert;

/**
 * 统计记录Service业务层处理
 * 
 * @author wjyoption
 * @date 2021-06-21
 */
@Service
public class WpStatRecordServiceImpl implements IWpStatRecordService 
{
    @Autowired
    private WpStatRecordMapper wpStatRecordMapper;

    /**
     * 查询统计记录
     * 
     * @param id 统计记录ID
     * @return 统计记录
     */
    @Override
    public WpStatRecord selectWpStatRecordById(Integer id)
    {
        return wpStatRecordMapper.selectWpStatRecordById(id);
    }

    /**
     * 查询统计记录列表
     * 
     * @param wpStatRecord 统计记录
     * @return 统计记录
     */
    @Override
    public List<WpStatRecord> selectWpStatRecordList(WpStatRecord wpStatRecord)
    {
        return wpStatRecordMapper.selectWpStatRecordList(wpStatRecord);
    }

    /**
     * 新增统计记录
     * 
     * @param wpStatRecord 统计记录
     * @return 结果
     */
    @Override
    public int insertWpStatRecord(WpStatRecord wpStatRecord)
    {
        return wpStatRecordMapper.insertWpStatRecord(wpStatRecord);
    }

    /**
     * 修改统计记录
     * 
     * @param wpStatRecord 统计记录
     * @return 结果
     */
    @Override
    public int updateWpStatRecord(WpStatRecord wpStatRecord)
    {
        return wpStatRecordMapper.updateWpStatRecord(wpStatRecord);
    }

    /**
     * 删除统计记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWpStatRecordByIds(String ids)
    {
        return wpStatRecordMapper.deleteWpStatRecordByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除统计记录信息
     * 
     * @param id 统计记录ID
     * @return 结果
     */
    public int deleteWpStatRecordById(Integer id)
    {
        return wpStatRecordMapper.deleteWpStatRecordById(id);
    }

	@Override
	public WpStatRecord selectWpStatRecordByDaily(int daily) {
		return this.wpStatRecordMapper.selectWpStatRecordByDaily(daily);
	}

	@Override
	public StatRecordTotal selectWpStatRecordTotal(WpStatRecord wpStatRecord) {
		return this.wpStatRecordMapper.selectWpStatRecordTotal(wpStatRecord);
	}
}
