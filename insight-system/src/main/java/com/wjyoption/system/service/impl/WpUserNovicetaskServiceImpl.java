package com.wjyoption.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wjyoption.system.mapper.WpUserNovicetaskMapper;
import com.wjyoption.system.domain.WpUserNovicetask;
import com.wjyoption.system.service.IWpUserNovicetaskService;
import com.wjyoption.common.core.text.Convert;

/**
 * 用户新手任务Service业务层处理
 * 
 * @author wjyoption
 * @date 2021-07-28
 */
@Service
public class WpUserNovicetaskServiceImpl implements IWpUserNovicetaskService 
{
    @Autowired
    private WpUserNovicetaskMapper wpUserNovicetaskMapper;

    /**
     * 查询用户新手任务
     * 
     * @param id 用户新手任务ID
     * @return 用户新手任务
     */
    @Override
    public WpUserNovicetask selectWpUserNovicetaskById(Integer id)
    {
        return wpUserNovicetaskMapper.selectWpUserNovicetaskById(id);
    }

    /**
     * 查询用户新手任务列表
     * 
     * @param wpUserNovicetask 用户新手任务
     * @return 用户新手任务
     */
    @Override
    public List<WpUserNovicetask> selectWpUserNovicetaskList(WpUserNovicetask wpUserNovicetask)
    {
        return wpUserNovicetaskMapper.selectWpUserNovicetaskList(wpUserNovicetask);
    }

    /**
     * 新增用户新手任务
     * 
     * @param wpUserNovicetask 用户新手任务
     * @return 结果
     */
    @Override
    public int insertWpUserNovicetask(WpUserNovicetask wpUserNovicetask)
    {
        return wpUserNovicetaskMapper.insertWpUserNovicetask(wpUserNovicetask);
    }

    /**
     * 修改用户新手任务
     * 
     * @param wpUserNovicetask 用户新手任务
     * @return 结果
     */
    @Override
    public int updateWpUserNovicetask(WpUserNovicetask wpUserNovicetask)
    {
        return wpUserNovicetaskMapper.updateWpUserNovicetask(wpUserNovicetask);
    }

    /**
     * 删除用户新手任务对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWpUserNovicetaskByIds(String ids)
    {
        return wpUserNovicetaskMapper.deleteWpUserNovicetaskByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户新手任务信息
     * 
     * @param id 用户新手任务ID
     * @return 结果
     */
    public int deleteWpUserNovicetaskById(Long id)
    {
        return wpUserNovicetaskMapper.deleteWpUserNovicetaskById(id);
    }
}
