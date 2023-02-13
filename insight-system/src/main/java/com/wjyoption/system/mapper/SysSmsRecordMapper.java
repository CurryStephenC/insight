package com.wjyoption.system.mapper;

import com.wjyoption.system.domain.SysSmsRecord;
import java.util.List;

/**
 * 短信发送记录Mapper接口
 * 
 * @author hs
 * @date 2020-04-29
 */
public interface SysSmsRecordMapper 
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
     * 修改短信发送记录
     * 
     * @param sysSmsRecord 短信发送记录
     * @return 结果
     */
    public int updateSysSmsRecord(SysSmsRecord sysSmsRecord);

    /**
     * 删除短信发送记录
     * 
     * @param id 短信发送记录ID
     * @return 结果
     */
    public int deleteSysSmsRecordById(Integer id);

    /**
     * 批量删除短信发送记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysSmsRecordByIds(String[] ids);
}
