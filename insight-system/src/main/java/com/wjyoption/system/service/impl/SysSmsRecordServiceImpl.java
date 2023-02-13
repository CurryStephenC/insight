package com.wjyoption.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.wjyoption.common.constant.Constants;
import com.wjyoption.common.core.domain.Response;
import com.wjyoption.common.enums.ErrorConstants;
import com.wjyoption.common.utils.DateUtils;
import com.wjyoption.common.utils.db.RedisEnum;
import com.wjyoption.common.utils.sms.SmsUtil;
import com.wjyoption.common.utils.sms.vo.SmsResp;
import com.wjyoption.system.domain.SysSmsRecord;
import com.wjyoption.system.mapper.SysSmsRecordMapper;
import com.wjyoption.system.service.ISysSmsRecordService;

/**
 * 短信发送记录Service业务层处理
 * 
 * @author hs
 * @date 2020-04-29
 */
@Service
public class SysSmsRecordServiceImpl implements ISysSmsRecordService 
{
    @Autowired
    private SysSmsRecordMapper sysSmsRecordMapper;
    @Resource RedisTemplate<String, List<Long>> redisTemplate;
    @Value("${wjyoption.sms.source}")
    private String source;
    
    @Value("${wjyoption.sms.webSite}")
    private String webSite;

    /**
     * 查询短信发送记录
     * 
     * @param id 短信发送记录ID
     * @return 短信发送记录
     */
    @Override
    public SysSmsRecord selectSysSmsRecordById(Integer id)
    {
        return sysSmsRecordMapper.selectSysSmsRecordById(id);
    }

    /**
     * 查询短信发送记录列表
     * 
     * @param sysSmsRecord 短信发送记录
     * @return 短信发送记录
     */
    @Override
    public List<SysSmsRecord> selectSysSmsRecordList(SysSmsRecord sysSmsRecord)
    {
        return sysSmsRecordMapper.selectSysSmsRecordList(sysSmsRecord);
    }

    /**
     * 新增短信发送记录
     * 
     * @param sysSmsRecord 短信发送记录
     * @return 结果
     */
    @Override
    public int insertSysSmsRecord(SysSmsRecord sysSmsRecord)
    {
        sysSmsRecord.setCreateTime(DateUtils.getNowDate());
        String content = sysSmsRecord.getContent();
        if(StringUtils.isNotBlank(sysSmsRecord.getSensitive())){
        	content = String.format(content, JSON.parseArray(sysSmsRecord.getSensitive()).toArray());
        }
        SmsResp resp = SmsUtil.mtSend(content, "91" + sysSmsRecord.getPhonenum());
        if("0".equals(resp.getCode())){
    		sysSmsRecord.setSendstate(2);
    	}else{
    		sysSmsRecord.setSendstate(3);
    	}
    	sysSmsRecord.setResultdata(resp.getResultData());
    	sysSmsRecord.setChannel(resp.getSmschannel());
        return sysSmsRecordMapper.insertSysSmsRecord(sysSmsRecord);
    }
    

	@Override
	public void sendMsg(Response<?> response,String template,String areaCode, String phone,String source,Integer smstype,
			Object... sensitive) {
		if(!checkSend(phone,true)){
			ErrorConstants.setResponse(response, ErrorConstants.VERIFY_SENDMUCH);
			return;
		}
		SysSmsRecord sysSmsRecord = new SysSmsRecord();
		sysSmsRecord.setContent(template);
		sysSmsRecord.setPhonenum(areaCode + phone);
		sysSmsRecord.setSmstype(smstype);
		sysSmsRecord.setSource(source);
		if(sensitive != null && sensitive.length > 0){
			sysSmsRecord.setSensitive(JSON.toJSONString(sensitive));
		}
		this.insertSysSmsRecord(sysSmsRecord);
	}


	//记录超过5次则无法再次发送
	private boolean checkSend(String mobile,boolean add){
		ValueOperations<String, List<Long>> value = this.redisTemplate.opsForValue();
		String key = RedisEnum.PREFIX_SMS_SEND_TOTAL.getKeyPrefix() + mobile;
		List<Long> list = value.get(key);
		if(CollectionUtils.isEmpty(list) || list.size() < Constants.SMS_SEND_COUNT){
			if(add) totalStatistics(mobile);
			return true;
		}
		long nowTime = System.currentTimeMillis();
		for(int i = 0;i < list.size();i++){
			Long time = list.get(i);
			if(nowTime > time){
				list.remove(i);
				i--;
			}
		}
		value.set(key, list,1,TimeUnit.HOURS);
		if(list.size() < Constants.SMS_SEND_COUNT){
			if(add) totalStatistics(mobile);
			return true;
		}
		return false;
	}
	private void totalStatistics(String mobile) {
		ValueOperations<String, List<Long>> value = this.redisTemplate.opsForValue();
		String key = RedisEnum.PREFIX_SMS_SEND_TOTAL.getKeyPrefix() + mobile;
		List<Long> list = value.get(key);
		if(CollectionUtils.isEmpty(list)){
			list = new ArrayList<>();
		}
		list.add(System.currentTimeMillis() + Constants.SMS_HOURSEND);
		value.set(key, list,1,TimeUnit.HOURS);
	}
}
