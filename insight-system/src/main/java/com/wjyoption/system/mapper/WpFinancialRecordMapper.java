package com.wjyoption.system.mapper;

import com.wjyoption.system.domain.WpFinancialRecord;
import com.wjyoption.system.vo.resp.FinancialRecordResp;

import java.util.List;

/**
 * 理财记录Mapper接口
 * 
 * @author wjyoption
 * @date 2021-06-05
 */
public interface WpFinancialRecordMapper 
{
    /**
     * 查询理财记录
     * 
     * @param id 理财记录ID
     * @return 理财记录
     */
    public WpFinancialRecord selectWpFinancialRecordById(Integer id);

    /**
     * 查询理财记录列表
     * 
     * @param wpFinancialRecord 理财记录
     * @return 理财记录集合
     */
    public List<WpFinancialRecord> selectWpFinancialRecordList(WpFinancialRecord wpFinancialRecord);
    public List<FinancialRecordResp> selectFinancialRecordList(WpFinancialRecord wpFinancialRecord);

    /**
     * 新增理财记录
     * 
     * @param wpFinancialRecord 理财记录
     * @return 结果
     */
    public int insertWpFinancialRecord(WpFinancialRecord wpFinancialRecord);

    /**
     * 修改理财记录
     * 
     * @param wpFinancialRecord 理财记录
     * @return 结果
     */
    public int updateWpFinancialRecord(WpFinancialRecord wpFinancialRecord);

    /**
     * 删除理财记录
     * 
     * @param id 理财记录ID
     * @return 结果
     */
    public int deleteWpFinancialRecordById(Integer id);

    /**
     * 批量删除理财记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpFinancialRecordByIds(String[] ids);

	public List<WpFinancialRecord> selectIbRecordList(
			WpFinancialRecord wpFinancialRecord);
}
