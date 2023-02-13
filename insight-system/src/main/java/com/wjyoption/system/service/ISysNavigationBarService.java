package com.wjyoption.system.service;

import com.wjyoption.system.domain.SysNavigationBar;
import java.util.List;

/**
 * 导航栏Service接口
 * 
 * @author hs
 * @date 2020-07-01
 */
public interface ISysNavigationBarService 
{
    /**
     * 查询导航栏
     * 
     * @param id 导航栏ID
     * @return 导航栏
     */
    public SysNavigationBar selectSysNavigationBarById(Long id);

    /**
     * 查询导航栏列表
     * 
     * @param sysNavigationBar 导航栏
     * @return 导航栏集合
     */
    public List<SysNavigationBar> selectSysNavigationBarList(SysNavigationBar sysNavigationBar);

    /**
     * 新增导航栏
     * 
     * @param sysNavigationBar 导航栏
     * @return 结果
     */
    public int insertSysNavigationBar(SysNavigationBar sysNavigationBar);

    /**
     * 修改导航栏
     * 
     * @param sysNavigationBar 导航栏
     * @return 结果
     */
    public int updateSysNavigationBar(SysNavigationBar sysNavigationBar);

    /**
     * 批量删除导航栏
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysNavigationBarByIds(String ids);

    /**
     * 删除导航栏信息
     * 
     * @param id 导航栏ID
     * @return 结果
     */
    public int deleteSysNavigationBarById(Long id);
}
