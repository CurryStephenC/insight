package com.wjyoption.system.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.wjyoption.common.core.text.Convert;
import com.wjyoption.common.utils.db.RedisEnum;
import com.wjyoption.system.domain.WpCheckinReward;
import com.wjyoption.system.mapper.WpCheckinRewardMapper;
import com.wjyoption.system.service.IWpCheckinRewardService;
import com.wjyoption.system.vo.resp.CheckinRewardUserResp;

/**
 * 累计签到奖项Service业务层处理
 * 
 * @author wjyoption
 * @date 2021-06-11
 */
@Service
public class WpCheckinRewardServiceImpl implements IWpCheckinRewardService 
{
    @Autowired
    private WpCheckinRewardMapper wpCheckinRewardMapper;
    
    @SuppressWarnings("rawtypes")
	@Autowired RedisTemplate redisTemplate;

    /**
     * 查询累计签到奖项
     * 
     * @param id 累计签到奖项ID
     * @return 累计签到奖项
     */
    @Override
    public WpCheckinReward selectWpCheckinRewardById(Integer id)
    {
        return wpCheckinRewardMapper.selectWpCheckinRewardById(id);
    }

    /**
     * 查询累计签到奖项列表
     * 
     * @param wpCheckinReward 累计签到奖项
     * @return 累计签到奖项
     */
    @Override
    public List<WpCheckinReward> selectWpCheckinRewardList(WpCheckinReward wpCheckinReward)
    {
        return wpCheckinRewardMapper.selectWpCheckinRewardList(wpCheckinReward);
    }

    /**
     * 新增累计签到奖项
     * 
     * @param wpCheckinReward 累计签到奖项
     * @return 结果
     */
    @Override
    public int insertWpCheckinReward(WpCheckinReward wpCheckinReward)
    {
    	clearCache();
        return wpCheckinRewardMapper.insertWpCheckinReward(wpCheckinReward);
    }

    /**
     * 修改累计签到奖项
     * 
     * @param wpCheckinReward 累计签到奖项
     * @return 结果
     */
    @Override
    public int updateWpCheckinReward(WpCheckinReward wpCheckinReward)
    {
    	clearCache();
        return wpCheckinRewardMapper.updateWpCheckinReward(wpCheckinReward);
    }

    /**
     * 删除累计签到奖项对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWpCheckinRewardByIds(String ids)
    {
    	clearCache();
        return wpCheckinRewardMapper.deleteWpCheckinRewardByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除累计签到奖项信息
     * 
     * @param id 累计签到奖项ID
     * @return 结果
     */
    public int deleteWpCheckinRewardById(Integer id)
    {
    	clearCache();
        return wpCheckinRewardMapper.deleteWpCheckinRewardById(id);
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<WpCheckinReward> selectAll() {
		String key = RedisEnum.CHECKIN_REWARD_TOTAL.getKeyPrefix();
		BoundValueOperations<String, List<WpCheckinReward>> boundValueOps = this.redisTemplate.boundValueOps(key);
		List<WpCheckinReward> list = boundValueOps.get();
		if(CollectionUtils.isNotEmpty(list)){
			return list;
		}
		list = this.wpCheckinRewardMapper.selectWpCheckinRewardList(new WpCheckinReward());
		boundValueOps.set(list,1,TimeUnit.DAYS);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	private void clearCache(){
		String key = RedisEnum.CHECKIN_REWARD_TOTAL.getKeyPrefix();
    	this.redisTemplate.delete(key);
	}

	@Override
	public List<CheckinRewardUserResp> selectMyCheckinRewardList(Integer uid) {
		return this.wpCheckinRewardMapper.selectMyCheckinRewardList(uid);
	}
}
