package com.wjyoption.system.mapper;

import com.wjyoption.system.domain.WpConfig;
import java.util.List;

/**
 * 系统配置Mapper接口
 * 
 * @author wjyoption
 * @date 2021-06-08
 */
public interface WpConfigMapper 
{
    /**
     * 查询系统配置
     * 
     * @param id 系统配置ID
     * @return 系统配置
     */
    public WpConfig selectWpConfigById(Integer id);

    /**
     * 查询系统配置列表
     * 
     * @param wpConfig 系统配置
     * @return 系统配置集合
     */
    public List<WpConfig> selectWpConfigList(WpConfig wpConfig);

    /**
     * 新增系统配置
     * 
     * @param wpConfig 系统配置
     * @return 结果
     */
    public int insertWpConfig(WpConfig wpConfig);

    /**
     * 修改系统配置
     * 
     * @param wpConfig 系统配置
     * @return 结果
     */
    public int updateWpConfig(WpConfig wpConfig);

    /**
     * 删除系统配置
     * 
     * @param id 系统配置ID
     * @return 结果
     */
    public int deleteWpConfigById(Integer id);

    /**
     * 批量删除系统配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpConfigByIds(String[] ids);
    
    
    
    
    
    
    /*********************************API*****************************************/
    
    /**
     * 根据名称获取key值
     * @param key
     * @return
     */
    public WpConfig selectWpConfigByKey(String key);
}
