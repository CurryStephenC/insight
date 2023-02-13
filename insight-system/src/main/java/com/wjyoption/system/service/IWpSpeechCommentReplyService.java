package com.wjyoption.system.service;

import com.wjyoption.common.core.domain.Response;
import com.wjyoption.system.domain.WpSpeechCommentReply;
import com.wjyoption.system.vo.resp.SpeechReplyResp;

import java.util.List;

/**
 * 言论评论回复Service接口
 * 
 * @author wjyoption
 * @date 2021-06-17
 */
public interface IWpSpeechCommentReplyService 
{
    /**
     * 查询言论评论回复
     * 
     * @param id 言论评论回复ID
     * @return 言论评论回复
     */
    public WpSpeechCommentReply selectWpSpeechCommentReplyById(Integer id);

    /**
     * 查询言论评论回复列表
     * 
     * @param wpSpeechCommentReply 言论评论回复
     * @return 言论评论回复集合
     */
    public List<WpSpeechCommentReply> selectWpSpeechCommentReplyList(WpSpeechCommentReply wpSpeechCommentReply);

    /**
     * 新增言论评论回复
     * 
     * @param wpSpeechCommentReply 言论评论回复
     * @return 结果
     */
    public int insertWpSpeechCommentReply(WpSpeechCommentReply wpSpeechCommentReply);

    /**
     * 修改言论评论回复
     * 
     * @param wpSpeechCommentReply 言论评论回复
     * @return 结果
     */
    public int updateWpSpeechCommentReply(WpSpeechCommentReply wpSpeechCommentReply);

    /**
     * 批量删除言论评论回复
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpSpeechCommentReplyByIds(String ids);

    /**
     * 删除言论评论回复信息
     * 
     * @param id 言论评论回复ID
     * @return 结果
     */
    public int deleteWpSpeechCommentReplyById(Integer id);

    /**************************************API**********************************************/
    
    /**
     * 回复列表
     * @param speech
     * @return
     */
	public List<SpeechReplyResp> selectSpeechReplyResp(
			WpSpeechCommentReply speech);
	
	/**
	 * 更新点赞/评论数
	 * @param refid id
	 * @param likeNum 点赞数需要增加的次数(减少则为负数) 
	 * @param commentNum 评论数需要增加的次数 
	 */
	public void updateNum(Integer refid, Integer likeNum,Integer commentNum);

	/**
	 * 回复
	 * @param content
	 * @param cid
	 * @param response
	 */
	public void reply(String content, Integer cid,Integer toreplyid, Response<Object> response);
}
