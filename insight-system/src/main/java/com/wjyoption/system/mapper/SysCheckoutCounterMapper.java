package com.wjyoption.system.mapper;

import com.wjyoption.system.domain.SysCheckoutCounter;

import java.util.List;

/**
 * 收银台Mapper接口
 * 
 * @author hs
 * @date 2021-06-25
 */
public interface SysCheckoutCounterMapper 
{
    /**
     * 查询收银台
     * 
     * @param id 收银台ID
     * @return 收银台
     */
    public SysCheckoutCounter selectSysCheckoutCounterById(Long id);

    /**
     * 查询收银台列表
     * 
     * @param sysCheckoutCounter 收银台
     * @return 收银台集合
     */
    public List<SysCheckoutCounter> selectSysCheckoutCounterList(SysCheckoutCounter sysCheckoutCounter);

    /**
     * 新增收银台
     * 
     * @param sysCheckoutCounter 收银台
     * @return 结果
     */
    public int insertSysCheckoutCounter(SysCheckoutCounter sysCheckoutCounter);

    /**
     * 修改收银台
     * 
     * @param sysCheckoutCounter 收银台
     * @return 结果
     */
    public int updateSysCheckoutCounter(SysCheckoutCounter sysCheckoutCounter);

    /**
     * 删除收银台
     * 
     * @param id 收银台ID
     * @return 结果
     */
    public int deleteSysCheckoutCounterById(Long id);

    /**
     * 批量删除收银台
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysCheckoutCounterByIds(String[] ids);

	public SysCheckoutCounter selectByOrderid(String orderid);

}
