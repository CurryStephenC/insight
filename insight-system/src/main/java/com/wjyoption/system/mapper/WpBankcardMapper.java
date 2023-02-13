package com.wjyoption.system.mapper;

import com.wjyoption.system.domain.WpBankcard;

import java.util.List;

/**
 * 用户银行信息Mapper接口
 * 
 * @author wjyoption
 * @date 2021-06-08
 */
public interface WpBankcardMapper 
{
    /**
     * 查询用户银行信息
     * 
     * @param id 用户银行信息ID
     * @return 用户银行信息
     */
    public WpBankcard selectWpBankcardById(Long id);

    /**
     * 查询用户银行信息列表
     * 
     * @param wpBankcard 用户银行信息
     * @return 用户银行信息集合
     */
    public List<WpBankcard> selectWpBankcardList(WpBankcard wpBankcard);

    /**
     * 新增用户银行信息
     * 
     * @param wpBankcard 用户银行信息
     * @return 结果
     */
    public int insertWpBankcard(WpBankcard wpBankcard);

    /**
     * 修改用户银行信息
     * 
     * @param wpBankcard 用户银行信息
     * @return 结果
     */
    public int updateWpBankcard(WpBankcard wpBankcard);

    /**
     * 删除用户银行信息
     * 
     * @param id 用户银行信息ID
     * @return 结果
     */
    public int deleteWpBankcardById(Long id);

    /**
     * 批量删除用户银行信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpBankcardByIds(String[] ids);

	public WpBankcard selectBankByUid(Integer uid);
}
