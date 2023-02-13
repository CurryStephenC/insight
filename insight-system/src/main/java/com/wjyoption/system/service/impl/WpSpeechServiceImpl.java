package com.wjyoption.system.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wjyoption.system.mapper.WpSpeechMapper;
import com.wjyoption.system.domain.WpSpeech;
import com.wjyoption.system.domain.WpSpeechLike;
import com.wjyoption.system.domain.WpUserinfo;
import com.wjyoption.system.service.ISysConfigService;
import com.wjyoption.system.service.IWpCashFlowService;
import com.wjyoption.system.service.IWpPriceLogService;
import com.wjyoption.system.service.IWpSpeechLikeService;
import com.wjyoption.system.service.IWpSpeechService;
import com.wjyoption.system.service.IWpUserinfoService;
import com.wjyoption.system.vo.resp.SpeechResp;
import com.wjyoption.common.core.domain.Response;
import com.wjyoption.common.core.text.Convert;
import com.wjyoption.common.enums.ErrorConstants;
import com.wjyoption.common.utils.DateUtils;
import com.wjyoption.common.utils.ThreadLocals;
import com.wjyoption.common.vo.UserLogin;

/**
 * 言论Service业务层处理
 * 
 * @author wjyoption
 * @date 2021-06-15
 */
@Service
public class WpSpeechServiceImpl implements IWpSpeechService 
{
    @Autowired
    private WpSpeechMapper wpSpeechMapper;
    @Autowired IWpSpeechLikeService likeService;
    @Autowired ISysConfigService configService;
    @Autowired IWpPriceLogService priceLogService;
    @Autowired IWpCashFlowService cashFlowService;
    @Autowired IWpUserinfoService userinfoService;

    /**
     * 查询言论
     * 
     * @param id 言论ID
     * @return 言论
     */
    @Override
    public WpSpeech selectWpSpeechById(Integer id)
    {
        return wpSpeechMapper.selectWpSpeechById(id);
    }

    /**
     * 查询言论列表
     * 
     * @param wpSpeech 言论
     * @return 言论
     */
    @Override
    public List<WpSpeech> selectWpSpeechList(WpSpeech wpSpeech)
    {
        return wpSpeechMapper.selectWpSpeechList(wpSpeech);
    }

    /**
     * 新增言论
     * 
     * @param wpSpeech 言论
     * @return 结果
     */
    @Override
    public int insertWpSpeech(WpSpeech wpSpeech)
    {
    	if(wpSpeech.getCreatetime() == null){
    		wpSpeech.setCreatetime(DateUtils.getNowSecond());
    	}
        return wpSpeechMapper.insertWpSpeech(wpSpeech);
    }

    /**
     * 修改言论
     * 
     * @param wpSpeech 言论
     * @return 结果
     */
    @Override
    public int updateWpSpeech(WpSpeech wpSpeech)
    {
    	WpSpeech bean = this.wpSpeechMapper.selectWpSpeechById(wpSpeech.getId());
    	if(bean.getStatus() != 0 && wpSpeech.getStatus() != 0){
    		return 0;
    	}
    	int count = wpSpeechMapper.updateWpSpeech(wpSpeech);
    	if(wpSpeech.getStatus() == 1){
    		BigDecimal reward = new BigDecimal(5);
    		WpUserinfo userinfo = this.userinfoService.selectWpUserinfoById(bean.getUid());
    		this.userinfoService.updateMoney(reward, bean.getUid());
    		this.priceLogService.insertWpPriceLog(bean.getUid(), reward.toString(), 1, "Community", "Speech reward", bean.getId(), userinfo.getUsermoney().add(reward));
    		this.cashFlowService.insertWpCashFlow(bean.getUid(), 13, reward.toString(), "言论红包", userinfo.getUsermoney().add(reward));
    	}
        return count;
    }

    /**
     * 删除言论对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWpSpeechByIds(String ids)
    {
        return wpSpeechMapper.deleteWpSpeechByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除言论信息
     * 
     * @param id 言论ID
     * @return 结果
     */
    public int deleteWpSpeechById(Integer id)
    {
        return wpSpeechMapper.deleteWpSpeechById(id);
    }

	@Override
	public List<SpeechResp> selectSpeechResp(WpSpeech speech) {
		List<SpeechResp> list = this.wpSpeechMapper.selectSpeechResp(speech);
		UserLogin user = ThreadLocals.getUser();
		if(user == null){
			return list;
		}
		WpSpeechLike wpSpeechLike = null;
		for(SpeechResp bean : list){
			wpSpeechLike = new WpSpeechLike();
			wpSpeechLike.setUid(user.getUid());
			wpSpeechLike.setType(1);
			wpSpeechLike.setRefid(bean.getId());
			WpSpeechLike like = this.likeService.selectUserLike(wpSpeechLike);
			if(like != null){
				bean.setLike(1);
			}
		}
		return list;
	}

	@Override
	public void updateNum(Integer refid, Integer likeNum,Integer commentNum) {
		WpSpeech speech = new WpSpeech();
		speech.setId(refid);
		if(likeNum != null && likeNum != 0){
			speech.setLikenum(likeNum);
		}
		if(commentNum != null && commentNum != 0){
			speech.setCommentnum(commentNum);
		}
		this.wpSpeechMapper.increaseOrDecrease(speech);
	}

	@Override
	public void releaseSpeech(String content, Response<Object> response) {
		String recordTotal = this.configService.selectConfigByKey("wp_speech_record_daily");
		System.out.println(StringUtils.defaultIfBlank(recordTotal, "3"));
		int records = Integer.parseInt(StringUtils.defaultIfBlank(recordTotal, "3"));
		String dateTimeNow = DateUtils.dateTimeNow(DateUtils.YYYYMMDD);
		Integer daily = Integer.valueOf(dateTimeNow);
		UserLogin user = ThreadLocals.getUser();
		WpSpeech param = new WpSpeech();
		param.setUid(user.getUid());
		param.setDaily(daily);
		List<WpSpeech> list = this.wpSpeechMapper.selectWpSpeechList(param);
		if(CollectionUtils.size(list) >= records){
			ErrorConstants.setResponse(response, ErrorConstants.REACHED_LIMIT);
			return;
		}
		param.setPhone(user.getUtel().substring(0,3) + "****" + user.getUtel().substring(user.getUtel().length() - 3));
		param.setPicurl(user.getUserphoto());
		param.setContent(content);
		param.setCreatetime(DateUtils.getNowSecond());
		this.insertWpSpeech(param);
	}
}
