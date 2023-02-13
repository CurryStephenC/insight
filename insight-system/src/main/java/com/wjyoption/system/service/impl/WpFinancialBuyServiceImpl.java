package com.wjyoption.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wjyoption.system.mapper.WpFinancialBuyMapper;
import com.wjyoption.system.domain.WpFinancialBuy;
import com.wjyoption.system.service.IWpFinancialBuyService;
import com.wjyoption.system.vo.resp.FinancialBuyResp;
import com.wjyoption.common.core.text.Convert;

/**
 * 老师购买记录Service业务层处理
 * 
 * @author wjyoption
 * @date 2021-06-07
 */
@Service
public class WpFinancialBuyServiceImpl implements IWpFinancialBuyService 
{
    @Autowired
    private WpFinancialBuyMapper wpFinancialBuyMapper;

    /**
     * 查询老师购买记录
     * 
     * @param id 老师购买记录ID
     * @return 老师购买记录
     */
    @Override
    public WpFinancialBuy selectWpFinancialBuyById(Integer id)
    {
        return wpFinancialBuyMapper.selectWpFinancialBuyById(id);
    }

    /**
     * 查询老师购买记录列表
     * 
     * @param wpFinancialBuy 老师购买记录
     * @return 老师购买记录
     */
    @Override
    public List<WpFinancialBuy> selectWpFinancialBuyList(WpFinancialBuy wpFinancialBuy)
    {
        return wpFinancialBuyMapper.selectWpFinancialBuyList(wpFinancialBuy);
    }

    /**
     * 新增老师购买记录
     * 
     * @param wpFinancialBuy 老师购买记录
     * @return 结果
     */
    @Override
    public int insertWpFinancialBuy(WpFinancialBuy wpFinancialBuy)
    {
        return wpFinancialBuyMapper.insertWpFinancialBuy(wpFinancialBuy);
    }

    /**
     * 修改老师购买记录
     * 
     * @param wpFinancialBuy 老师购买记录
     * @return 结果
     */
    @Override
    public int updateWpFinancialBuy(WpFinancialBuy wpFinancialBuy)
    {
        return wpFinancialBuyMapper.updateWpFinancialBuy(wpFinancialBuy);
    }

    /**
     * 删除老师购买记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWpFinancialBuyByIds(String ids)
    {
        return wpFinancialBuyMapper.deleteWpFinancialBuyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除老师购买记录信息
     * 
     * @param id 老师购买记录ID
     * @return 结果
     */
    public int deleteWpFinancialBuyById(Integer id)
    {
        return wpFinancialBuyMapper.deleteWpFinancialBuyById(id);
    }

	@Override
	public List<FinancialBuyResp> selectFinancialBuyList(Integer detailid) {
		return this.wpFinancialBuyMapper.selectFinancialBuyList(detailid);
	}
}
