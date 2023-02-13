package com.wjyoption.system.service;

import java.math.BigDecimal;
import java.util.List;

import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.core.domain.Response;
import com.wjyoption.common.vo.PayNotifyVo;
import com.wjyoption.system.domain.CodepayOrder;
import com.wjyoption.system.vo.report.CodepayOrderTotal;

/**
 * 支付Service接口
 * 
 * @author wjyoption
 * @date 2021-06-07
 */
public interface ICodepayOrderService 
{
    /**
     * 查询支付
     * 
     * @param id 支付ID
     * @return 支付
     */
    public CodepayOrder selectCodepayOrderById(Long id);

    /**
     * 查询支付列表
     * 
     * @param codepayOrder 支付
     * @return 支付集合
     */
    public List<CodepayOrder> selectCodepayOrderList(CodepayOrder codepayOrder);

    /**
     * 新增支付
     * 
     * @param codepayOrder 支付
     * @return 结果
     */
    public int insertCodepayOrder(CodepayOrder codepayOrder);

    /**
     * 修改支付
     * 
     * @param codepayOrder 支付
     * @return 结果
     */
    public int updateCodepayOrder(CodepayOrder codepayOrder);

    /**
     * 批量删除支付
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCodepayOrderByIds(String ids);

    /**
     * 删除支付信息
     * 
     * @param id 支付ID
     * @return 结果
     */
    public int deleteCodepayOrderById(Long id);
    
    
    public CodepayOrder selectCodepayOrderByOrderid(String pay_no);
    public CodepayOrder selectCodepayOrderByThirdid(String thirdid);

	public void payNotify(PayNotifyVo vo, Response<String> response);

	/**
	 * 获取用户充值总额
	 * @param uid
	 * @return
	 */
	public BigDecimal selectTotalMoney(Integer uid);
	
	
	/**
	 * 查询总计
	 * @param codepayOrder
	 * @return
	 */
	public CodepayOrderTotal selectCodepayOrderTotal(CodepayOrder codepayOrder);
	
	/**
	 * 审核充值
	 * @param codepayOrder
	 * @return
	 */
	public AjaxResult auditChongzhi(CodepayOrder codepayOrder);
}
