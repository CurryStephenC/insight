package com.wjyoption.system.service;

import com.wjyoption.common.core.domain.Response;
import com.wjyoption.system.domain.WpOrder;
import com.wjyoption.system.vo.report.ProductNowDataVo;
import com.wjyoption.system.vo.resp.OrderResp;

import java.util.List;
import java.util.Map;

/**
 * 订单模块Service接口
 * 
 * @author wjyoption
 * @date 2021-07-08
 */
public interface IWpOrderService 
{
    /**
     * 查询订单模块
     * 
     * @param oid 订单模块ID
     * @return 订单模块
     */
    public WpOrder selectWpOrderById(Integer oid);

    /**
     * 查询订单模块列表
     * 
     * @param wpOrder 订单模块
     * @return 订单模块集合
     */
    public List<WpOrder> selectWpOrderList(WpOrder wpOrder);
    public Map<String, Object> selectWpOrderListTotal(WpOrder wpOrder);

    /**
     * 新增订单模块
     * 
     * @param wpOrder 订单模块
     * @return 结果
     */
    public int insertWpOrder(WpOrder wpOrder);

    /**
     * 修改订单模块
     * 
     * @param wpOrder 订单模块
     * @return 结果
     */
    public int updateWpOrder(WpOrder wpOrder);

    /**
     * 批量删除订单模块
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpOrderByIds(String ids);

    /**
     * 删除订单模块信息
     * 
     * @param oid 订单模块ID
     * @return 结果
     */
    public int deleteWpOrderById(Integer oid);

    
    /************************************************API***********************************************************/
    
    /**
     * 获取订单列表
     * @param order
     * @return
     */
	public List<OrderResp> selectOrderList(WpOrder order);

	/**
	 * 下单
	 * @param order
	 * @param response
	 */
	public void placeOrder(OrderResp order, Response<String> response);

	/**
	 * 结算订单
	 * @param nowData
	 */
	public void overOrder(Map<String, ProductNowDataVo> nowData);
}
