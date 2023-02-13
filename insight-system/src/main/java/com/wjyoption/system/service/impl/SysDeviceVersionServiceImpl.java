package com.wjyoption.system.service.impl;

import java.util.List;

import com.wjyoption.common.utils.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wjyoption.system.mapper.SysDeviceVersionMapper;
import com.wjyoption.system.domain.SysDeviceVersion;
import com.wjyoption.system.service.ISysDeviceVersionService;
import com.wjyoption.common.core.text.Convert;

/**
 * 设备版本信息Service业务层处理
 * 
 * @author wjyoption
 * @date 2021-06-11
 */
@Service
public class SysDeviceVersionServiceImpl implements ISysDeviceVersionService 
{
    @Autowired
    private SysDeviceVersionMapper sysDeviceVersionMapper;

    /**
     * 查询设备版本信息
     * 
     * @param id 设备版本信息ID
     * @return 设备版本信息
     */
    @Override
    public SysDeviceVersion selectSysDeviceVersionById(Integer id)
    {
        return sysDeviceVersionMapper.selectSysDeviceVersionById(id);
    }

    /**
     * 查询设备版本信息列表
     * 
     * @param sysDeviceVersion 设备版本信息
     * @return 设备版本信息
     */
    @Override
    public List<SysDeviceVersion> selectSysDeviceVersionList(SysDeviceVersion sysDeviceVersion)
    {
        return sysDeviceVersionMapper.selectSysDeviceVersionList(sysDeviceVersion);
    }

    /**
     * 新增设备版本信息
     * 
     * @param sysDeviceVersion 设备版本信息
     * @return 结果
     */
    @Override
    public int insertSysDeviceVersion(SysDeviceVersion sysDeviceVersion)
    {
        sysDeviceVersion.setCreateTime(DateUtils.getNowDate());
        return sysDeviceVersionMapper.insertSysDeviceVersion(sysDeviceVersion);
    }

    /**
     * 修改设备版本信息
     * 
     * @param sysDeviceVersion 设备版本信息
     * @return 结果
     */
    @Override
    public int updateSysDeviceVersion(SysDeviceVersion sysDeviceVersion)
    {
        sysDeviceVersion.setUpdateTime(DateUtils.getNowDate());
        return sysDeviceVersionMapper.updateSysDeviceVersion(sysDeviceVersion);
    }

    /**
     * 删除设备版本信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysDeviceVersionByIds(String ids)
    {
        return sysDeviceVersionMapper.deleteSysDeviceVersionByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除设备版本信息信息
     * 
     * @param id 设备版本信息ID
     * @return 结果
     */
    public int deleteSysDeviceVersionById(Integer id)
    {
        return sysDeviceVersionMapper.deleteSysDeviceVersionById(id);
    }

	@Override
	public SysDeviceVersion selectSysDeviceVersionByDeviceid(String deviceid) {
		return this.sysDeviceVersionMapper.selectSysDeviceVersionByDeviceid(deviceid);
	}
}
