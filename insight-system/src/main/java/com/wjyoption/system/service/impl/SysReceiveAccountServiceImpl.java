package com.wjyoption.system.service.impl;

import java.util.List;
import com.wjyoption.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wjyoption.system.mapper.SysReceiveAccountMapper;
import com.wjyoption.system.domain.SysReceiveAccount;
import com.wjyoption.system.service.ISysReceiveAccountService;
import com.wjyoption.common.core.text.Convert;

/**
 * 收款账号Service业务层处理
 * 
 * @author hs
 * @date 2021-06-25
 */
@Service
public class SysReceiveAccountServiceImpl implements ISysReceiveAccountService 
{
    @Autowired
    private SysReceiveAccountMapper sysReceiveAccountMapper;

    /**
     * 查询收款账号
     * 
     * @param id 收款账号ID
     * @return 收款账号
     */
    @Override
    public SysReceiveAccount selectSysReceiveAccountById(Long id)
    {
        return sysReceiveAccountMapper.selectSysReceiveAccountById(id);
    }

    /**
     * 查询收款账号列表
     * 
     * @param sysReceiveAccount 收款账号
     * @return 收款账号
     */
    @Override
    public List<SysReceiveAccount> selectSysReceiveAccountList(SysReceiveAccount sysReceiveAccount)
    {
        return sysReceiveAccountMapper.selectSysReceiveAccountList(sysReceiveAccount);
    }

    /**
     * 新增收款账号
     * 
     * @param sysReceiveAccount 收款账号
     * @return 结果
     */
    @Override
    public int insertSysReceiveAccount(SysReceiveAccount sysReceiveAccount)
    {
        sysReceiveAccount.setCreateTime(DateUtils.getNowDate());
        return sysReceiveAccountMapper.insertSysReceiveAccount(sysReceiveAccount);
    }

    /**
     * 修改收款账号
     * 
     * @param sysReceiveAccount 收款账号
     * @return 结果
     */
    @Override
    public int updateSysReceiveAccount(SysReceiveAccount sysReceiveAccount)
    {
        sysReceiveAccount.setUpdateTime(DateUtils.getNowDate());
        return sysReceiveAccountMapper.updateSysReceiveAccount(sysReceiveAccount);
    }

    /**
     * 删除收款账号对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysReceiveAccountByIds(String ids)
    {
        return sysReceiveAccountMapper.deleteSysReceiveAccountByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除收款账号信息
     * 
     * @param id 收款账号ID
     * @return 结果
     */
    public int deleteSysReceiveAccountById(Long id)
    {
        return sysReceiveAccountMapper.deleteSysReceiveAccountById(id);
    }
}
