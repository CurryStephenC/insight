package com.wjyoption.system.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wjyoption.system.mapper.WpCashFlowMapper;
import com.wjyoption.system.mapper.WpUserinfoMapper;
import com.wjyoption.system.domain.WpCashFlow;
import com.wjyoption.system.domain.WpUserinfo;
import com.wjyoption.system.service.IWpCashFlowService;
import com.wjyoption.system.vo.resp.CashFlowResp;
import com.wjyoption.common.core.text.Convert;

/**
 * 流水Service业务层处理
 * 
 * @author wjyoption
 * @date 2021-06-04
 */
@Service
public class WpCashFlowServiceImpl implements IWpCashFlowService 
{
    @Autowired
    private WpCashFlowMapper wpCashFlowMapper;
    @Autowired WpUserinfoMapper userinfoMapper;

    /**
     * 查询流水
     * 
     * @param id 流水ID
     * @return 流水
     */
    @Override
    public WpCashFlow selectWpCashFlowById(Integer id)
    {
        return wpCashFlowMapper.selectWpCashFlowById(id);
    }

    /**
     * 查询流水列表
     * 
     * @param wpCashFlow 流水
     * @return 流水
     */
    @Override
    public List<WpCashFlow> selectWpCashFlowList(WpCashFlow wpCashFlow)
    {
        return wpCashFlowMapper.selectWpCashFlowList(wpCashFlow);
    }

    /**
     * 新增流水
     * 
     * @param wpCashFlow 流水
     * @return 结果
     */
    @Override
    public int insertWpCashFlow(WpCashFlow wpCashFlow)
    {
    	if(StringUtils.isBlank(wpCashFlow.getNowmoney())){
    		WpUserinfo userinfo = this.userinfoMapper.selectWpUserinfoById(wpCashFlow.getUid());
    		wpCashFlow.setNowmoney(userinfo.getUsermoney().toString());
    	}
    	wpCashFlow.setTime(System.currentTimeMillis()/1000);
        return wpCashFlowMapper.insertWpCashFlow(wpCashFlow);
    }

    /**
     * 修改流水
     * 
     * @param wpCashFlow 流水
     * @return 结果
     */
    @Override
    public int updateWpCashFlow(WpCashFlow wpCashFlow)
    {
        return wpCashFlowMapper.updateWpCashFlow(wpCashFlow);
    }

    /**
     * 删除流水对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWpCashFlowByIds(String ids)
    {
        return wpCashFlowMapper.deleteWpCashFlowByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除流水信息
     * 
     * @param id 流水ID
     * @return 结果
     */
    public int deleteWpCashFlowById(Integer id)
    {
        return wpCashFlowMapper.deleteWpCashFlowById(id);
    }

	@Override
	public int insertWpCashFlow(Integer uid, Integer typeid, String money,
			String content, BigDecimal usermoney) {
		if(usermoney == null){
			WpUserinfo user = this.userinfoMapper.selectWpUserinfoById(uid);
			usermoney = user.getUsermoney();
		}
		WpCashFlow vo = new WpCashFlow();
		vo.setUid(uid);
		vo.setType(typeid);
		vo.setMoney(money);
		vo.setContent(content);
		vo.setNowmoney(usermoney.toString());
		vo.setTime(System.currentTimeMillis()/1000);
		return insertWpCashFlow(vo);
	}

	@Override
	public List<CashFlowResp> selectCashFlowList(CashFlowResp wpCashFlow) {
		if(StringUtils.isNotBlank(wpCashFlow.getExistsType()) && wpCashFlow.getNoType() == null){
			String[] noTypes = wpCashFlow.getExistsType().split(",");
			wpCashFlow.setNoType(new ArrayList<Integer>());
			for (int i = 0; i < noTypes.length; i++) {
				wpCashFlow.getNoType().add(Integer.valueOf(noTypes[i]));
			}
		}
		return this.wpCashFlowMapper.selectCashFlowList(wpCashFlow);
	}

	@Override
	public String selectTotalMoney(WpCashFlow flow) {
		return this.wpCashFlowMapper.selectTotalMoney(flow);
	}
}
