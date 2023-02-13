package com.wjyoption.system.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wjyoption.common.core.domain.Ztree;
import com.wjyoption.common.core.text.Convert;
import com.wjyoption.common.utils.DateUtils;
import com.wjyoption.common.utils.db.RedisEnum;
import com.wjyoption.common.utils.security.Md5Util;
import com.wjyoption.common.vo.UserLogin;
import com.wjyoption.system.domain.CodepayOrder;
import com.wjyoption.system.domain.WpConfig;
import com.wjyoption.system.domain.WpFinancialRecord;
import com.wjyoption.system.domain.WpUserinfo;
import com.wjyoption.system.domain.WpuserinfoLow;
import com.wjyoption.system.mapper.WpUserinfoMapper;
import com.wjyoption.system.service.ICodepayOrderService;
import com.wjyoption.system.service.IWpCashFlowService;
import com.wjyoption.system.service.IWpConfigService;
import com.wjyoption.system.service.IWpFinancialRecordService;
import com.wjyoption.system.service.IWpUserinfoService;
import com.wjyoption.system.vo.resp.ChildListResp;

/**
 * 前端用户Service业务层处理
 * 
 * @author hs
 * @date 2021-06-03
 */
@Service
public class WpUserinfoServiceImpl implements IWpUserinfoService 
{
	private static Logger logger = LoggerFactory.getLogger(WpUserinfoServiceImpl.class);
    @Autowired
    private WpUserinfoMapper wpUserinfoMapper;
    @SuppressWarnings("rawtypes")
	@Autowired RedisTemplate redisTemplate;
    @Autowired IWpFinancialRecordService recordService;
    @Autowired IWpCashFlowService cashFlowService;
    @Autowired IWpConfigService configService;
    @Autowired ICodepayOrderService codepayOrderService;
    

    /**
     * 查询前端用户
     * 
     * @param uid 前端用户ID
     * @return 前端用户
     */
    @Override
    public WpUserinfo selectWpUserinfoById(Integer uid)
    {
        return wpUserinfoMapper.selectWpUserinfoById(uid);
    }

    /**
     * 查询前端用户列表
     * 
     * @param wpUserinfo 前端用户
     * @return 前端用户
     */
    @Override
    public List<WpUserinfo> selectWpUserinfoList(WpUserinfo wpUserinfo)
    {
        return wpUserinfoMapper.selectWpUserinfoList(wpUserinfo);
    }

    /**
     * 新增前端用户
     * 
     * @param wpUserinfo 前端用户
     * @return 结果
     */
    @Override
    public int insertWpUserinfo(WpUserinfo wpUserinfo)
    {
        return wpUserinfoMapper.insertWpUserinfo(wpUserinfo);
    }

    /**
     * 修改前端用户
     * 
     * @param wpUserinfo 前端用户
     * @return 结果
     */
    @Override
    public int updateWpUserinfo(WpUserinfo wpUserinfo)
    {
    	if(StringUtils.isNotBlank(wpUserinfo.getUpwd()) && wpUserinfo.getUpwd().length() != 32){
    		WpUserinfo userinfo = this.wpUserinfoMapper.selectWpUserinfoById(wpUserinfo.getUid());
    		wpUserinfo.setUpwd(Md5Util.MD5(wpUserinfo.getUpwd() + userinfo.getUtime()));
    	}
        return wpUserinfoMapper.updateWpUserinfo(wpUserinfo);
    }

    /**
     * 删除前端用户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWpUserinfoByIds(String ids)
    {
        return wpUserinfoMapper.deleteWpUserinfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除前端用户信息
     * 
     * @param uid 前端用户ID
     * @return 结果
     */
    public int deleteWpUserinfoById(Integer uid)
    {
        return wpUserinfoMapper.deleteWpUserinfoById(uid);
    }

	@Override
	public WpUserinfo selectUserByPhone(String phone) {
		return wpUserinfoMapper.selectUserByPhone(phone);
	}

	@Override
	public int updateMoney(BigDecimal money,Integer uid) {
		return updateMoney(money, uid, null);
	}
	@SuppressWarnings("unchecked")
	@Override
	public int updateMoney(BigDecimal money,Integer uid,Integer newtask) {
		WpUserinfo userinfo = this.wpUserinfoMapper.selectWpUserinfoById(uid);
		ValueOperations<String,String> opsForValue = redisTemplate.opsForValue();
		String key = RedisEnum.USERID_LOGIN.getKeyPrefix() + uid;
		String login_key = opsForValue.get(key);
		WpUserinfo user = new WpUserinfo();
		user.setUid(uid);
		user.setUsermoney(money);
		if(newtask != null){
			user.setNewtask(newtask);
		}
		int count = this.wpUserinfoMapper.updateMoney(user);
		if(count > 0 && StringUtils.isNotBlank(login_key) && redisTemplate.hasKey(login_key)){
			ValueOperations<String, UserLogin> opsForValue2 = redisTemplate.opsForValue();
			UserLogin userLogin = opsForValue2.get(login_key);
			userLogin.setWallet(userinfo.getUsermoney().add(money));
			if(newtask != null){
				userLogin.setNewtask(newtask);
			}
			updateUserToRedis(userLogin);
		}
		return count;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void updateUserToRedis(UserLogin user){
		String key = RedisEnum.USER_LOGIN.getKeyPrefix() + user.getToken();
		ValueOperations<String, UserLogin> opsForValue = redisTemplate.opsForValue();
		if(opsForValue.get(key) != null){
			opsForValue.set(key, user, 0);
		}else{
			opsForValue.set(key, user, 14, TimeUnit.DAYS);
		}
		ValueOperations<String,String> opsForValue2 = redisTemplate.opsForValue();
		String key2 = RedisEnum.USERID_LOGIN.getKeyPrefix() + user.getUid();
//		if(opsForValue2.get(key2) != null){
//			opsForValue2.set(key2, key,0);
//		}else{
			opsForValue2.set(key2, key,14,TimeUnit.DAYS);
//		}
	}

	@Override
	public List<ChildListResp> selectChildList(ChildListResp wpUserinfo) {
		return this.wpUserinfoMapper.selectChildList(wpUserinfo);
	}

	@Override
	@Transactional
	public void updateIbStatus() {
		String totalMoney = this.configService.selectWpConfigByKey("wp_ib_totalmoney");
//		String totalRecharge = this.configService.selectWpConfigByKey("wp_ib_totalrecharge");
		
		BigDecimal ib_total_money = new BigDecimal(StringUtils.defaultIfBlank(totalMoney, "30000"));//IB用户自身购买理财条件
//		BigDecimal ib_total_recharge = new BigDecimal(StringUtils.defaultIfBlank(totalRecharge, "30000"));//IB用户充值必须大于30000
		this.wpUserinfoMapper.updateAllNoib();
		WpFinancialRecord wpFinancialRecord = new WpFinancialRecord();
		wpFinancialRecord.setEndtime(DateUtils.getNowSecond());
		wpFinancialRecord.setBuymoney(ib_total_money);
		wpFinancialRecord.setStatus(0);
		List<WpFinancialRecord> list = this.recordService.selectIbRecordList(wpFinancialRecord);
		if(CollectionUtils.isEmpty(list)){
			logger.info("没有IB身份可以更新");
			return;
		}
		int recordnum = 0;
		for(WpFinancialRecord record : list){
//			WpCashFlow flow = new WpCashFlow();
//			flow.setUid(record.getUid());
//			List<Integer> types = new ArrayList<Integer>();
//			types.add(6);
//			flow.setTypes(types);
//			String balanceMoney = this.cashFlowService.selectTotalMoney(flow);
//			BigDecimal balance = new BigDecimal(0);
//			if(StringUtils.isNotBlank(balanceMoney)){
//				balance = new BigDecimal(balanceMoney);
//			}
//			types = new ArrayList<Integer>();
//			types.add(5);
//			flow.setTypes(types);
//			String rechargeMoney = this.cashFlowService.selectTotalMoney(flow);
//			BigDecimal recharge = new BigDecimal(0);
//			if(StringUtils.isNotBlank(rechargeMoney)){
//				recharge = new BigDecimal(rechargeMoney);
//			}
//			if(balance.add(recharge).compareTo(ib_total_recharge) >= 0){
				WpUserinfo userinfo = this.wpUserinfoMapper.selectWpUserinfoById(record.getUid());
				//如果是VIP-IB则无需更新IB身份
				if(userinfo.getIbstatus() == 2){
					continue;
				}
				WpUserinfo u = new WpUserinfo();
				u.setUid(record.getUid());
				u.setIbstatus(1);
				if(userinfo.getIbtime() == null){
					u.setIbtime(DateUtils.getNowSecond());
				}
				this.wpUserinfoMapper.updateWpUserinfo(u);
				recordnum++;
//			}
		}
		logger.info("ib身份数量：" + recordnum);
	}

	@Override
	public List<Ztree> selectUserinfoTree(WpUserinfo wpUserinfo) {
		List<WpUserinfo> list = this.wpUserinfoMapper.selectWpUserinfoList(wpUserinfo);
		List<Ztree> treeList = new ArrayList<>();
		for(WpUserinfo user : list){
			if(user.getOid() == null) continue;
			Ztree tree = new Ztree();
			tree.setId(user.getUid().longValue());
			tree.setName(user.getUtel());
			tree.setTitle(user.getUtel());
			tree.setpId(user.getOid().longValue());
			treeList.add(tree);
		}
		return treeList;
	}

	@Override
	public void freeze(Integer uid) {
		WpUserinfo wpUserinfo = new WpUserinfo();
		wpUserinfo.setUid(uid);
		wpUserinfo.setUstatus(1);
		this.wpUserinfoMapper.updateWpUserinfo(wpUserinfo);
		offline(uid);
//		freezeDown(uid);
		
		WpUserinfo params = new WpUserinfo();
		params.setOid(uid);
		List<WpUserinfo> list = this.wpUserinfoMapper.selectWpUserinfoList(params);
		if(list.size() == 0) return;
		for(WpUserinfo u : list){
			freeze(u.getUid());
		}
	}
	
	@Override
	public int updateUserStatus(Integer uid, Integer ustatus) {
		WpUserinfo user = new WpUserinfo();
		user.setUid(uid);
		user.setUstatus(ustatus);
		int count = this.wpUserinfoMapper.updateWpUserinfo(user);
		if(count > 0 && ustatus == 1){
			offline(uid);
		}
		return count;
	}
	
	@SuppressWarnings("unchecked")
	public void offline(Integer uid){
		String key = RedisEnum.USERID_LOGIN.getKeyPrefix() + uid;
		BoundValueOperations<String,String> boundValueOps = this.redisTemplate.boundValueOps(key);
		String userKey = boundValueOps.get();
		if(StringUtils.isNotBlank(userKey) && this.redisTemplate.hasKey(userKey)){
			this.redisTemplate.delete(key);
			this.redisTemplate.delete(userKey);
		}
	}

	@Override
	public List<Ztree> selectUserinfoTree(Integer oid) {
		List<Ztree> list = this.wpUserinfoMapper.selectUserinfoTree(oid);
		for(Ztree z : list){
			CodepayOrder codepayOrder = new CodepayOrder();
			codepayOrder.setStatus(1);
			codepayOrder.setPayId(z.getId().intValue());
			List<CodepayOrder> payList = this.codepayOrderService.selectCodepayOrderList(codepayOrder);
			if(payList == null || payList.size() == 0){
				z.setName(z.getName()+"/0");
			}else{
				BigDecimal payMoney = new BigDecimal(0);
				for (CodepayOrder obj : payList) {
					payMoney = payMoney.add(obj.getMoney());
				}
				z.setName(z.getName() + "/" + payMoney.intValue());
			}
			
			WpFinancialRecord wpFinancialRecord = new WpFinancialRecord();
			wpFinancialRecord.setStatus(0);
			wpFinancialRecord.setUid(codepayOrder.getPayId());
			List<WpFinancialRecord> recordList = this.recordService.selectWpFinancialRecordList(wpFinancialRecord);
			if(recordList == null || recordList.size() == 0){
				z.setName(z.getName()+"/0</i>");
			}else{
				BigDecimal recordMoney = new BigDecimal(0);
				for(WpFinancialRecord record : recordList){
					recordMoney = recordMoney.add(record.getBuymoney());
				}
				z.setName(z.getName() + "/" + recordMoney.intValue() + "</i>");
			}
			z.setName(z.getName().split(" ")[0] + " <i style=\"color:red\">" + z.getName().split(" ")[1]);
		}
		return list;
	}
	
	/**
     * 指定日期数量
     * @param uids
     * @return
     */
    public int selectDailyCount(WpUserinfo daily){
    	return this.wpUserinfoMapper.selectDailyCount(daily);
    }

	@Override
	@Transactional
	public void updateOrderPrice() {
		this.wpUserinfoMapper.setAllNoOrderPrice();
		WpFinancialRecord wpFinancialRecord = new WpFinancialRecord();
		wpFinancialRecord.setStatus(0);
		wpFinancialRecord.setIsgive(0);
		List<WpFinancialRecord> list = this.recordService.selectWpFinancialRecordList(wpFinancialRecord);
		Map<Integer, BigDecimal> map = new HashMap<Integer, BigDecimal>();
		for(WpFinancialRecord record : list){
			Integer uid = record.getUid();
			if(map.containsKey(uid)){
				map.put(uid, map.get(uid).add(record.getBuymoney()));
			}else{
				map.put(uid, record.getBuymoney());
			}
		}
		Set<Integer> keySet = map.keySet();
		WpConfig config = this.configService.selectWpConfigBeanByKey("order_singleprice_percent_financial");
		BigDecimal percent = BigDecimal.valueOf(0.01);
		if(config != null && config.getStatus() != 0 && StringUtils.isBlank(config.getValue())){
			percent = new BigDecimal(config.getValue());
		}
		for(Integer key : keySet){
			WpUserinfo info = new WpUserinfo();
			info.setUid(key);
			info.setOrdermaxprice(map.get(key).multiply(percent));
			this.wpUserinfoMapper.updateWpUserinfo(info);
		}
	}

	@Override
	public List<WpuserinfoLow> exportUserinfoTree(Integer oid) {
		List<WpuserinfoLow> ret = new ArrayList<>();
		List<Ztree> list = this.wpUserinfoMapper.selectUserinfoTree(oid);
		for(Ztree z : list){
			String[] strs = z.getName().split(" ");
			WpuserinfoLow low = new WpuserinfoLow();
			low.setUtel(strs[0]);
			low.setUsermoney(new BigDecimal(strs[1]));
			
			CodepayOrder codepayOrder = new CodepayOrder();
			codepayOrder.setStatus(1);
			codepayOrder.setPayId(z.getId().intValue());
			List<CodepayOrder> payList = this.codepayOrderService.selectCodepayOrderList(codepayOrder);
			if(payList == null || payList.size() == 0){
				low.setRechargemoney(new BigDecimal(0));
			}else{
				BigDecimal payMoney = new BigDecimal(0);
				for (CodepayOrder obj : payList) {
					payMoney = payMoney.add(obj.getMoney());
				}
				low.setRechargemoney(payMoney);
			}
			
			WpFinancialRecord wpFinancialRecord = new WpFinancialRecord();
			wpFinancialRecord.setStatus(0);
			wpFinancialRecord.setUid(codepayOrder.getPayId());
			List<WpFinancialRecord> recordList = this.recordService.selectWpFinancialRecordList(wpFinancialRecord);
			if(recordList == null || recordList.size() == 0){
				low.setFinancialmoney(new BigDecimal(0));
			}else{
				BigDecimal recordMoney = new BigDecimal(0);
				for(WpFinancialRecord record : recordList){
					recordMoney = recordMoney.add(record.getBuymoney());
				}
				low.setFinancialmoney(recordMoney);
			}
			ret.add(low);
		}
		return ret;
	}
}
