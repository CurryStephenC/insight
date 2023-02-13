package com.wjyoption.system.service.impl;

import java.util.List;

import com.wjyoption.common.utils.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wjyoption.system.mapper.SysBanksMapper;
import com.wjyoption.system.domain.SysBanks;
import com.wjyoption.system.service.ISysBanksService;
import com.wjyoption.system.vo.resp.BanksResp;
import com.wjyoption.common.core.text.Convert;

/**
 * 银行信息Service业务层处理
 * 
 * @author hs
 * @date 2021-03-29
 */
@Service
public class SysBanksServiceImpl implements ISysBanksService 
{
    @Autowired
    private SysBanksMapper sysBanksMapper;

    /**
     * 查询银行信息
     * 
     * @param id 银行信息ID
     * @return 银行信息
     */
    @Override
    public SysBanks selectSysBanksById(Integer id)
    {
        return sysBanksMapper.selectSysBanksById(id);
    }

    /**
     * 查询银行信息列表
     * 
     * @param sysBanks 银行信息
     * @return 银行信息
     */
    @Override
    public List<SysBanks> selectSysBanksList(SysBanks sysBanks)
    {
        return sysBanksMapper.selectSysBanksList(sysBanks);
    }

    /**
     * 新增银行信息
     * 
     * @param sysBanks 银行信息
     * @return 结果
     */
    @Override
    public int insertSysBanks(SysBanks sysBanks)
    {
        sysBanks.setCreateTime(DateUtils.getNowDate());
        return sysBanksMapper.insertSysBanks(sysBanks);
    }

    /**
     * 修改银行信息
     * 
     * @param sysBanks 银行信息
     * @return 结果
     */
    @Override
    public int updateSysBanks(SysBanks sysBanks)
    {
        sysBanks.setUpdateTime(DateUtils.getNowDate());
        return sysBanksMapper.updateSysBanks(sysBanks);
    }

    /**
     * 删除银行信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysBanksByIds(String ids)
    {
        return sysBanksMapper.deleteSysBanksByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除银行信息信息
     * 
     * @param id 银行信息ID
     * @return 结果
     */
    public int deleteSysBanksById(Integer id)
    {
        return sysBanksMapper.deleteSysBanksById(id);
    }

	@Override
	public List<BanksResp> selectBanksList() {
		return this.sysBanksMapper.selectBanksList();
	}
}
