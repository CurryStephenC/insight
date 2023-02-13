package com.wjyoption.system.service.impl;

import java.util.List;

import com.wjyoption.common.utils.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wjyoption.system.mapper.SysArticleDetailMapper;
import com.wjyoption.system.domain.SysArticleDetail;
import com.wjyoption.system.service.ISysArticleDetailService;
import com.wjyoption.system.vo.ArticleDetailVo;
import com.wjyoption.system.vo.param.ArticleParam;
import com.wjyoption.common.core.text.Convert;

/**
 * 文章详情Service业务层处理
 * 
 * @author hs
 * @date 2020-02-06
 */
@Service
public class SysArticleDetailServiceImpl implements ISysArticleDetailService 
{
    @Autowired
    private SysArticleDetailMapper sysArticleDetailMapper;

    /**
     * 查询文章详情
     * 
     * @param id 文章详情ID
     * @return 文章详情
     */
    @Override
    public SysArticleDetail selectSysArticleDetailById(Integer id)
    {
        return sysArticleDetailMapper.selectSysArticleDetailById(id);
    }

    /**
     * 查询文章详情列表
     * 
     * @param sysArticleDetail 文章详情
     * @return 文章详情
     */
    @Override
    public List<SysArticleDetail> selectSysArticleDetailList(SysArticleDetail sysArticleDetail)
    {
        return sysArticleDetailMapper.selectSysArticleDetailList(sysArticleDetail);
    }

    /**
     * 新增文章详情
     * 
     * @param sysArticleDetail 文章详情
     * @return 结果
     */
    @Override
    public int insertSysArticleDetail(SysArticleDetail sysArticleDetail)
    {
        sysArticleDetail.setCreateTime(DateUtils.getNowDate());
        sysArticleDetail.setUpdateTime(sysArticleDetail.getCreateTime());
        return sysArticleDetailMapper.insertSysArticleDetail(sysArticleDetail);
    }

    /**
     * 修改文章详情
     * 
     * @param sysArticleDetail 文章详情
     * @return 结果
     */
    @Override
    public int updateSysArticleDetail(SysArticleDetail sysArticleDetail)
    {
        sysArticleDetail.setUpdateTime(DateUtils.getNowDate());
        return sysArticleDetailMapper.updateSysArticleDetail(sysArticleDetail);
    }

    /**
     * 删除文章详情对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysArticleDetailByIds(String ids)
    {
        return sysArticleDetailMapper.deleteSysArticleDetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除文章详情信息
     * 
     * @param id 文章详情ID
     * @return 结果
     */
    public int deleteSysArticleDetailById(Integer id)
    {
        return sysArticleDetailMapper.deleteSysArticleDetailById(id);
    }

	@Override
	public List<ArticleDetailVo> queryArticleList(ArticleParam param) {
		return this.sysArticleDetailMapper.queryArticleList(param);
	}

	@Override
	public List<ArticleDetailVo> queryArticleDetails(ArticleParam param) {
		return this.sysArticleDetailMapper.queryArticleDetails(param);
	}
}
