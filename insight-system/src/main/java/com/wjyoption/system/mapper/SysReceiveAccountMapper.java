package com.wjyoption.system.mapper;

import com.wjyoption.system.domain.SysReceiveAccount;
import java.util.List;

/**
 * 收款账号Mapper接口
 * 
 * @author hs
 * @date 2021-06-25
 */
public interface SysReceiveAccountMapper 
{
    /**
     * 查询收款账号
     * 
     * @param id 收款账号ID
     * @return 收款账号
     */
    public SysReceiveAccount selectSysReceiveAccountById(Long id);

    /**
     * 查询收款账号列表
     * 
     * @param sysReceiveAccount 收款账号
     * @return 收款账号集合
     */
    public List<SysReceiveAccount> selectSysReceiveAccountList(SysReceiveAccount sysReceiveAccount);

    /**
     * 新增收款账号
     * 
     * @param sysReceiveAccount 收款账号
     * @return 结果
     */
    public int insertSysReceiveAccount(SysReceiveAccount sysReceiveAccount);

    /**
     * 修改收款账号
     * 
     * @param sysReceiveAccount 收款账号
     * @return 结果
     */
    public int updateSysReceiveAccount(SysReceiveAccount sysReceiveAccount);

    /**
     * 删除收款账号
     * 
     * @param id 收款账号ID
     * @return 结果
     */
    public int deleteSysReceiveAccountById(Long id);

    /**
     * 批量删除收款账号
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysReceiveAccountByIds(String[] ids);
}
