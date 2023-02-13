package com.wjyoption.system.service;

import com.wjyoption.system.domain.SysArticleDetail;
import com.wjyoption.system.vo.ArticleDetailVo;
import com.wjyoption.system.vo.param.ArticleParam;

import java.util.List;

/**
 * 文章详情Service接口
 * 
 * @author hs
 * @date 2020-02-06
 */
public interface ISysArticleDetailService 
{
    /**
     * 查询文章详情
     * 
     * @param id 文章详情ID
     * @return 文章详情
     */
    public SysArticleDetail selectSysArticleDetailById(Integer id);

    /**
     * 查询文章详情列表
     * 
     * @param sysArticleDetail 文章详情
     * @return 文章详情集合
     */
    public List<SysArticleDetail> selectSysArticleDetailList(SysArticleDetail sysArticleDetail);

    /**
     * 新增文章详情
     * 
     * @param sysArticleDetail 文章详情
     * @return 结果
     */
    public int insertSysArticleDetail(SysArticleDetail sysArticleDetail);

    /**
     * 修改文章详情
     * 
     * @param sysArticleDetail 文章详情
     * @return 结果
     */
    public int updateSysArticleDetail(SysArticleDetail sysArticleDetail);

    /**
     * 批量删除文章详情
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysArticleDetailByIds(String ids);

    /**
     * 删除文章详情信息
     * 
     * @param id 文章详情ID
     * @return 结果
     */
    public int deleteSysArticleDetailById(Integer id);

    
    
    /***********************************API***************************************/
    
	public List<ArticleDetailVo> queryArticleList(ArticleParam param);
	public List<ArticleDetailVo> queryArticleDetails(ArticleParam param);
}
