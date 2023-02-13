package com.wjyoption.system.mapper;

import com.wjyoption.system.domain.SysPayType;
import java.util.List;

/**
 * 支付方式Mapper接口
 * 
 * @author wjyoption
 * @date 2021-06-26
 */
public interface SysPayTypeMapper 
{
    /**
     * 查询支付方式
     * 
     * @param id 支付方式ID
     * @return 支付方式
     */
    public SysPayType selectSysPayTypeById(Long id);
    public SysPayType selectSysPayTypeByCode(String code);

    /**
     * 查询支付方式列表
     * 
     * @param sysPayType 支付方式
     * @return 支付方式集合
     */
    public List<SysPayType> selectSysPayTypeList(SysPayType sysPayType);

    /**
     * 新增支付方式
     * 
     * @param sysPayType 支付方式
     * @return 结果
     */
    public int insertSysPayType(SysPayType sysPayType);

    /**
     * 修改支付方式
     * 
     * @param sysPayType 支付方式
     * @return 结果
     */
    public int updateSysPayType(SysPayType sysPayType);

    /**
     * 删除支付方式
     * 
     * @param id 支付方式ID
     * @return 结果
     */
    public int deleteSysPayTypeById(Long id);

    /**
     * 批量删除支付方式
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysPayTypeByIds(String[] ids);
}
