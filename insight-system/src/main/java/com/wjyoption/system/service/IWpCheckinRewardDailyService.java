package com.wjyoption.system.service;

import com.wjyoption.system.domain.WpCheckinRewardDaily;
import java.util.List;

/**
 * 用户每日签到奖项Service接口
 * 
 * @author wjyoption
 * @date 2021-06-11
 */
public interface IWpCheckinRewardDailyService 
{
    /**
     * 查询用户每日签到奖项
     * 
     * @param id 用户每日签到奖项ID
     * @return 用户每日签到奖项
     */
    public WpCheckinRewardDaily selectWpCheckinRewardDailyById(Integer id);

    /**
     * 查询用户每日签到奖项列表
     * 
     * @param wpCheckinRewardDaily 用户每日签到奖项
     * @return 用户每日签到奖项集合
     */
    public List<WpCheckinRewardDaily> selectWpCheckinRewardDailyList(WpCheckinRewardDaily wpCheckinRewardDaily);
    public List<WpCheckinRewardDaily> selectAll();

    /**
     * 新增用户每日签到奖项
     * 
     * @param wpCheckinRewardDaily 用户每日签到奖项
     * @return 结果
     */
    public int insertWpCheckinRewardDaily(WpCheckinRewardDaily wpCheckinRewardDaily);

    /**
     * 修改用户每日签到奖项
     * 
     * @param wpCheckinRewardDaily 用户每日签到奖项
     * @return 结果
     */
    public int updateWpCheckinRewardDaily(WpCheckinRewardDaily wpCheckinRewardDaily);

    /**
     * 批量删除用户每日签到奖项
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpCheckinRewardDailyByIds(String ids);

    /**
     * 删除用户每日签到奖项信息
     * 
     * @param id 用户每日签到奖项ID
     * @return 结果
     */
    public int deleteWpCheckinRewardDailyById(Integer id);
}
