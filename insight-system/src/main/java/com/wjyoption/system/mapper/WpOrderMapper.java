package com.wjyoption.system.mapper;

import com.wjyoption.system.domain.WpOrder;
import com.wjyoption.system.vo.resp.OrderResp;

import java.util.List;
import java.util.Map;

/**
 * 订单模块Mapper接口
 * 
 * @author wjyoption
 * @date 2021-07-08
 */
public interface WpOrderMapper 
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
     * 删除订单模块
     * 
     * @param oid 订单模块ID
     * @return 结果
     */
    public int deleteWpOrderById(Integer oid);

    /**
     * 批量删除订单模块
     * 
     * @param oids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpOrderByIds(String[] oids);

	public List<OrderResp> selectOrderList(WpOrder order);
}
