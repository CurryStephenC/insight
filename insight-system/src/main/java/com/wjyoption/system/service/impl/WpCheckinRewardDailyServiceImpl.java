package com.wjyoption.system.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.wjyoption.system.mapper.WpCheckinRewardDailyMapper;
import com.wjyoption.system.domain.WpCheckinRewardDaily;
import com.wjyoption.system.service.IWpCheckinRewardDailyService;
import com.wjyoption.common.core.text.Convert;
import com.wjyoption.common.utils.db.RedisEnum;

/**
 * 用户每日签到奖项Service业务层处理
 * 
 * @author wjyoption
 * @date 2021-06-11
 */
@Service
public class WpCheckinRewardDailyServiceImpl implements IWpCheckinRewardDailyService 
{
    @Autowired
    private WpCheckinRewardDailyMapper wpCheckinRewardDailyMapper;
    @SuppressWarnings("rawtypes")
	@Autowired RedisTemplate redisTemplate;

    /**
     * 查询用户每日签到奖项
     * 
     * @param id 用户每日签到奖项ID
     * @return 用户每日签到奖项
     */
    @Override
    public WpCheckinRewardDaily selectWpCheckinRewardDailyById(Integer id)
    {
        return wpCheckinRewardDailyMapper.selectWpCheckinRewardDailyById(id);
    }

    /**
     * 查询用户每日签到奖项列表
     * 
     * @param wpCheckinRewardDaily 用户每日签到奖项
     * @return 用户每日签到奖项
     */
    @Override
    public List<WpCheckinRewardDaily> selectWpCheckinRewardDailyList(WpCheckinRewardDaily wpCheckinRewardDaily)
    {
        return wpCheckinRewardDailyMapper.selectWpCheckinRewardDailyList(wpCheckinRewardDaily);
    }

    /**
     * 新增用户每日签到奖项
     * 
     * @param wpCheckinRewardDaily 用户每日签到奖项
     * @return 结果
     */
    @Override
    public int insertWpCheckinRewardDaily(WpCheckinRewardDaily wpCheckinRewardDaily)
    {
    	clearReds();
        return wpCheckinRewardDailyMapper.insertWpCheckinRewardDaily(wpCheckinRewardDaily);
    }

    /**
     * 修改用户每日签到奖项
     * 
     * @param wpCheckinRewardDaily 用户每日签到奖项
     * @return 结果
     */
    @Override
    public int updateWpCheckinRewardDaily(WpCheckinRewardDaily wpCheckinRewardDaily)
    {
    	clearReds();
        return wpCheckinRewardDailyMapper.updateWpCheckinRewardDaily(wpCheckinRewardDaily);
    }

    /**
     * 删除用户每日签到奖项对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWpCheckinRewardDailyByIds(String ids)
    {
    	clearReds();
        return wpCheckinRewardDailyMapper.deleteWpCheckinRewardDailyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户每日签到奖项信息
     * 
     * @param id 用户每日签到奖项ID
     * @return 结果
     */
    public int deleteWpCheckinRewardDailyById(Integer id)
    {
    	clearReds();
        return wpCheckinRewardDailyMapper.deleteWpCheckinRewardDailyById(id);
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<WpCheckinRewardDaily> selectAll() {
		String key = RedisEnum.CHECKIN_REWARD_DAILY.getKeyPrefix();
		BoundValueOperations<String, List<WpCheckinRewardDaily>> boundValueOps = this.redisTemplate.boundValueOps(key);
		List<WpCheckinRewardDaily> list = boundValueOps.get();
		if(CollectionUtils.isNotEmpty(list)){
			return list;
		}
		list = this.wpCheckinRewardDailyMapper.selectWpCheckinRewardDailyList(new WpCheckinRewardDaily());
		boundValueOps.set(list,1,TimeUnit.DAYS);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	private void clearReds(){
		String key = RedisEnum.CHECKIN_REWARD_DAILY.getKeyPrefix();
    	this.redisTemplate.delete(key);
	}
}
