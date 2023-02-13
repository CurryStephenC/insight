package com.wjyoption.system.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.core.domain.Response;
import com.wjyoption.common.core.text.Convert;
import com.wjyoption.common.enums.ErrorConstants;
import com.wjyoption.common.utils.DateUtils;
import com.wjyoption.common.vo.PayNotifyVo;
import com.wjyoption.system.domain.CodepayOrder;
import com.wjyoption.system.domain.WpFinancialRecord;
import com.wjyoption.system.domain.WpUserNovicetaskRate;
import com.wjyoption.system.domain.WpUserinfo;
import com.wjyoption.system.mapper.CodepayOrderMapper;
import com.wjyoption.system.service.ICodepayOrderService;
import com.wjyoption.system.service.IWpCashFlowService;
import com.wjyoption.system.service.IWpFinancialRecordService;
import com.wjyoption.system.service.IWpPriceLogService;
import com.wjyoption.system.service.IWpUserNovicetaskRateService;
import com.wjyoption.system.service.IWpUserinfoService;
import com.wjyoption.system.vo.report.CodepayOrderTotal;

/**
 * 支付Service业务层处理
 * 
 * @author wjyoption
 * @date 2021-06-07
 */
@Service
public class CodepayOrderServiceImpl implements ICodepayOrderService 
{
    @Autowired
    private CodepayOrderMapper codepayOrderMapper;
    @Autowired IWpUserinfoService userinfoService;
    @Autowired IWpCashFlowService cashFlowService;
    @Autowired IWpPriceLogService priceLogService;
    @Autowired IWpFinancialRecordService financialRecordService;
    @Autowired IWpUserNovicetaskRateService novicetaskRateService;
    
    private static Logger logger = LoggerFactory.getLogger(CodepayOrderServiceImpl.class);

    /**
     * 查询支付
     * 
     * @param id 支付ID
     * @return 支付
     */
    @Override
    public CodepayOrder selectCodepayOrderById(Long id)
    {
        return codepayOrderMapper.selectCodepayOrderById(id);
    }

    /**
     * 查询支付列表
     * 
     * @param codepayOrder 支付
     * @return 支付
     */
    @Override
    public List<CodepayOrder> selectCodepayOrderList(CodepayOrder codepayOrder)
    {
        return codepayOrderMapper.selectCodepayOrderList(codepayOrder);
    }

    /**
     * 新增支付
     * 
     * @param codepayOrder 支付
     * @return 结果
     */
    @Override
    public int insertCodepayOrder(CodepayOrder codepayOrder)
    {
        return codepayOrderMapper.insertCodepayOrder(codepayOrder);
    }

    /**
     * 修改支付
     * 
     * @param codepayOrder 支付
     * @return 结果
     */
    @Override
    public int updateCodepayOrder(CodepayOrder codepayOrder)
    {
        return codepayOrderMapper.updateCodepayOrder(codepayOrder);
    }

    /**
     * 删除支付对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCodepayOrderByIds(String ids)
    {
        return codepayOrderMapper.deleteCodepayOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除支付信息
     * 
     * @param id 支付ID
     * @return 结果
     */
    public int deleteCodepayOrderById(Long id)
    {
        return codepayOrderMapper.deleteCodepayOrderById(id);
    }

	@Override
	public CodepayOrder selectCodepayOrderByOrderid(String pay_no) {
		return this.codepayOrderMapper.selectCodepayOrderByOrderid(pay_no);
	}

	@Override
	public CodepayOrder selectCodepayOrderByThirdid(String thirdid) {
		return this.codepayOrderMapper.selectCodepayOrderByThirdid(thirdid);
	}

	@Override
	@Transactional
	public void payNotify(PayNotifyVo vo, Response<String> response) {
//		if(!PayUtil.compareSign(vo)){
//			ErrorConstants.setResponse(response, ErrorConstants.FAIL);
//			logger.info("签名错误");
//			return;
//		}
		CodepayOrder order = this.codepayOrderMapper.selectCodepayOrderByOrderid(vo.getOrderNo());
		if(order == null){
			ErrorConstants.setResponse(response, ErrorConstants.FAIL);
			logger.info("订单不存在");
			return;
		}
		if(order.getStatus() != 0){
			return;
		}
		if("0".equals(vo.getState())){
			order.setStatus(1);
		}else{
			order.setStatus(2);
		}
		order.setThirdid(vo.getTrade_no());
		order.setUpTime(DateUtils.dateTimeNow(DateUtils.YYYY_MM_DD_HH_MM_SS));
		if(order.getMoney().compareTo(vo.getPay_amount()) != 0){
			order.setMoney(vo.getPay_amount());
		}
		this.codepayOrderMapper.updateCodepayOrder(order);
		if(order.getStatus() == 1){
			WpUserinfo userinfo = this.userinfoService.selectWpUserinfoById(order.getPayId());
			this.userinfoService.updateMoney(order.getMoney(), order.getPayId());
			this.cashFlowService.insertWpCashFlow(order.getPayId(), 5, order.getMoney().toString(), "充值", userinfo.getUsermoney().add(order.getMoney()));
			if(order.getComenewtask() == 1){
				WpFinancialRecord wpFinancialRecord = new WpFinancialRecord();
				wpFinancialRecord.setUid(order.getPayId());
				wpFinancialRecord.setComenewtask(1);
				List<WpFinancialRecord> list = this.financialRecordService.selectWpFinancialRecordList(wpFinancialRecord);
				if(list == null || list.size() == 0){
					return;
				}
				int taskid = 4;
				WpUserNovicetaskRate bean = this.novicetaskRateService.selectWpUserNovicetaskRateByTaskidUid(order.getPayId(), taskid);
				long now = DateUtils.getNowSecond();
				if(bean == null){
					bean = new WpUserNovicetaskRate();
					bean.setTaskid(taskid);
					bean.setUid(order.getPayId());
					bean.setCreatetime(now);
				}else if(bean.getStatus() == 3){
					return;
				}
				bean.setStatus(3);
				bean.setFinishtime(now);
				if(bean.getId() == 0){
					this.novicetaskRateService.insertWpUserNovicetaskRate(bean);
				}else{
					this.novicetaskRateService.updateWpUserNovicetaskRate(bean);
				}
			}
		}
		
		
	}

	@Override
	public BigDecimal selectTotalMoney(Integer uid) {
		return this.codepayOrderMapper.selectTotalMoney(uid);
	}
	
	@Override
	public CodepayOrderTotal selectCodepayOrderTotal(CodepayOrder codepayOrder) {
		return this.codepayOrderMapper.selectCodepayOrderTotal(codepayOrder);
	}
	
	@Override
	@Transactional
	public AjaxResult auditChongzhi(CodepayOrder codepayOrder) {
		if(codepayOrder.getId() == null || codepayOrder.getStatus() == null || codepayOrder.getStatus() > 2
				|| codepayOrder.getMoney() == null || codepayOrder.getPrice() == null){
			return AjaxResult.error("参数不对");
		}
		CodepayOrder order = this.codepayOrderMapper.selectCodepayOrderById(codepayOrder.getId());
		if(order==null || order.getStatus() != 0){
			return AjaxResult.error("无法操作");
		}
		if(codepayOrder.getStatus() == 2){
			order.setStatus(2);
			this.codepayOrderMapper.updateCodepayOrder(order);
			return AjaxResult.success();
		}
		if(codepayOrder.getStatus() != 1){
			return AjaxResult.error("没有此类更新");
		}
		WpUserinfo userinfo = this.userinfoService.selectWpUserinfoById(order.getPayId());
		order.setStatus(1);
		order.setMoney(codepayOrder.getMoney());
		order.setPrice(codepayOrder.getPrice());
		order.setOperatorid(codepayOrder.getOperatorid());
		order.setUpTime(DateUtils.dateTimeNow(DateUtils.YYYY_MM_DD_HH_MM_SS));
		this.codepayOrderMapper.updateCodepayOrder(order);
		this.userinfoService.updateMoney(codepayOrder.getMoney(), order.getPayId());
		this.cashFlowService.insertWpCashFlow(userinfo.getUid(), 5, codepayOrder.getMoney().toString(), "审核充值", userinfo.getUsermoney().add(codepayOrder.getMoney()));
		return AjaxResult.success();
	}
}
