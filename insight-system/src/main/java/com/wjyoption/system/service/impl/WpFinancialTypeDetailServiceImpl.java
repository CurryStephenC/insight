package com.wjyoption.system.service.impl;

import java.util.List;

import com.wjyoption.common.utils.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wjyoption.system.mapper.WpFinancialTypeDetailMapper;
import com.wjyoption.system.domain.WpFinancialTypeDetail;
import com.wjyoption.system.service.IWpFinancialTypeDetailService;

/**
 * 理财类型详情Service业务层处理
 * 
 * @author wjyoption
 * @date 2021-06-04
 */
@Service
public class WpFinancialTypeDetailServiceImpl implements IWpFinancialTypeDetailService 
{
    @Autowired
    private WpFinancialTypeDetailMapper wpFinancialTypeDetailMapper;

    /**
     * 查询理财类型详情
     * 
     * @param id 理财类型详情ID
     * @return 理财类型详情
     */
    @Override
    public WpFinancialTypeDetail selectWpFinancialTypeDetailById(Integer id)
    {
        return wpFinancialTypeDetailMapper.selectWpFinancialTypeDetailById(id);
    }

    /**
     * 查询理财类型详情列表
     * 
     * @param wpFinancialTypeDetail 理财类型详情
     * @return 理财类型详情
     */
    @Override
    public List<WpFinancialTypeDetail> selectWpFinancialTypeDetailList(WpFinancialTypeDetail wpFinancialTypeDetail)
    {
        return wpFinancialTypeDetailMapper.selectWpFinancialTypeDetailList(wpFinancialTypeDetail);
    }

    /**
     * 新增理财类型详情
     * 
     * @param wpFinancialTypeDetail 理财类型详情
     * @return 结果
     */
    @Override
    public int insertWpFinancialTypeDetail(WpFinancialTypeDetail wpFinancialTypeDetail)
    {
        wpFinancialTypeDetail.setCreateTime(DateUtils.getNowDate());
        return wpFinancialTypeDetailMapper.insertWpFinancialTypeDetail(wpFinancialTypeDetail);
    }

    /**
     * 修改理财类型详情
     * 
     * @param wpFinancialTypeDetail 理财类型详情
     * @return 结果
     */
    @Override
    public int updateWpFinancialTypeDetail(WpFinancialTypeDetail wpFinancialTypeDetail)
    {
        wpFinancialTypeDetail.setUpdateTime(DateUtils.getNowDate());
        return wpFinancialTypeDetailMapper.updateWpFinancialTypeDetail(wpFinancialTypeDetail);
    }

	@Override
	public WpFinancialTypeDetail selectDetailBytypeId(Integer typeid) {
		return wpFinancialTypeDetailMapper.selectDetailBytypeId(typeid);
	}

}
