package com.wjyoption.system.service.impl;

import java.util.List;
import com.wjyoption.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wjyoption.system.mapper.SysAdvertMapper;
import com.wjyoption.system.domain.SysAdvert;
import com.wjyoption.system.service.ISysAdvertService;
import com.wjyoption.common.core.text.Convert;

/**
 * 广告Service业务层处理
 * 
 * @author hs
 * @date 2019-09-19
 */
@Service
public class SysAdvertServiceImpl implements ISysAdvertService 
{
    @Autowired
    private SysAdvertMapper sysAdvertMapper;

    /**
     * 查询广告
     * 
     * @param id 广告ID
     * @return 广告
     */
    @Override
    public SysAdvert selectSysAdvertById(Long id)
    {
        return sysAdvertMapper.selectSysAdvertById(id);
    }

    /**
     * 查询广告列表
     * 
     * @param sysAdvert 广告
     * @return 广告
     */
    @Override
    public List<SysAdvert> selectSysAdvertList(SysAdvert sysAdvert)
    {
        return sysAdvertMapper.selectSysAdvertList(sysAdvert);
    }

    /**
     * 新增广告
     * 
     * @param sysAdvert 广告
     * @return 结果
     */
    @Override
    public int insertSysAdvert(SysAdvert sysAdvert)
    {
        sysAdvert.setCreateTime(DateUtils.getNowDate());
        return sysAdvertMapper.insertSysAdvert(sysAdvert);
    }

    /**
     * 修改广告
     * 
     * @param sysAdvert 广告
     * @return 结果
     */
    @Override
    public int updateSysAdvert(SysAdvert sysAdvert)
    {
        sysAdvert.setUpdateTime(DateUtils.getNowDate());
        return sysAdvertMapper.updateSysAdvert(sysAdvert);
    }

    /**
     * 删除广告对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysAdvertByIds(String ids)
    {
        return sysAdvertMapper.deleteSysAdvertByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除广告信息
     * 
     * @param id 广告ID
     * @return 结果
     */
    public int deleteSysAdvertById(Long id)
    {
        return sysAdvertMapper.deleteSysAdvertById(id);
    }
}
