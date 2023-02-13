package com.wjyoption.system.service;

import java.util.List;

import com.wjyoption.system.domain.SysBanks;
import com.wjyoption.system.vo.resp.BanksResp;

/**
 * 银行信息Service接口
 * 
 * @author hs
 * @date 2021-03-29
 */
public interface ISysBanksService 
{
    /**
     * 查询银行信息
     * 
     * @param id 银行信息ID
     * @return 银行信息
     */
    public SysBanks selectSysBanksById(Integer id);

    /**
     * 查询银行信息列表
     * 
     * @param sysBanks 银行信息
     * @return 银行信息集合
     */
    public List<SysBanks> selectSysBanksList(SysBanks sysBanks);

    /**
     * 新增银行信息
     * 
     * @param sysBanks 银行信息
     * @return 结果
     */
    public int insertSysBanks(SysBanks sysBanks);

    /**
     * 修改银行信息
     * 
     * @param sysBanks 银行信息
     * @return 结果
     */
    public int updateSysBanks(SysBanks sysBanks);

    /**
     * 批量删除银行信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysBanksByIds(String ids);

    /**
     * 删除银行信息信息
     * 
     * @param id 银行信息ID
     * @return 结果
     */
    public int deleteSysBanksById(Integer id);
    
    /**
     * 获取显示银行信息列表
     * @return
     */
    public List<BanksResp> selectBanksList();
}
