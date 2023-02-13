package com.wjyoption.system.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wjyoption.system.mapper.StatUserMapper;
import com.wjyoption.system.service.IStatUserService;
import com.wjyoption.system.vo.report.StatuserReport;

@Service
public class StatUserService implements IStatUserService {

	@Autowired StatUserMapper statUserMapper;

	@Override
	public List<StatuserReport> selectDeptStat(StatuserReport report) {
		StatuserReport param = new StatuserReport();
		param.setBegintime(report.getBegintime());
		param.setEndtime(report.getEndtime());
		
		List<StatuserReport> list = this.statUserMapper.selectAllDept();
		BigDecimal defaultMoney = new BigDecimal(0);
		for(StatuserReport bean : list){
			param.setTopids(Arrays.asList(bean.getUids().split(",")));
			StatuserReport recharge = this.statUserMapper.selectRecharge(param);
			StatuserReport withdraw = this.statUserMapper.selectWithdraw(param);
			
			bean.setRegistnum(this.statUserMapper.selectRegisterNum(param));
			bean.setRechargemoney(Optional.ofNullable(recharge.getRechargemoney()).orElse(defaultMoney).setScale(2, BigDecimal.ROUND_HALF_UP));
			bean.setRechargenum(recharge.getRechargenum());
			bean.setWithdrawmoney(Optional.ofNullable(withdraw.getWithdrawmoney()).orElse(defaultMoney).setScale(2, BigDecimal.ROUND_HALF_UP));
			bean.setWithdrawnum(withdraw.getWithdrawnum());
			bean.setSubtract(bean.getRechargemoney().subtract(bean.getWithdrawmoney()));
		}
		list.sort(Comparator.comparing(StatuserReport::getRegistnum).reversed());
		return list;
	}

	@Override
	public List<StatuserReport> selectDeptDetail(StatuserReport report) {
		StatuserReport param = new StatuserReport();
		param.setBegintime(report.getBegintime());
		param.setEndtime(report.getEndtime());
		
		List<StatuserReport> list = this.statUserMapper.selectTopList(report.getDeptId());
		BigDecimal defaultMoney = new BigDecimal(0);
		for(StatuserReport bean : list){
			param.setTopid(bean.getUid());
			StatuserReport recharge = this.statUserMapper.selectRecharge(param);
			StatuserReport withdraw = this.statUserMapper.selectWithdraw(param);
			
			bean.setRegistnum(this.statUserMapper.selectRegisterNum(param));
			bean.setRechargemoney(Optional.ofNullable(recharge.getRechargemoney()).orElse(defaultMoney).setScale(2, BigDecimal.ROUND_HALF_UP));
			bean.setRechargenum(recharge.getRechargenum());
			bean.setWithdrawmoney(Optional.ofNullable(withdraw.getWithdrawmoney()).orElse(defaultMoney).setScale(2, BigDecimal.ROUND_HALF_UP));
			bean.setWithdrawnum(withdraw.getWithdrawnum());
			bean.setSubtract(bean.getRechargemoney().subtract(bean.getWithdrawmoney()));
		}
		list.sort(Comparator.comparing(StatuserReport::getRegistnum).reversed());
		return list;
	}
	

}
