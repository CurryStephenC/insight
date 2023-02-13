package com.wjyoption.system.mapper;

import com.wjyoption.system.domain.WpCheckinRewardUser;
import java.util.List;

/**
 * 用户累计签到奖励Mapper接口
 * 
 * @author wjyoption
 * @date 2021-06-12
 */
public interface WpCheckinRewardUserMapper 
{
    /**
     * 查询用户累计签到奖励
     * 
     * @param id 用户累计签到奖励ID
     * @return 用户累计签到奖励
     */
    public WpCheckinRewardUser selectWpCheckinRewardUserById(Integer id);

    /**
     * 查询用户累计签到奖励列表
     * 
     * @param wpCheckinRewardUser 用户累计签到奖励
     * @return 用户累计签到奖励集合
     */
    public List<WpCheckinRewardUser> selectWpCheckinRewardUserList(WpCheckinRewardUser wpCheckinRewardUser);

    /**
     * 新增用户累计签到奖励
     * 
     * @param wpCheckinRewardUser 用户累计签到奖励
     * @return 结果
     */
    public int insertWpCheckinRewardUser(WpCheckinRewardUser wpCheckinRewardUser);

    /**
     * 修改用户累计签到奖励
     * 
     * @param wpCheckinRewardUser 用户累计签到奖励
     * @return 结果
     */
    public int updateWpCheckinRewardUser(WpCheckinRewardUser wpCheckinRewardUser);

    /**
     * 删除用户累计签到奖励
     * 
     * @param id 用户累计签到奖励ID
     * @return 结果
     */
    public int deleteWpCheckinRewardUserById(Integer id);

    /**
     * 批量删除用户累计签到奖励
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpCheckinRewardUserByIds(String[] ids);
}
