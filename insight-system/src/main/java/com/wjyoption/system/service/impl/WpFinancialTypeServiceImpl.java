package com.wjyoption.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wjyoption.system.mapper.WpFinancialTypeMapper;
import com.wjyoption.system.domain.WpFinancialType;
import com.wjyoption.system.service.IWpFinancialTypeService;
import com.wjyoption.system.vo.param.FinancialTypeParam;
import com.wjyoption.system.vo.resp.FinancialTypeResp;
import com.wjyoption.common.core.text.Convert;
import com.wjyoption.common.utils.DateUtils;

/**
 * 理财类型Service业务层处理
 * 
 * @author wjyoption
 * @date 2021-06-04
 */
@Service
public class WpFinancialTypeServiceImpl implements IWpFinancialTypeService 
{
    @Autowired
    private WpFinancialTypeMapper wpFinancialTypeMapper;

    /**
     * 查询理财类型
     * 
     * @param id 理财类型ID
     * @return 理财类型
     */
    @Override
    public WpFinancialType selectWpFinancialTypeById(Integer id)
    {
        return wpFinancialTypeMapper.selectWpFinancialTypeById(id);
    }

    /**
     * 查询理财类型列表
     * 
     * @param wpFinancialType 理财类型
     * @return 理财类型
     */
    @Override
    public List<WpFinancialType> selectWpFinancialTypeList(WpFinancialType wpFinancialType)
    {
        return wpFinancialTypeMapper.selectWpFinancialTypeList(wpFinancialType);
    }

    /**
     * 新增理财类型
     * 
     * @param wpFinancialType 理财类型
     * @return 结果
     */
    @Override
    public int insertWpFinancialType(WpFinancialType wpFinancialType)
    {
    	wpFinancialType.setCreatetime(DateUtils.getNowSecond());
        return wpFinancialTypeMapper.insertWpFinancialType(wpFinancialType);
    }

    /**
     * 修改理财类型
     * 
     * @param wpFinancialType 理财类型
     * @return 结果
     */
    @Override
    public int updateWpFinancialType(WpFinancialType wpFinancialType)
    {
        return wpFinancialTypeMapper.updateWpFinancialType(wpFinancialType);
    }

    /**
     * 删除理财类型对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWpFinancialTypeByIds(String ids)
    {
        return wpFinancialTypeMapper.deleteWpFinancialTypeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除理财类型信息
     * 
     * @param id 理财类型ID
     * @return 结果
     */
    public int deleteWpFinancialTypeById(Integer id)
    {
        return wpFinancialTypeMapper.deleteWpFinancialTypeById(id);
    }

	@Override
	public List<FinancialTypeResp> selectFinancialTypeResp(
			FinancialTypeParam wpFinancialType) {
		return this.wpFinancialTypeMapper.selectFinancialTypeResp(wpFinancialType);
	}
}
