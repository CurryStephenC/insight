package com.wjyoption.system.mapper;

import com.wjyoption.system.domain.WpCheckinReward;
import com.wjyoption.system.vo.resp.CheckinRewardUserResp;

import java.util.List;

/**
 * 累计签到奖项Mapper接口
 * 
 * @author wjyoption
 * @date 2021-06-11
 */
public interface WpCheckinRewardMapper 
{
    /**
     * 查询累计签到奖项
     * 
     * @param id 累计签到奖项ID
     * @return 累计签到奖项
     */
    public WpCheckinReward selectWpCheckinRewardById(Integer id);

    /**
     * 查询累计签到奖项列表
     * 
     * @param wpCheckinReward 累计签到奖项
     * @return 累计签到奖项集合
     */
    public List<WpCheckinReward> selectWpCheckinRewardList(WpCheckinReward wpCheckinReward);
    
    /**
     * 新增累计签到奖项
     * 
     * @param wpCheckinReward 累计签到奖项
     * @return 结果
     */
    public int insertWpCheckinReward(WpCheckinReward wpCheckinReward);

    /**
     * 修改累计签到奖项
     * 
     * @param wpCheckinReward 累计签到奖项
     * @return 结果
     */
    public int updateWpCheckinReward(WpCheckinReward wpCheckinReward);

    /**
     * 删除累计签到奖项
     * 
     * @param id 累计签到奖项ID
     * @return 结果
     */
    public int deleteWpCheckinRewardById(Integer id);

    /**
     * 批量删除累计签到奖项
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpCheckinRewardByIds(String[] ids);
    
    
    
    
    
    
    
    /**
     * 获取我的签到奖励情况
     * @param uid
     * @return
     */
    public List<CheckinRewardUserResp> selectMyCheckinRewardList(Integer uid);
}
