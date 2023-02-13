package com.wjyoption.system.service;

import com.wjyoption.system.domain.WpCommunicateGroup;
import java.util.List;

/**
 * 交流推广群Service接口
 * 
 * @author wjyoption
 * @date 2021-07-28
 */
public interface IWpCommunicateGroupService 
{
    /**
     * 查询交流推广群
     * 
     * @param id 交流推广群ID
     * @return 交流推广群
     */
    public WpCommunicateGroup selectWpCommunicateGroupById(Long id);

    /**
     * 查询交流推广群列表
     * 
     * @param wpCommunicateGroup 交流推广群
     * @return 交流推广群集合
     */
    public List<WpCommunicateGroup> selectWpCommunicateGroupList(WpCommunicateGroup wpCommunicateGroup);

    /**
     * 新增交流推广群
     * 
     * @param wpCommunicateGroup 交流推广群
     * @return 结果
     */
    public int insertWpCommunicateGroup(WpCommunicateGroup wpCommunicateGroup);

    /**
     * 修改交流推广群
     * 
     * @param wpCommunicateGroup 交流推广群
     * @return 结果
     */
    public int updateWpCommunicateGroup(WpCommunicateGroup wpCommunicateGroup);

    /**
     * 批量删除交流推广群
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpCommunicateGroupByIds(String ids);

    /**
     * 删除交流推广群信息
     * 
     * @param id 交流推广群ID
     * @return 结果
     */
    public int deleteWpCommunicateGroupById(Long id);
}
