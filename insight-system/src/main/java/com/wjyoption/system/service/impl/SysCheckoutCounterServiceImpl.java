package com.wjyoption.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wjyoption.common.core.domain.Response;
import com.wjyoption.common.core.text.Convert;
import com.wjyoption.common.utils.DateUtils;
import com.wjyoption.common.vo.PayNotifyVo;
import com.wjyoption.system.domain.SysCheckoutCounter;
import com.wjyoption.system.mapper.SysCheckoutCounterMapper;
import com.wjyoption.system.service.ICodepayOrderService;
import com.wjyoption.system.service.ISysCheckoutCounterService;

/**
 * 收银台Service业务层处理
 * 
 * @author hs
 * @date 2021-06-25
 */
@Service
public class SysCheckoutCounterServiceImpl implements ISysCheckoutCounterService 
{
    @Autowired
    private SysCheckoutCounterMapper sysCheckoutCounterMapper;
    @Autowired ICodepayOrderService payOrderService;
    
    /**
     * 查询收银台
     * 
     * @param id 收银台ID
     * @return 收银台
     */
    @Override
    public SysCheckoutCounter selectSysCheckoutCounterById(Long id)
    {
        return sysCheckoutCounterMapper.selectSysCheckoutCounterById(id);
    }

    /**
     * 查询收银台列表
     * 
     * @param sysCheckoutCounter 收银台
     * @return 收银台
     */
    @Override
    public List<SysCheckoutCounter> selectSysCheckoutCounterList(SysCheckoutCounter sysCheckoutCounter)
    {
        return sysCheckoutCounterMapper.selectSysCheckoutCounterList(sysCheckoutCounter);
    }

    /**
     * 新增收银台
     * 
     * @param sysCheckoutCounter 收银台
     * @return 结果
     */
    @Override
    public int insertSysCheckoutCounter(SysCheckoutCounter sysCheckoutCounter)
    {
        sysCheckoutCounter.setCreateTime(DateUtils.getNowDate());
        return sysCheckoutCounterMapper.insertSysCheckoutCounter(sysCheckoutCounter);
    }

    /**
     * 修改收银台
     * 需推送结果给第三方
     * @param sysCheckoutCounter 收银台
     * @return 结果
     */
    @Override
    @Transactional
    public int updateSysCheckoutCounter(SysCheckoutCounter sysCheckoutCounter)
    {
        sysCheckoutCounter.setUpdateTime(DateUtils.getNowDate());
        int count = sysCheckoutCounterMapper.updateSysCheckoutCounter(sysCheckoutCounter);
        if(sysCheckoutCounter.getStatus() != 1){
        	SysCheckoutCounter bean = this.sysCheckoutCounterMapper.selectSysCheckoutCounterById(sysCheckoutCounter.getId());
        	PayNotifyVo vo = new PayNotifyVo();
        	vo.setOrderNo(bean.getOrderid());
        	vo.setPay_amount(bean.getRealmoney());
        	vo.setState(bean.getStatus() == 2 ? "0" : "1");
        	vo.setTrade_no(bean.getTransactionid());
        	this.payOrderService.payNotify(vo, new Response<String>());
        }
        return count;
    }
    
    
    @Override
    public int updateOnly(SysCheckoutCounter sysCheckoutCounter)
    {
    	sysCheckoutCounter.setUpdateTime(DateUtils.getNowDate());
    	return sysCheckoutCounterMapper.updateSysCheckoutCounter(sysCheckoutCounter);
    }
    
    

    /**
     * 删除收银台对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysCheckoutCounterByIds(String ids)
    {
        return sysCheckoutCounterMapper.deleteSysCheckoutCounterByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除收银台信息
     * 
     * @param id 收银台ID
     * @return 结果
     */
    public int deleteSysCheckoutCounterById(Long id)
    {
        return sysCheckoutCounterMapper.deleteSysCheckoutCounterById(id);
    }

	@Override
	public SysCheckoutCounter selectByOrderid(String orderid) {
		return this.sysCheckoutCounterMapper.selectByOrderid(orderid);
	}
}
