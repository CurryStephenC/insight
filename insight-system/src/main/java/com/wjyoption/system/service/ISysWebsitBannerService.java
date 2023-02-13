package com.wjyoption.system.service;

import com.wjyoption.system.domain.SysWebsitBanner;
import java.util.List;

/**
 * 网站bannerService接口
 * 
 * @author hs
 * @date 2020-02-07
 */
public interface ISysWebsitBannerService 
{
    /**
     * 查询网站banner
     * 
     * @param id 网站bannerID
     * @return 网站banner
     */
    public SysWebsitBanner selectSysWebsitBannerById(Integer id);

    /**
     * 查询网站banner列表
     * 
     * @param sysWebsitBanner 网站banner
     * @return 网站banner集合
     */
    public List<SysWebsitBanner> selectSysWebsitBannerList(SysWebsitBanner sysWebsitBanner);

    /**
     * 新增网站banner
     * 
     * @param sysWebsitBanner 网站banner
     * @return 结果
     */
    public int insertSysWebsitBanner(SysWebsitBanner sysWebsitBanner);

    /**
     * 修改网站banner
     * 
     * @param sysWebsitBanner 网站banner
     * @return 结果
     */
    public int updateSysWebsitBanner(SysWebsitBanner sysWebsitBanner);

    /**
     * 批量删除网站banner
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysWebsitBannerByIds(String ids);

    /**
     * 删除网站banner信息
     * 
     * @param id 网站bannerID
     * @return 结果
     */
    public int deleteSysWebsitBannerById(Integer id);
}
