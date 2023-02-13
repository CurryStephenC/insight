//package com.wjyoption.quartz.task.order;
//
//
//
//import java.net.URI;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//import org.apache.commons.lang3.StringUtils;
//import org.java_websocket.WebSocket;
//import org.java_websocket.client.WebSocketClient;
//import org.java_websocket.drafts.Draft_6455;
//import org.java_websocket.handshake.ServerHandshake;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.data.redis.core.BoundValueOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.wjyoption.common.utils.db.RedisEnum;
//import com.wjyoption.common.utils.http.HttpUtils;
//import com.wjyoption.system.domain.WpProductinfo;
//import com.wjyoption.system.service.IWpProductdataService;
//import com.wjyoption.system.service.IWpProductinfoService;
//import com.wjyoption.system.vo.report.ProductNowDataVo;
//
//@Component
//public class WebsocketClient implements CommandLineRunner{
//
////	private static Logger logger = LoggerFactory.getLogger(WebsocketClient.class);
//	private static final Logger logger = LoggerFactory.getLogger("sys-tcp");
//	private static long last_close_time = 0;
//	private static String keyPrefix = RedisEnum.KLINE_TOKEN.getKeyPrefix();//token缓存key
//	
//	private static Map<String, WpProductinfo> PRODUCT_MAP = new HashMap<>();
//	
//	/**
//	 * 接收数据开关
//	 */
//	public static boolean MSG_LOGGER_SWITCH = false;
//	
//	@SuppressWarnings("rawtypes")
//	@Autowired RedisTemplate redisTemplate = new RedisTemplate<>();
//	@Autowired IWpProductinfoService productinfoService;
//	@Autowired IWpProductdataService productdataService;
//	@Value("${wjyoption.tcpSwitch}")
//	private String tcpSwitch;
//	
//	@SuppressWarnings("unchecked")
//	public String token(){
//		BoundValueOperations<String,String> boundValueOps = redisTemplate.boundValueOps(keyPrefix);
//		String token = boundValueOps.get();
//		if(StringUtils.isNotBlank(token)){
//			return token;
//		}
//    	return updateToken();
//    }
//	
//	@SuppressWarnings("unchecked")
//	protected String updateToken(){
//		Map<String,String> json = new HashMap<>();
//    	json.put("u", "H1JqCXO");
//    	json.put("p", "sQJJumY");
//    	
//		try {
//			BoundValueOperations<String,String> boundValueOps = redisTemplate.boundValueOps(keyPrefix);
//			String res = HttpUtils.post("https://pricecloud.finpoints.com:18002/auth", json);
//			logger.info("token数据"+res);
//			JSONObject result = JSON.parseObject(res);
//			String token = result.getString("data");
//			boundValueOps.set(token, 50, TimeUnit.SECONDS);
//			return token;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	public static void main(String[] args) {
//		new WebsocketClient().queryMsg();
//	}
//	@Override
//	public void run(String... args){
//		if(PRODUCT_MAP.size() == 0){
//			updateProductMap();
//		}
//        queryMsg();
//    }
//	WebSocketClient client = null;
//	public void queryMsg() {
//		if("0".equals(tcpSwitch)){
//			return;
//		}
//		if(last_close_time != 0 && System.currentTimeMillis() - last_close_time < 10000){
//			updateToken();
//		}
//		try {
//			if(client == null || !client.isOpen())
//            client = new WebSocketClient(new URI("wss://pricecloud.finpoints.com:447/ws?depth=1&token=" + token()),new Draft_6455()) {
//                @Override
//                public void onOpen(ServerHandshake serverHandshake) {
//                	logger.info("握手成功");
//                }
//
//                @Override
//                public void onMessage(String msg) {
////                	{"symbol":"EURAUD","time":1626061910,"depth":1,
////                	"tick":[{"bid_price":1.58829,"bid_size":100000,"bid_originator":"LP-3","ask_price":1.5884,"ask_size":100000,"ask_originator":"LP-3"}]
////                	,"quote_time":0}
//                	if(MSG_LOGGER_SWITCH){
//                		logger.info("客户端收到消息："+msg);
//                	}
//                	try {
//                		if(StringUtils.isNotBlank(msg)){
//                			JSONObject json = JSON.parseObject(msg);
//                			if(json.containsKey("symbol")){
//                				String procode = json.getString("symbol");
//                				if(PRODUCT_MAP.containsKey(procode)){
//                					WpProductinfo productinfo = PRODUCT_MAP.get(procode);
//                					JSONArray array = json.getJSONArray("tick");
//                					JSONObject jsonObject = array.getJSONObject(0);
//                					ProductNowDataVo data = new ProductNowDataVo();
//                					data.setAsk_price(jsonObject.getBigDecimal("ask_price"));
//                					data.setBid_price(jsonObject.getBigDecimal("bid_price"));
//                					data.setSymbol(procode);
//                					data.setTime(json.getLong("time"));
//                					updateRedisValue(productinfo, data);
//                				}
//                			}
//                		}
//					} catch (Exception e) {
//						logger.error("处理数据错误...."+msg,e);
//					}
//                    
//                }
//
//                @Override
//                public void onClose(int i, String s, boolean b) {
//                	logger.info("链接已关闭");
//                    queryMsg();
//                    last_close_time = System.currentTimeMillis();
//                }
//
//                @Override
//                public void onError(Exception e){
//                    logger.error("发生错误已关闭",e);
//                    queryMsg();
//                    last_close_time = System.currentTimeMillis();
//                }
//            };
//        } catch (Exception e) {
//        	logger.error("client error...",e);
//        }
//
//		try {
//			if (!client.isOpen()) {
//	            if (client.getReadyState().equals(WebSocket.READYSTATE.NOT_YET_CONNECTED)) {
//                    client.connect();
//                    logger.info("client connect");
//	            } else if (client.getReadyState().equals(WebSocket.READYSTATE.CLOSING) || client.getReadyState().equals(WebSocket.READYSTATE.CLOSED)) {
//	                client.reconnect();
//	                logger.info("client reconnect");
//	            }
//	        }else{
//	        	logger.info("client open,no need open");
//	        }
//		} catch (Exception e) {
//			logger.error("connect or reconnect error...",e);
//		}
//	}
//	
//	@SuppressWarnings("unchecked")
//	public void updateRedisValue(WpProductinfo productinfo,ProductNowDataVo nowData){
//		String key = getRedisNowKey();
//		Integer pid = productinfo.getPid();
//		BoundValueOperations<String,Map<String,ProductNowDataVo>> boundValueOps = this.redisTemplate.boundValueOps(key);
//		Map<String, ProductNowDataVo> map = boundValueOps.get();
//		if(map == null){
//			map = new HashMap<String, ProductNowDataVo>();
//		}
//		map.put(pid.toString(), nowData);
//		boundValueOps.set(map);
//	}
//	
//	/**
//	 * key:pid
//	 * value:nowdata
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public Map<String,ProductNowDataVo> getNowData(){
//		String key = getRedisNowKey();
//		BoundValueOperations<String,Map<String,ProductNowDataVo>> boundValueOps = this.redisTemplate.boundValueOps(key);
//		return boundValueOps.get();
//	}
//	
//	public void updateProductMap(){
//		WpProductinfo wpProductinfo = new WpProductinfo();
//		wpProductinfo.setIsdelete(0);
//		List<WpProductinfo> productList = this.productinfoService.selectWpProductinfoList(wpProductinfo);
//		for(WpProductinfo product : productList){
//			PRODUCT_MAP.put(product.getProcode().toUpperCase(), product);
//		}
//	}
//	
//	private String getRedisNowKey(){
//		return RedisEnum.PRODUCT_NOWDATA.getKeyPrefix();
////		return productNowDataKey + "_" + pid;
//	}
//}