package com.wjyoption.system.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wjyoption.system.mapper.WpPriceLogMapper;
import com.wjyoption.system.mapper.WpUserinfoMapper;
import com.wjyoption.system.domain.WpPriceLog;
import com.wjyoption.system.domain.WpUserinfo;
import com.wjyoption.system.service.IWpPriceLogService;
import com.wjyoption.system.vo.resp.PriceLogResp;
import com.wjyoption.common.core.text.Convert;

/**
 * 资金日志Service业务层处理
 * 
 * @author wjyoption
 * @date 2021-06-05
 */
@Service
public class WpPriceLogServiceImpl implements IWpPriceLogService 
{
    @Autowired
    private WpPriceLogMapper wpPriceLogMapper;
    
    @Autowired WpUserinfoMapper userinfoMapper;

    /**
     * 查询资金日志
     * 
     * @param id 资金日志ID
     * @return 资金日志
     */
    @Override
    public WpPriceLog selectWpPriceLogById(Integer id)
    {
        return wpPriceLogMapper.selectWpPriceLogById(id);
    }

    /**
     * 查询资金日志列表
     * 
     * @param wpPriceLog 资金日志
     * @return 资金日志
     */
    @Override
    public List<WpPriceLog> selectWpPriceLogList(WpPriceLog wpPriceLog)
    {
        return wpPriceLogMapper.selectWpPriceLogList(wpPriceLog);
    }

    /**
     * 新增资金日志
     * 
     * @param wpPriceLog 资金日志
     * @return 结果
     */
    @Override
    public int insertWpPriceLog(WpPriceLog wpPriceLog)
    {
        return wpPriceLogMapper.insertWpPriceLog(wpPriceLog);
    }

    /**
     * 修改资金日志
     * 
     * @param wpPriceLog 资金日志
     * @return 结果
     */
    @Override
    public int updateWpPriceLog(WpPriceLog wpPriceLog)
    {
        return wpPriceLogMapper.updateWpPriceLog(wpPriceLog);
    }

    /**
     * 删除资金日志对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWpPriceLogByIds(String ids)
    {
        return wpPriceLogMapper.deleteWpPriceLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除资金日志信息
     * 
     * @param id 资金日志ID
     * @return 结果
     */
    public int deleteWpPriceLogById(Integer id)
    {
        return wpPriceLogMapper.deleteWpPriceLogById(id);
    }

	@Override
	public int insertWpPriceLog(Integer uid, String money,Integer type, String title,
			String content, Integer oid, BigDecimal usermoney) {
		WpPriceLog vo = new WpPriceLog();
		vo.setAccount(money);
		vo.setContent(content);
		if(usermoney == null){
			WpUserinfo userinfo = this.userinfoMapper.selectWpUserinfoById(uid);
			vo.setNowmoney(userinfo.getUsermoney());
		}else{
			vo.setNowmoney(usermoney);
		}
		vo.setOid(oid);
		vo.setType(type);
		vo.setUid(uid);
		vo.setTitle(title);
		vo.setTime(System.currentTimeMillis()/1000);
		return insertWpPriceLog(vo);
	}

	@Override
	public List<PriceLogResp> selectPriceLogList(WpPriceLog wpPriceLog) {
		return this.wpPriceLogMapper.selectPriceLogList(wpPriceLog);
	}
}
