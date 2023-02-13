package com.wjyoption.system.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.wjyoption.common.core.text.Convert;
import com.wjyoption.common.utils.db.RedisEnum;
import com.wjyoption.system.domain.WpProductinfo;
import com.wjyoption.system.mapper.WpProductinfoMapper;
import com.wjyoption.system.service.IWpProductinfoService;
import com.wjyoption.system.vo.resp.ProductResp;

/**
 * 产品列表Service业务层处理
 * 
 * @author wjyoption
 * @date 2021-06-19
 */
@Service
public class WpProductinfoServiceImpl implements IWpProductinfoService 
{
	private static Logger logger = LoggerFactory.getLogger(WpProductinfoServiceImpl.class);
    @Autowired
    private WpProductinfoMapper wpProductinfoMapper;
    @SuppressWarnings("rawtypes")
	@Autowired RedisTemplate redisTemplate;

    /**
     * 查询产品列表
     * 
     * @param pid 产品列表ID
     * @return 产品列表
     */
    @Override
    public WpProductinfo selectWpProductinfoById(Integer pid)
    {
        return wpProductinfoMapper.selectWpProductinfoById(pid);
    }

    /**
     * 查询产品列表列表
     * 
     * @param wpProductinfo 产品列表
     * @return 产品列表
     */
    @Override
    public List<WpProductinfo> selectWpProductinfoList(WpProductinfo wpProductinfo)
    {
        return wpProductinfoMapper.selectWpProductinfoList(wpProductinfo);
    }

    /**
     * 新增产品列表
     * 
     * @param wpProductinfo 产品列表
     * @return 结果
     */
    @Override
    public int insertWpProductinfo(WpProductinfo wpProductinfo)
    {
        int count = wpProductinfoMapper.insertWpProductinfo(wpProductinfo);
        if(count > 0){
        	removeRedisAllProduct();
        }
        return count;
    }

    /**
     * 修改产品列表
     * 
     * @param wpProductinfo 产品列表
     * @return 结果
     */
    @Override
    public int updateWpProductinfo(WpProductinfo wpProductinfo)
    {
        int count = wpProductinfoMapper.updateWpProductinfo(wpProductinfo);
        if(count > 0){
        	removeRedisAllProduct();
        }
        return count;
    }

    /**
     * 删除产品列表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWpProductinfoByIds(String ids)
    {
        int count = wpProductinfoMapper.deleteWpProductinfoByIds(Convert.toStrArray(ids));
        if(count > 0){
        	removeRedisAllProduct();
        }
        return count;
    }

    /**
     * 删除产品列表信息
     * 
     * @param pid 产品列表ID
     * @return 结果
     */
    public int deleteWpProductinfoById(Long pid)
    {
        int count = wpProductinfoMapper.deleteWpProductinfoById(pid);
        if(count > 0){
        	removeRedisAllProduct();
        }
        return count;
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductResp> queryAllProduct() {
		String key = RedisEnum.PRODUCT_ALL_DATA.getKeyPrefix();
		BoundValueOperations<String,List<ProductResp>> boundValueOps = redisTemplate.boundValueOps(key);
		List<ProductResp> list = boundValueOps.get();
		if(list == null){
			list = this.wpProductinfoMapper.queryAllProduct();
			list.forEach(obj -> {
				if(obj.getIsopen() == 0){
					obj.setOpenFlag(false);
				}else{
					WpProductinfo product = new WpProductinfo();
					product.setOpentime(obj.getOpentime());
					product.setPid(obj.getPid());
					product.setIsopen(1);
					obj.setOpenFlag(canBuy(null, product));
				}
			});
			boundValueOps.set(list, 5, TimeUnit.MINUTES);
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	private void removeRedisAllProduct(){
		try {
			String key = RedisEnum.PRODUCT_ALL_DATA.getKeyPrefix();
			this.redisTemplate.delete(key);
		} catch (Exception e) {
			logger.error("removeRedisAllProduct error...",e);
		}
	}

	@Override
	public boolean canBuy(Integer pid, WpProductinfo product) {
		if(product == null && pid == null){
			logger.info("全部为空，返回false");
			return false;
		}
		if(product == null && pid != null){
			product = this.wpProductinfoMapper.selectWpProductinfoById(pid);
		}
		if(product.getIsopen() == 0){
			logger.info("isopen == 0,休市");
			return false;
		}
		Calendar calendar = Calendar.getInstance();
		int week = calendar.get(Calendar.DAY_OF_WEEK)-1;
		if(StringUtils.isBlank(product.getOpentime())){
			logger.info("开盘时间为空，休市");
			return false;
		}
		if(week == 0){
			week = 7;
		}
		String opentime = product.getOpentime().split("-", 7)[week-1];
		if(StringUtils.isBlank(opentime)){
			logger.info("当天开盘时间为空，休市》" + week + "|" + product.getOpentime());
			return false;
		}
//		00:00~02:25|04:40~24:00
		String[] times = opentime.split("\\|");
//		System.out.println(opentime);
//		System.out.println(times);
		Date now = calendar.getTime();
		Calendar calendar_end = Calendar.getInstance();
		for(String beginEnd : times){
			String[] first = beginEnd.split("~")[0].split(":");
			String[] end = beginEnd.split("~")[1].split(":");
			calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(first[0]));
			calendar.set(Calendar.MINUTE, Integer.valueOf(first[1]));
			calendar.set(Calendar.SECOND, 0);
			
			calendar_end.set(Calendar.HOUR_OF_DAY, Integer.valueOf(end[0]));
			calendar_end.set(Calendar.MINUTE, Integer.valueOf(end[1]));
			calendar_end.set(Calendar.SECOND, 0);
			if(now.after(calendar.getTime()) && now.before(calendar_end.getTime())){
				return true;
			}
		}
		return false;
	}
	public static void main(String[] args) {
//		Calendar calendar = Calendar.getInstance();
//		int week = calendar.get(Calendar.WEEK_OF_YEAR)-1;
//		System.out.println("00:00~02:25".split("~")[0]);
//		System.out.println("00:00~02:25|04:40~24:00".split("\\|")[1]);
//		String opentime = "00:00~02:25|14:40~14:45";
		Calendar calendar = Calendar.getInstance();
		int week = calendar.get(Calendar.DAY_OF_WEEK)-1;
		if(week == 0){
			week = 7;
		}
		System.out.println(week-1);
		System.out.println("04:40~23:00-04:40~24:00-00:00~02:25|04:40~24:00-00:00~02:25|04:40~24:00-00:00~02:25|04:40~24:00-00:00~02:00--".split("-",7).length);
		String opentime = "04:40~23:00-14:50~24:00-00:00~02:25|04:40~24:00-00:00~02:25|04:40~24:00-00:00~02:25|04:40~24:00-00:00~02:00--".split("-", 7)[week-1];
		String[] times = opentime.split("\\|");
		Date now = calendar.getTime();
		Calendar calendar_end = Calendar.getInstance();
		for(String beginEnd : times){
			String[] first = beginEnd.split("~")[0].split(":");
			String[] end = beginEnd.split("~")[1].split(":");
			calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(first[0]));
			calendar.set(Calendar.MINUTE, Integer.valueOf(first[1]));
			calendar.set(Calendar.SECOND, 0);
			
			calendar_end.set(Calendar.HOUR_OF_DAY, Integer.valueOf(end[0]));
			calendar_end.set(Calendar.MINUTE, Integer.valueOf(end[1]));
			calendar_end.set(Calendar.SECOND, 0);
			if(now.after(calendar.getTime()) && now.before(calendar_end.getTime())){
				System.out.println("true");
				return;
			}
		}
		System.out.println("fail");
	}
}
