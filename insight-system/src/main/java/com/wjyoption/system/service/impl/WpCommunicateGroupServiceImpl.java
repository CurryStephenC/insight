package com.wjyoption.system.service.impl;

import java.util.List;
import com.wjyoption.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wjyoption.system.mapper.WpCommunicateGroupMapper;
import com.wjyoption.system.domain.WpCommunicateGroup;
import com.wjyoption.system.service.IWpCommunicateGroupService;
import com.wjyoption.common.core.text.Convert;

/**
 * 交流推广群Service业务层处理
 * 
 * @author wjyoption
 * @date 2021-07-28
 */
@Service
public class WpCommunicateGroupServiceImpl implements IWpCommunicateGroupService 
{
    @Autowired
    private WpCommunicateGroupMapper wpCommunicateGroupMapper;

    /**
     * 查询交流推广群
     * 
     * @param id 交流推广群ID
     * @return 交流推广群
     */
    @Override
    public WpCommunicateGroup selectWpCommunicateGroupById(Long id)
    {
        return wpCommunicateGroupMapper.selectWpCommunicateGroupById(id);
    }

    /**
     * 查询交流推广群列表
     * 
     * @param wpCommunicateGroup 交流推广群
     * @return 交流推广群
     */
    @Override
    public List<WpCommunicateGroup> selectWpCommunicateGroupList(WpCommunicateGroup wpCommunicateGroup)
    {
        return wpCommunicateGroupMapper.selectWpCommunicateGroupList(wpCommunicateGroup);
    }

    /**
     * 新增交流推广群
     * 
     * @param wpCommunicateGroup 交流推广群
     * @return 结果
     */
    @Override
    public int insertWpCommunicateGroup(WpCommunicateGroup wpCommunicateGroup)
    {
        wpCommunicateGroup.setCreateTime(DateUtils.getNowDate());
        return wpCommunicateGroupMapper.insertWpCommunicateGroup(wpCommunicateGroup);
    }

    /**
     * 修改交流推广群
     * 
     * @param wpCommunicateGroup 交流推广群
     * @return 结果
     */
    @Override
    public int updateWpCommunicateGroup(WpCommunicateGroup wpCommunicateGroup)
    {
        wpCommunicateGroup.setUpdateTime(DateUtils.getNowDate());
        return wpCommunicateGroupMapper.updateWpCommunicateGroup(wpCommunicateGroup);
    }

    /**
     * 删除交流推广群对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWpCommunicateGroupByIds(String ids)
    {
        return wpCommunicateGroupMapper.deleteWpCommunicateGroupByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除交流推广群信息
     * 
     * @param id 交流推广群ID
     * @return 结果
     */
    public int deleteWpCommunicateGroupById(Long id)
    {
        return wpCommunicateGroupMapper.deleteWpCommunicateGroupById(id);
    }
}
