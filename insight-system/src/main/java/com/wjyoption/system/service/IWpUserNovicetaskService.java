package com.wjyoption.system.service;

import com.wjyoption.system.domain.WpUserNovicetask;
import java.util.List;

/**
 * 用户新手任务Service接口
 * 
 * @author wjyoption
 * @date 2021-07-28
 */
public interface IWpUserNovicetaskService 
{
    /**
     * 查询用户新手任务
     * 
     * @param id 用户新手任务ID
     * @return 用户新手任务
     */
    public WpUserNovicetask selectWpUserNovicetaskById(Integer id);

    /**
     * 查询用户新手任务列表
     * 
     * @param wpUserNovicetask 用户新手任务
     * @return 用户新手任务集合
     */
    public List<WpUserNovicetask> selectWpUserNovicetaskList(WpUserNovicetask wpUserNovicetask);

    /**
     * 新增用户新手任务
     * 
     * @param wpUserNovicetask 用户新手任务
     * @return 结果
     */
    public int insertWpUserNovicetask(WpUserNovicetask wpUserNovicetask);

    /**
     * 修改用户新手任务
     * 
     * @param wpUserNovicetask 用户新手任务
     * @return 结果
     */
    public int updateWpUserNovicetask(WpUserNovicetask wpUserNovicetask);

    /**
     * 批量删除用户新手任务
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpUserNovicetaskByIds(String ids);

    /**
     * 删除用户新手任务信息
     * 
     * @param id 用户新手任务ID
     * @return 结果
     */
    public int deleteWpUserNovicetaskById(Long id);
}
