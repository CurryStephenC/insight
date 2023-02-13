package com.wjyoption.system.service.impl;

import java.util.List;
import com.wjyoption.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wjyoption.system.mapper.SysWebsitBannerMapper;
import com.wjyoption.system.domain.SysWebsitBanner;
import com.wjyoption.system.service.ISysWebsitBannerService;
import com.wjyoption.common.core.text.Convert;

/**
 * 网站bannerService业务层处理
 * 
 * @author hs
 * @date 2020-02-07
 */
@Service
public class SysWebsitBannerServiceImpl implements ISysWebsitBannerService 
{
    @Autowired
    private SysWebsitBannerMapper sysWebsitBannerMapper;

    /**
     * 查询网站banner
     * 
     * @param id 网站bannerID
     * @return 网站banner
     */
    @Override
    public SysWebsitBanner selectSysWebsitBannerById(Integer id)
    {
        return sysWebsitBannerMapper.selectSysWebsitBannerById(id);
    }

    /**
     * 查询网站banner列表
     * 
     * @param sysWebsitBanner 网站banner
     * @return 网站banner
     */
    @Override
    public List<SysWebsitBanner> selectSysWebsitBannerList(SysWebsitBanner sysWebsitBanner)
    {
        return sysWebsitBannerMapper.selectSysWebsitBannerList(sysWebsitBanner);
    }

    /**
     * 新增网站banner
     * 
     * @param sysWebsitBanner 网站banner
     * @return 结果
     */
    @Override
    public int insertSysWebsitBanner(SysWebsitBanner sysWebsitBanner)
    {
        sysWebsitBanner.setCreateTime(DateUtils.getNowDate());
        return sysWebsitBannerMapper.insertSysWebsitBanner(sysWebsitBanner);
    }

    /**
     * 修改网站banner
     * 
     * @param sysWebsitBanner 网站banner
     * @return 结果
     */
    @Override
    public int updateSysWebsitBanner(SysWebsitBanner sysWebsitBanner)
    {
        sysWebsitBanner.setUpdateTime(DateUtils.getNowDate());
        return sysWebsitBannerMapper.updateSysWebsitBanner(sysWebsitBanner);
    }

    /**
     * 删除网站banner对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysWebsitBannerByIds(String ids)
    {
        return sysWebsitBannerMapper.deleteSysWebsitBannerByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除网站banner信息
     * 
     * @param id 网站bannerID
     * @return 结果
     */
    public int deleteSysWebsitBannerById(Integer id)
    {
        return sysWebsitBannerMapper.deleteSysWebsitBannerById(id);
    }
}
