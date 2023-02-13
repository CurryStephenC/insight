package com.wjyoption.system.service;

import com.wjyoption.common.core.domain.Response;
import com.wjyoption.system.domain.WpSpeechComment;
import com.wjyoption.system.vo.resp.SpeechCommentResp;

import java.util.List;

/**
 * 言论评论Service接口
 * 
 * @author wjyoption
 * @date 2021-06-17
 */
public interface IWpSpeechCommentService 
{
    /**
     * 查询言论评论
     * 
     * @param id 言论评论ID
     * @return 言论评论
     */
    public WpSpeechComment selectWpSpeechCommentById(Integer id);

    /**
     * 查询言论评论列表
     * 
     * @param wpSpeechComment 言论评论
     * @return 言论评论集合
     */
    public List<WpSpeechComment> selectWpSpeechCommentList(WpSpeechComment wpSpeechComment);

    /**
     * 新增言论评论
     * 
     * @param wpSpeechComment 言论评论
     * @return 结果
     */
    public int insertWpSpeechComment(WpSpeechComment wpSpeechComment);

    /**
     * 修改言论评论
     * 
     * @param wpSpeechComment 言论评论
     * @return 结果
     */
    public int updateWpSpeechComment(WpSpeechComment wpSpeechComment);

    /**
     * 批量删除言论评论
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpSpeechCommentByIds(String ids);

    /**
     * 删除言论评论信息
     * 
     * @param id 言论评论ID
     * @return 结果
     */
    public int deleteWpSpeechCommentById(Integer id);

    
    /*******************************************API***********************************************/
    /**
     * 评论列表
     * @param speech
     * @return
     */
	public List<SpeechCommentResp> selectSpeechCommentResp(
			WpSpeechComment speech);

	/**
	 * 更新点赞/评论数
	 * @param refid id
	 * @param likeNum 点赞数需要增加的次数(减少则为负数) 
	 * @param commentNum 评论数需要增加的次数 
	 */
	public void updateNum(Integer refid, Integer likeNum, Integer commentNum);

	/**
	 * 评论言论
	 * @param content
	 * @param sid
	 * @param response
	 */
	public void comment(String content, Integer sid, Response<Object> response);
}
