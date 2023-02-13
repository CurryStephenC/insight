package com.wjyoption.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.wjyoption.common.core.text.Convert;
import com.wjyoption.common.utils.DateUtils;
import com.wjyoption.common.utils.db.RedisEnum;
import com.wjyoption.system.domain.WpConfig;
import com.wjyoption.system.mapper.WpConfigMapper;
import com.wjyoption.system.service.IWpConfigService;

/**
 * 系统配置Service业务层处理
 * 
 * @author wjyoption
 * @date 2021-06-08
 */
@Service
public class WpConfigServiceImpl implements IWpConfigService 
{
    @Autowired
    private WpConfigMapper wpConfigMapper;
    
    @SuppressWarnings("rawtypes")
	@Autowired RedisTemplate redisTemplate;

    /**
     * 查询系统配置
     * 
     * @param id 系统配置ID
     * @return 系统配置
     */
    @Override
    public WpConfig selectWpConfigById(Integer id)
    {
        return wpConfigMapper.selectWpConfigById(id);
    }

    /**
     * 查询系统配置列表
     * 
     * @param wpConfig 系统配置
     * @return 系统配置
     */
    @Override
    public List<WpConfig> selectWpConfigList(WpConfig wpConfig)
    {
        return wpConfigMapper.selectWpConfigList(wpConfig);
    }

    /**
     * 新增系统配置
     * 
     * @param wpConfig 系统配置
     * @return 结果
     */
    @Override
    public int insertWpConfig(WpConfig wpConfig)
    {
        wpConfig.setCreateTime(DateUtils.getNowSecond());
        int count = wpConfigMapper.insertWpConfig(wpConfig);
        updateConfigRedis();
        return count;
    }

    /**
     * 修改系统配置
     * 
     * @param wpConfig 系统配置
     * @return 结果
     */
    @Override
    public int updateWpConfig(WpConfig wpConfig)
    {
        wpConfig.setUpdateTime(DateUtils.getNowSecond());
        int count = wpConfigMapper.updateWpConfig(wpConfig);
        updateConfigRedis();
        return count;
    }

    /**
     * 删除系统配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWpConfigByIds(String ids)
    {
        int count = wpConfigMapper.deleteWpConfigByIds(Convert.toStrArray(ids));
        updateConfigRedis();
        return count;
    }

    /**
     * 删除系统配置信息
     * 
     * @param id 系统配置ID
     * @return 结果
     */
    public int deleteWpConfigById(Integer id)
    {
        int count = wpConfigMapper.deleteWpConfigById(id);
        updateConfigRedis();
        return count;
    }
    
    @SuppressWarnings("unchecked")
	public void updateConfigRedis(){
    	List<WpConfig> configList = this.wpConfigMapper.selectWpConfigList(null);
    	String key = RedisEnum.WP_CONFG_KEY.getKeyPrefix();
    	ValueOperations<String,Map<String, WpConfig>> opsForValue = this.redisTemplate.opsForValue();
    	Map<String, WpConfig> map = new HashMap<String, WpConfig>();
		for(WpConfig config : configList){
			map.put(config.getName(), config);
		}
		opsForValue.set(key, map,1,TimeUnit.DAYS);
    }

	@SuppressWarnings("unchecked")
	@Override
	public String selectWpConfigByKey(String name) {
		String key = RedisEnum.WP_CONFG_KEY.getKeyPrefix();
		ValueOperations<String,Map<String, WpConfig>> opsForValue = this.redisTemplate.opsForValue();
		Map<String, WpConfig> map = opsForValue.get(key);
		if(map != null && map.containsKey(name)){
			return map.get(name).getValue();
		}
		WpConfig config = this.wpConfigMapper.selectWpConfigByKey(name);
		if(config != null){
			updateConfigRedis();
			return config.getValue();
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public WpConfig selectWpConfigBeanByKey(String name) {
		String key = RedisEnum.WP_CONFG_KEY.getKeyPrefix();
		ValueOperations<String,Map<String, WpConfig>> opsForValue = this.redisTemplate.opsForValue();
		Map<String, WpConfig> map = opsForValue.get(key);
		if(map != null && map.containsKey(name)){
			return map.get(name);
		}
		WpConfig config = this.wpConfigMapper.selectWpConfigByKey(name);
		if(config != null){
			updateConfigRedis();
			return config;
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, WpConfig> selectAll(){
		String key = RedisEnum.WP_CONFG_KEY.getKeyPrefix();
		ValueOperations<String,Map<String, WpConfig>> opsForValue = this.redisTemplate.opsForValue();
		Map<String, WpConfig> map = opsForValue.get(key);
		if(map != null){
			return map;
		}
		List<WpConfig> list = this.wpConfigMapper.selectWpConfigList(new WpConfig());
		if(list != null && list.size() > 0){
			map = new HashMap<String, WpConfig>();
			for(WpConfig config : list){
				map.put(config.getName(), config);
			}
			opsForValue.set(key, map,2,TimeUnit.HOURS);
			return map;
		}
		return null;
	}
}
