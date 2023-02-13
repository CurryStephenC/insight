package com.wjyoption.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wjyoption.system.mapper.WpBankcardMapper;
import com.wjyoption.system.domain.WpBankcard;
import com.wjyoption.system.service.IWpBankcardService;
import com.wjyoption.common.core.text.Convert;

/**
 * 用户银行信息Service业务层处理
 * 
 * @author wjyoption
 * @date 2021-06-08
 */
@Service
public class WpBankcardServiceImpl implements IWpBankcardService 
{
    @Autowired
    private WpBankcardMapper wpBankcardMapper;

    /**
     * 查询用户银行信息
     * 
     * @param id 用户银行信息ID
     * @return 用户银行信息
     */
    @Override
    public WpBankcard selectWpBankcardById(Long id)
    {
        return wpBankcardMapper.selectWpBankcardById(id);
    }

    /**
     * 查询用户银行信息列表
     * 
     * @param wpBankcard 用户银行信息
     * @return 用户银行信息
     */
    @Override
    public List<WpBankcard> selectWpBankcardList(WpBankcard wpBankcard)
    {
        return wpBankcardMapper.selectWpBankcardList(wpBankcard);
    }

    /**
     * 新增用户银行信息
     * 
     * @param wpBankcard 用户银行信息
     * @return 结果
     */
    @Override
    public int insertWpBankcard(WpBankcard wpBankcard)
    {
        return wpBankcardMapper.insertWpBankcard(wpBankcard);
    }

    /**
     * 修改用户银行信息
     * 
     * @param wpBankcard 用户银行信息
     * @return 结果
     */
    @Override
    public int updateWpBankcard(WpBankcard wpBankcard)
    {
        return wpBankcardMapper.updateWpBankcard(wpBankcard);
    }

    /**
     * 删除用户银行信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWpBankcardByIds(String ids)
    {
        return wpBankcardMapper.deleteWpBankcardByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户银行信息信息
     * 
     * @param id 用户银行信息ID
     * @return 结果
     */
    public int deleteWpBankcardById(Long id)
    {
        return wpBankcardMapper.deleteWpBankcardById(id);
    }

	@Override
	public WpBankcard selectBankByUid(Integer uid) {
		return this.wpBankcardMapper.selectBankByUid(uid);
	}
}
