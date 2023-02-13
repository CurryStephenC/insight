package com.wjyoption.system.service;

import com.wjyoption.system.domain.SysAdvert;
import java.util.List;

/**
 * 广告Service接口
 * 
 * @author hs
 * @date 2019-09-19
 */
public interface ISysAdvertService 
{
    /**
     * 查询广告
     * 
     * @param id 广告ID
     * @return 广告
     */
    public SysAdvert selectSysAdvertById(Long id);

    /**
     * 查询广告列表
     * 
     * @param sysAdvert 广告
     * @return 广告集合
     */
    public List<SysAdvert> selectSysAdvertList(SysAdvert sysAdvert);

    /**
     * 新增广告
     * 
     * @param sysAdvert 广告
     * @return 结果
     */
    public int insertSysAdvert(SysAdvert sysAdvert);

    /**
     * 修改广告
     * 
     * @param sysAdvert 广告
     * @return 结果
     */
    public int updateSysAdvert(SysAdvert sysAdvert);

    /**
     * 批量删除广告
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysAdvertByIds(String ids);

    /**
     * 删除广告信息
     * 
     * @param id 广告ID
     * @return 结果
     */
    public int deleteSysAdvertById(Long id);
}
