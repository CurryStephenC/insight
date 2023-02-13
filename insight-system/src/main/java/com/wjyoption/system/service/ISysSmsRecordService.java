package com.wjyoption.system.service;

import java.util.List;

import com.wjyoption.common.core.domain.Response;
import com.wjyoption.system.domain.SysSmsRecord;

/**
 * 短信发送记录Service接口
 * 
 * @author hs
 * @date 2020-04-29
 */
public interface ISysSmsRecordService 
{
    /**
     * 查询短信发送记录
     * 
     * @param id 短信发送记录ID
     * @return 短信发送记录
     */
    public SysSmsRecord selectSysSmsRecordById(Integer id);

    /**
     * 查询短信发送记录列表
     * 
     * @param sysSmsRecord 短信发送记录
     * @return 短信发送记录集合
     */
    public List<SysSmsRecord> selectSysSmsRecordList(SysSmsRecord sysSmsRecord);

    /**
     * 新增短信发送记录
     * 
     * @param sysSmsRecord 短信发送记录
     * @return 结果
     */
    public int insertSysSmsRecord(SysSmsRecord sysSmsRecord);
    
    /**
     * 发送短信
     * @param template 短信模板
     * @param areaCode 区号
     * @param phone		发送号码
     * @param source	来源
     * @param smstype	短信类型：1、验证码，2、营销
     * @param sensitive	模板中需要替换的数据
     */
    public void sendMsg(Response<?> response,String template,String areaCode,String phone,String source,Integer smstype,Object... sensitive);
    

}
