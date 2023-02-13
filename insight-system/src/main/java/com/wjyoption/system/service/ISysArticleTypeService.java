package com.wjyoption.system.service;

import com.wjyoption.system.domain.SysArticleType;
import java.util.List;

/**
 * 网站文章类型Service接口
 * 
 * @author hs
 * @date 2020-02-06
 */
public interface ISysArticleTypeService 
{
    /**
     * 查询网站文章类型
     * 
     * @param id 网站文章类型ID
     * @return 网站文章类型
     */
    public SysArticleType selectSysArticleTypeById(Long id);

    /**
     * 查询网站文章类型列表
     * 
     * @param sysArticleType 网站文章类型
     * @return 网站文章类型集合
     */
    public List<SysArticleType> selectSysArticleTypeList(SysArticleType sysArticleType);

    /**
     * 新增网站文章类型
     * 
     * @param sysArticleType 网站文章类型
     * @return 结果
     */
    public int insertSysArticleType(SysArticleType sysArticleType);

    /**
     * 修改网站文章类型
     * 
     * @param sysArticleType 网站文章类型
     * @return 结果
     */
    public int updateSysArticleType(SysArticleType sysArticleType);

    /**
     * 批量删除网站文章类型
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysArticleTypeByIds(String ids);

    /**
     * 删除网站文章类型信息
     * 
     * @param id 网站文章类型ID
     * @return 结果
     */
    public int deleteSysArticleTypeById(Long id);
}
