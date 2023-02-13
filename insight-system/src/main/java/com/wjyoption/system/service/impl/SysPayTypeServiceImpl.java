package com.wjyoption.system.service.impl;

import java.util.List;

import com.wjyoption.common.utils.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wjyoption.system.mapper.SysPayTypeMapper;
import com.wjyoption.system.domain.SysPayType;
import com.wjyoption.system.service.ISysPayTypeService;
import com.wjyoption.common.core.text.Convert;

/**
 * 支付方式Service业务层处理
 * 
 * @author wjyoption
 * @date 2021-06-26
 */
@Service
public class SysPayTypeServiceImpl implements ISysPayTypeService 
{
    @Autowired
    private SysPayTypeMapper sysPayTypeMapper;

    /**
     * 查询支付方式
     * 
     * @param id 支付方式ID
     * @return 支付方式
     */
    @Override
    public SysPayType selectSysPayTypeById(Long id)
    {
        return sysPayTypeMapper.selectSysPayTypeById(id);
    }

    /**
     * 查询支付方式列表
     * 
     * @param sysPayType 支付方式
     * @return 支付方式
     */
    @Override
    public List<SysPayType> selectSysPayTypeList(SysPayType sysPayType)
    {
        return sysPayTypeMapper.selectSysPayTypeList(sysPayType);
    }

    /**
     * 新增支付方式
     * 
     * @param sysPayType 支付方式
     * @return 结果
     */
    @Override
    public int insertSysPayType(SysPayType sysPayType)
    {
        sysPayType.setCreateTime(DateUtils.getNowDate());
        return sysPayTypeMapper.insertSysPayType(sysPayType);
    }

    /**
     * 修改支付方式
     * 
     * @param sysPayType 支付方式
     * @return 结果
     */
    @Override
    public int updateSysPayType(SysPayType sysPayType)
    {
        sysPayType.setUpdateTime(DateUtils.getNowDate());
        return sysPayTypeMapper.updateSysPayType(sysPayType);
    }

    /**
     * 删除支付方式对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysPayTypeByIds(String ids)
    {
        return sysPayTypeMapper.deleteSysPayTypeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除支付方式信息
     * 
     * @param id 支付方式ID
     * @return 结果
     */
    public int deleteSysPayTypeById(Long id)
    {
        return sysPayTypeMapper.deleteSysPayTypeById(id);
    }

	@Override
	public SysPayType selectSysPayTypeByCode(String code) {
		return this.sysPayTypeMapper.selectSysPayTypeByCode(code);
	}
}
