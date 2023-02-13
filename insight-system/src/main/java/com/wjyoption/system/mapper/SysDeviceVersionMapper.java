package com.wjyoption.system.mapper;

import com.wjyoption.system.domain.SysDeviceVersion;
import java.util.List;

/**
 * 设备版本信息Mapper接口
 * 
 * @author wjyoption
 * @date 2021-06-11
 */
public interface SysDeviceVersionMapper 
{
    /**
     * 查询设备版本信息
     * 
     * @param id 设备版本信息ID
     * @return 设备版本信息
     */
    public SysDeviceVersion selectSysDeviceVersionById(Integer id);

    /**
     * 查询设备版本信息列表
     * 
     * @param sysDeviceVersion 设备版本信息
     * @return 设备版本信息集合
     */
    public List<SysDeviceVersion> selectSysDeviceVersionList(SysDeviceVersion sysDeviceVersion);

    /**
     * 新增设备版本信息
     * 
     * @param sysDeviceVersion 设备版本信息
     * @return 结果
     */
    public int insertSysDeviceVersion(SysDeviceVersion sysDeviceVersion);

    /**
     * 修改设备版本信息
     * 
     * @param sysDeviceVersion 设备版本信息
     * @return 结果
     */
    public int updateSysDeviceVersion(SysDeviceVersion sysDeviceVersion);

    /**
     * 删除设备版本信息
     * 
     * @param id 设备版本信息ID
     * @return 结果
     */
    public int deleteSysDeviceVersionById(Integer id);

    /**
     * 批量删除设备版本信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysDeviceVersionByIds(String[] ids);
    
    
    
    
    
    /**
     * 根据版本ID查询
     * @param deviceid
     * @return
     */
    public SysDeviceVersion selectSysDeviceVersionByDeviceid(String deviceid);
}
