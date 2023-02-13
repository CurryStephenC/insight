package com.wjyoption.system.service;

import com.wjyoption.common.core.domain.Response;
import com.wjyoption.system.domain.WpSpeech;
import com.wjyoption.system.vo.resp.SpeechResp;

import java.util.List;

/**
 * 言论Service接口
 * 
 * @author wjyoption
 * @date 2021-06-15
 */
public interface IWpSpeechService 
{
    /**
     * 查询言论
     * 
     * @param id 言论ID
     * @return 言论
     */
    public WpSpeech selectWpSpeechById(Integer id);

    /**
     * 查询言论列表
     * 
     * @param wpSpeech 言论
     * @return 言论集合
     */
    public List<WpSpeech> selectWpSpeechList(WpSpeech wpSpeech);

    /**
     * 新增言论
     * 
     * @param wpSpeech 言论
     * @return 结果
     */
    public int insertWpSpeech(WpSpeech wpSpeech);

    /**
     * 修改言论
     * 
     * @param wpSpeech 言论
     * @return 结果
     */
    public int updateWpSpeech(WpSpeech wpSpeech);

    /**
     * 批量删除言论
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpSpeechByIds(String ids);

    /**
     * 删除言论信息
     * 
     * @param id 言论ID
     * @return 结果
     */
    public int deleteWpSpeechById(Integer id);

    /*********************************************API****************************************************/
    /**
     * api评论列表
     * @param speech
     * @return
     */
	public List<SpeechResp> selectSpeechResp(WpSpeech speech);

	/**
	 * 更新点赞/评论数
	 * @param refid id
	 * @param likeNum 点赞数需要增加的次数(减少则为负数) 
	 * @param commentNum 评论数需要增加的次数 
	 */
	public void updateNum(Integer refid, Integer likeNum,Integer commentNum);

	/**
	 * 发布言论
	 * @param content
	 * @param response
	 */
	public void releaseSpeech(String content, Response<Object> response);
}
