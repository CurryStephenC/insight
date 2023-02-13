package com.wjyoption.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wjyoption.common.config.Global;
import com.wjyoption.common.json.JSON;
import com.wjyoption.common.json.JSONObject;
import com.wjyoption.common.utils.http.HttpUtils;
import com.wjyoption.common.utils.ip.IPSeeker;

/**
 * 获取地址类
 * 
 * @author hs
 */
public class AddressUtils
{
    private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);

    public static final String IP_URL = "http://ip.taobao.com/service/getIpInfo.php";

    public static String getRealAddressByIP(String ip)
    {
        String address = "XX XX";
        
        // 内网不查询
        if (IpUtils.internalIp(ip))
        {
            return "内网IP";
        }
        if (Global.isAddressEnabled())
        {
        	String add = IPSeeker.getInstance().getAddress(ip);
        	if(!StringUtils.equals(add, IPSeeker.COUNTRY_AREA)){
        		return add;
        	}
            String rspStr = HttpUtils.sendPost(IP_URL, "ip=" + ip);
            if (StringUtils.isEmpty(rspStr))
            {
                log.error("获取地理位置异常 {}", ip);
                return address;
            }
            JSONObject obj;
            try
            {
                obj = JSON.unmarshal(rspStr, JSONObject.class);
                JSONObject data = obj.getObj("data");
                String region = data.getStr("region");
                String city = data.getStr("city");
                address = region + " " + city;
            }
            catch (Exception e)
            {
                log.error("获取地理位置异常 {}", ip);
            }
        }
        return address;
    }
}
