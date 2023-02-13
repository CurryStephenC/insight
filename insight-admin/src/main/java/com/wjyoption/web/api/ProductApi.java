package com.wjyoption.web.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.wjyoption.common.annotation.Login;
import com.wjyoption.common.annotation.NotNullParam;
import com.wjyoption.common.annotation.Sign;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.Response;
import com.wjyoption.common.core.page.PageDomain;
import com.wjyoption.common.utils.ThreadLocals;
import com.wjyoption.system.domain.WpOrder;
import com.wjyoption.system.service.IWpConfigService;
import com.wjyoption.system.service.IWpOrderService;
import com.wjyoption.system.service.IWpProductdataService;
import com.wjyoption.system.service.IWpProductinfoService;
import com.wjyoption.system.vo.report.ProductKlineVo;
import com.wjyoption.system.vo.resp.OrderResp;
import com.wjyoption.system.vo.resp.ProductResp;


@Api(tags={"产品模块"},value="产品")
@RestController
@RequestMapping("api/product")
@CrossOrigin
public class ProductApi  extends BaseController{

	@Autowired IWpProductinfoService productinfoService;
	@Autowired IWpProductdataService productdataService;
	@Autowired IWpOrderService orderService;
//	@Autowired WebsocketClient websocketClient;
	@Autowired IWpConfigService configService;
	
	@SuppressWarnings("rawtypes")
	@Autowired RedisTemplate redisTemplate;
	
	
//	@SuppressWarnings("deprecation")
//	@ApiOperation("获取token")
//	@RequestMapping(value = "token",method = {RequestMethod.POST})
//	@Sign
//	public Response<String> token(){
//		Response<String> response = new Response<>();
//		response.setData(websocketClient.token());
//		return response;
//	}
	
	@ApiOperation("产品列表")
	@RequestMapping(value = "productList",method = {RequestMethod.POST})
	@Sign
	public Response<List<ProductResp>> productList(){
		Response<List<ProductResp>> response = new Response<>();
		List<ProductResp> list = this.productinfoService.queryAllProduct();
		response.setData(list);
		return response;
	}
	
//	@ApiOperation("产品当前价格")
//	@RequestMapping(value = "productNowPrice",method = {RequestMethod.POST})
//	@Sign
//	public Response<List<ProductResp>> productNowPrice(){
//		Response<List<ProductResp>> response = new Response<>();
//		List<ProductResp> list = this.productinfoService.queryAllProduct();
//		if(list != null){
//			Map<String, ProductNowDataVo> map = this.websocketClient.getNowData();
//			if(map != null){
//				for(ProductResp p : list){
//					if(map.containsKey(p.getPid().toString())){
//						ProductNowDataVo nowData = map.get(p.getPid().toString());
//						p.setPrice(nowData.getBid_price());
//						p.setUpdatetime(nowData.getTime());
//					}
//				}
//			}
//		}
//		response.setData(list);
//		return response;
//	}
	
	@ApiOperation("产品K线数据")
	@RequestMapping(value = "kline",method = {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pid", value = "产品ID", dataType = "Integer",required=true),
		@ApiImplicitParam(name = "interval", value = "间隔如：1,15,30,60,D", dataType = "String",required=true),
	})
	@Sign
	@NotNullParam("pid||interval")
	public Response<List<ProductKlineVo>> kline(Integer pid,String interval){
		Response<List<ProductKlineVo>> response = new Response<>();
		response.setData(this.productdataService.queryKlineData(pid, interval.toUpperCase()));
		return response;
	}
	
	@ApiOperation("订单列表")
	@RequestMapping(value = "orderList",method = {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ostaus", value = "0交易，1平仓", dataType = "int"),
		@ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "int"),
		@ApiImplicitParam(name = "pageSize", value = "每页记录", dataType = "int"),
		@ApiImplicitParam(name = "orderByColumn", value = "排序字段：如 id", dataType = "String"),
		@ApiImplicitParam(name = "isAsc", value = "排序方式->顺序：asc,倒序：desc", dataType = "String"),
	})
	@Sign
	@Login
//	@NotNullParam("ostaus")
	public Response<List<OrderResp>> orderList(Integer ostaus){
		Response<List<OrderResp>> response = new Response<>();
		PageDomain page = startPageApi();
		WpOrder order = new WpOrder();
		order.setUid(ThreadLocals.getUser().getUid());
//		order.setUid(111);
		order.setOstaus(ostaus);
		List<OrderResp> list = this.orderService.selectOrderList(order);
		response.setData(list);
		if(page.getPageSize() != null){
			response.setTotal(new PageInfo<OrderResp>(list, page.getPageSize()).getTotal());
		}else
			response.setTotal((long) list.size());
		return response;
	}
	
	
//	@SuppressWarnings("unchecked")
//	@ApiOperation("下单")
//	@RequestMapping(value = "placeOrder",method = {RequestMethod.POST})
//	@Sign
//	@Login
//	@NotNullParam("pid||ostyle||endprofit||fee||buyprice")
//	public Response<String> placeOrder(OrderResp order){
//		Response<String> response = new Response<>();
//		String buyOrderSwitch = this.configService.selectWpConfigByKey("wp_order_buy_switch");//下单开关，0关，其他开
//		if(StringUtils.equals("0", buyOrderSwitch)){
//			ErrorConstants.setResponse(response, ErrorConstants.NO_TRADING_AUTHORITY);
//			return response;
//		}
//		order.setUid(ThreadLocals.getUser().getUid());
//		synchronized (order.getUid()) {
//			String key = RedisEnum.PRODUCT_PLACEORDER_LIMIT.getKeyPrefix() + "_" +order.getUid();
//			BoundValueOperations<String,String> boundValueOps = this.redisTemplate.boundValueOps(key);
//			if(StringUtils.isNotBlank(boundValueOps.get())){
//				ErrorConstants.setResponse(response, ErrorConstants.PRODUCT_PLACEORDER_TIME_SMALL);
//			}else{
//				Map<String, ProductNowDataVo> map = this.websocketClient.getNowData();
//				ProductNowDataVo nowData = map.get(order.getPid().toString());
//				if(nowData != null){
//					order.setBuyprice(nowData.getBid_price());
//				}
//				this.orderService.placeOrder(order,response);
//				boundValueOps.set(key, 5, TimeUnit.SECONDS);
//			}
//		}
//		return response;
//	}
	
//	@ApiOperation(value = "重连",hidden=true)
//	@RequestMapping("reconnect")
//	@Sign
//	public Response<String> reconnect(String pass){
//		Response<String> response = new Response<>();
//		if(!"021b3c3920b81fb098a2d02377c751b7".equalsIgnoreCase(pass)){
//			response.setMessage("--------");
//			return response;
//		}
//		websocketClient.queryMsg();
//		return response;
//	}
	
	
//	@ApiOperation(value = "日志开关",hidden=true)
//	@RequestMapping(value = "openLog",method = {RequestMethod.GET})
//	public Response<String> openLog(boolean loggerSwitch){
//		WebsocketClient.MSG_LOGGER_SWITCH = loggerSwitch;
//		return new Response<>();
//	}
	
	
	
}
