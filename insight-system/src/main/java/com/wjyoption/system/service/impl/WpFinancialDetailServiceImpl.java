package com.wjyoption.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wjyoption.system.mapper.WpFinancialDetailMapper;
import com.wjyoption.system.domain.WpFinancialDetail;
import com.wjyoption.system.service.IWpFinancialDetailService;
import com.wjyoption.system.vo.resp.FinancialDetailResp;
import com.wjyoption.common.core.text.Convert;

/**
 * 理财收益详情Service业务层处理
 * 
 * @author wjyoption
 * @date 2021-06-07
 */
@Service
public class WpFinancialDetailServiceImpl implements IWpFinancialDetailService 
{
    @Autowired
    private WpFinancialDetailMapper wpFinancialDetailMapper;

    /**
     * 查询理财收益详情
     * 
     * @param id 理财收益详情ID
     * @return 理财收益详情
     */
    @Override
    public WpFinancialDetail selectWpFinancialDetailById(Integer id)
    {
        return wpFinancialDetailMapper.selectWpFinancialDetailById(id);
    }

    /**
     * 查询理财收益详情列表
     * 
     * @param wpFinancialDetail 理财收益详情
     * @return 理财收益详情
     */
    @Override
    public List<WpFinancialDetail> selectWpFinancialDetailList(WpFinancialDetail wpFinancialDetail)
    {
        return wpFinancialDetailMapper.selectWpFinancialDetailList(wpFinancialDetail);
    }

    /**
     * 新增理财收益详情
     * 
     * @param wpFinancialDetail 理财收益详情
     * @return 结果
     */
    @Override
    public int insertWpFinancialDetail(WpFinancialDetail wpFinancialDetail)
    {
        return wpFinancialDetailMapper.insertWpFinancialDetail(wpFinancialDetail);
    }

    /**
     * 修改理财收益详情
     * 
     * @param wpFinancialDetail 理财收益详情
     * @return 结果
     */
    @Override
    public int updateWpFinancialDetail(WpFinancialDetail wpFinancialDetail)
    {
        return wpFinancialDetailMapper.updateWpFinancialDetail(wpFinancialDetail);
    }

    /**
     * 删除理财收益详情对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWpFinancialDetailByIds(String ids)
    {
        return wpFinancialDetailMapper.deleteWpFinancialDetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除理财收益详情信息
     * 
     * @param id 理财收益详情ID
     * @return 结果
     */
    public int deleteWpFinancialDetailById(Integer id)
    {
        return wpFinancialDetailMapper.deleteWpFinancialDetailById(id);
    }

	@Override
	public List<FinancialDetailResp> selectFinancialDetailList(Integer refid) {
		return this.wpFinancialDetailMapper.selectFinancialDetailList(refid);
	}
}
