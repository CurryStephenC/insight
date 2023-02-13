package com.wjyoption.system.service.impl;

import java.util.List;
import com.wjyoption.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wjyoption.system.mapper.SysNavigationBarMapper;
import com.wjyoption.system.domain.SysNavigationBar;
import com.wjyoption.system.service.ISysNavigationBarService;
import com.wjyoption.common.core.text.Convert;

/**
 * 导航栏Service业务层处理
 * 
 * @author hs
 * @date 2020-07-01
 */
@Service
public class SysNavigationBarServiceImpl implements ISysNavigationBarService 
{
    @Autowired
    private SysNavigationBarMapper sysNavigationBarMapper;

    /**
     * 查询导航栏
     * 
     * @param id 导航栏ID
     * @return 导航栏
     */
    @Override
    public SysNavigationBar selectSysNavigationBarById(Long id)
    {
        return sysNavigationBarMapper.selectSysNavigationBarById(id);
    }

    /**
     * 查询导航栏列表
     * 
     * @param sysNavigationBar 导航栏
     * @return 导航栏
     */
    @Override
    public List<SysNavigationBar> selectSysNavigationBarList(SysNavigationBar sysNavigationBar)
    {
        return sysNavigationBarMapper.selectSysNavigationBarList(sysNavigationBar);
    }

    /**
     * 新增导航栏
     * 
     * @param sysNavigationBar 导航栏
     * @return 结果
     */
    @Override
    public int insertSysNavigationBar(SysNavigationBar sysNavigationBar)
    {
        sysNavigationBar.setCreateTime(DateUtils.getNowDate());
        return sysNavigationBarMapper.insertSysNavigationBar(sysNavigationBar);
    }

    /**
     * 修改导航栏
     * 
     * @param sysNavigationBar 导航栏
     * @return 结果
     */
    @Override
    public int updateSysNavigationBar(SysNavigationBar sysNavigationBar)
    {
        sysNavigationBar.setUpdateTime(DateUtils.getNowDate());
        return sysNavigationBarMapper.updateSysNavigationBar(sysNavigationBar);
    }

    /**
     * 删除导航栏对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysNavigationBarByIds(String ids)
    {
        return sysNavigationBarMapper.deleteSysNavigationBarByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除导航栏信息
     * 
     * @param id 导航栏ID
     * @return 结果
     */
    public int deleteSysNavigationBarById(Long id)
    {
        return sysNavigationBarMapper.deleteSysNavigationBarById(id);
    }
}
