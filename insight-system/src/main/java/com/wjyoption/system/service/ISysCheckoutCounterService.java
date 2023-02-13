package com.wjyoption.system.service;

import com.wjyoption.system.domain.SysCheckoutCounter;
import java.util.List;

/**
 * 收银台Service接口
 * 
 * @author hs
 * @date 2021-06-25
 */
public interface ISysCheckoutCounterService 
{
    /**
     * 查询收银台
     * 
     * @param id 收银台ID
     * @return 收银台
     */
    public SysCheckoutCounter selectSysCheckoutCounterById(Long id);
    public SysCheckoutCounter selectByOrderid(String orderid);

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
     * 不带业务更新
     * @param sysCheckoutCounter
     * @return
     */
    public int updateOnly(SysCheckoutCounter sysCheckoutCounter);

    /**
     * 批量删除收银台
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysCheckoutCounterByIds(String ids);

    /**
     * 删除收银台信息
     * 
     * @param id 收银台ID
     * @return 结果
     */
    public int deleteSysCheckoutCounterById(Long id);
    
}
