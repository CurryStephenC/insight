package com.wjyoption.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.wjyoption.common.core.text.Convert;
import com.wjyoption.common.utils.db.RedisEnum;
import com.wjyoption.system.domain.WpProductdata;
import com.wjyoption.system.mapper.WpProductdataMapper;
import com.wjyoption.system.service.IWpProductdataService;
import com.wjyoption.system.vo.report.ProductKlineRedisVo;
import com.wjyoption.system.vo.report.ProductKlineVo;

/**
 * 产品数据Service业务层处理
 * 
 * @author wjyoption
 * @date 2021-07-10
 */
@Service
public class WpProductdataServiceImpl implements IWpProductdataService 
{
    @Autowired
    private WpProductdataMapper wpProductdataMapper;
    @SuppressWarnings("rawtypes")
	@Autowired RedisTemplate redisTemplate;

    /**
     * 查询产品数据
     * 
     * @param id 产品数据ID
     * @return 产品数据
     */
    @Override
    public WpProductdata selectWpProductdataById(Integer id)
    {
        return wpProductdataMapper.selectWpProductdataById(id);
    }

    /**
     * 查询产品数据列表
     * 
     * @param wpProductdata 产品数据
     * @return 产品数据
     */
    @Override
    public List<WpProductdata> selectWpProductdataList(WpProductdata wpProductdata)
    {
        return wpProductdataMapper.selectWpProductdataList(wpProductdata);
    }

    /**
     * 新增产品数据
     * 
     * @param wpProductdata 产品数据
     * @return 结果
     */
    @Override
    public int insertWpProductdata(WpProductdata wpProductdata)
    {
        return wpProductdataMapper.insertWpProductdata(wpProductdata);
    }

    /**
     * 修改产品数据
     * 
     * @param wpProductdata 产品数据
     * @return 结果
     */
    @Override
    public int updateWpProductdata(WpProductdata wpProductdata)
    {
        return wpProductdataMapper.updateWpProductdata(wpProductdata);
    }

    /**
     * 删除产品数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWpProductdataByIds(String ids)
    {
        return wpProductdataMapper.deleteWpProductdataByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除产品数据信息
     * 
     * @param id 产品数据ID
     * @return 结果
     */
    public int deleteWpProductdataById(Integer id)
    {
        return wpProductdataMapper.deleteWpProductdataById(id);
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductKlineVo> queryKlineData(Integer pid, String interval) {
		String keyPrefix = RedisEnum.PRODUCT_KLINE.getKeyPrefix();
		String key = keyPrefix + "_" + pid + "_" + interval;
		BoundValueOperations<String,ProductKlineRedisVo> boundValueOps = this.redisTemplate.boundValueOps(key);
		ProductKlineRedisVo redisVo = boundValueOps.get();
		if(redisVo == null){
			return null;
		}
		List<ProductKlineVo> list = redisVo.getData();
		return list;
	}

	@Override
	public WpProductdata selectWpProductdataByPid(Integer pid) {
		return this.wpProductdataMapper.selectWpProductdataByPid(pid);
	}

	@Override
	public int updateWpProductdataByPid(WpProductdata wpProductdata) {
		return this.wpProductdataMapper.updateWpProductdataByPid(wpProductdata);
	}
}
