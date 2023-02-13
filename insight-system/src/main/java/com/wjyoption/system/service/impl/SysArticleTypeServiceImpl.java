package com.wjyoption.system.service.impl;

import java.util.List;
import com.wjyoption.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wjyoption.system.mapper.SysArticleTypeMapper;
import com.wjyoption.system.domain.SysArticleType;
import com.wjyoption.system.service.ISysArticleTypeService;
import com.wjyoption.common.core.text.Convert;

/**
 * 网站文章类型Service业务层处理
 * 
 * @author hs
 * @date 2020-02-06
 */
@Service
public class SysArticleTypeServiceImpl implements ISysArticleTypeService 
{
    @Autowired
    private SysArticleTypeMapper sysArticleTypeMapper;

    /**
     * 查询网站文章类型
     * 
     * @param id 网站文章类型ID
     * @return 网站文章类型
     */
    @Override
    public SysArticleType selectSysArticleTypeById(Long id)
    {
        return sysArticleTypeMapper.selectSysArticleTypeById(id);
    }

    /**
     * 查询网站文章类型列表
     * 
     * @param sysArticleType 网站文章类型
     * @return 网站文章类型
     */
    @Override
    public List<SysArticleType> selectSysArticleTypeList(SysArticleType sysArticleType)
    {
        return sysArticleTypeMapper.selectSysArticleTypeList(sysArticleType);
    }

    /**
     * 新增网站文章类型
     * 
     * @param sysArticleType 网站文章类型
     * @return 结果
     */
    @Override
    public int insertSysArticleType(SysArticleType sysArticleType)
    {
        sysArticleType.setCreateTime(DateUtils.getNowDate());
        return sysArticleTypeMapper.insertSysArticleType(sysArticleType);
    }

    /**
     * 修改网站文章类型
     * 
     * @param sysArticleType 网站文章类型
     * @return 结果
     */
    @Override
    public int updateSysArticleType(SysArticleType sysArticleType)
    {
        sysArticleType.setUpdateTime(DateUtils.getNowDate());
        return sysArticleTypeMapper.updateSysArticleType(sysArticleType);
    }

    /**
     * 删除网站文章类型对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysArticleTypeByIds(String ids)
    {
        return sysArticleTypeMapper.deleteSysArticleTypeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除网站文章类型信息
     * 
     * @param id 网站文章类型ID
     * @return 结果
     */
    public int deleteSysArticleTypeById(Long id)
    {
        return sysArticleTypeMapper.deleteSysArticleTypeById(id);
    }
}
