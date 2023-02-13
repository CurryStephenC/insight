package com.wjyoption.web.api.vericode;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class VeriCodeUtil implements ApplicationRunner {

	/**
	 * key:email
	 */
	private static Map<String, VeriCodeVo> veriCodeMap = new HashMap<>();
	
	public static void putVeri(String veriCode,String email){
		synchronized (veriCodeMap) {
			VeriCodeVo vo = new VeriCodeVo();
			vo.setEmail(email);
			vo.setVeriCode(veriCode);
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MINUTE, 10);
			vo.setOvertime(cal.getTime());
			veriCodeMap.put(email, vo);
		}
	}
	public static void removeEmail(String email){
		synchronized (veriCodeMap) {
			veriCodeMap.remove(email);
		}
	}
	public static String getVeriCode(String email){
		synchronized (veriCodeMap) {
			VeriCodeVo vo = veriCodeMap.get(email);
			if(vo == null){
				return null;
			}
			return vo.getVeriCode();
		}
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		new Thread(()->{
			while(true){
				if(veriCodeMap.isEmpty()){
					try {
						Thread.sleep(10*1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
					continue;
				}
				Date now = new Date();
				synchronized (veriCodeMap) {
					Set<String> keySet = veriCodeMap.keySet();
					for(String key : keySet){
						if(now.after(veriCodeMap.get(key).getOvertime())){
							veriCodeMap.remove(key);
						}
					}
				}
				try {
					Thread.sleep(20*1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

}
class VeriCodeVo{
	private String email;
	
	private String veriCode;
	
	private Date overtime;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVeriCode() {
		return veriCode;
	}

	public void setVeriCode(String veriCode) {
		this.veriCode = veriCode;
	}

	public Date getOvertime() {
		return overtime;
	}

	public void setOvertime(Date overtime) {
		this.overtime = overtime;
	}
}
