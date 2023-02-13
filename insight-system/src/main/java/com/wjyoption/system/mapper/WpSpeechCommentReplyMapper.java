package com.wjyoption.system.mapper;

import java.util.List;

import com.wjyoption.system.domain.WpSpeechCommentReply;
import com.wjyoption.system.vo.resp.SpeechReplyResp;

/**
 * 言论评论回复Mapper接口
 * 
 * @author wjyoption
 * @date 2021-06-17
 */
public interface WpSpeechCommentReplyMapper 
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
     * 删除言论评论回复
     * 
     * @param id 言论评论回复ID
     * @return 结果
     */
    public int deleteWpSpeechCommentReplyById(Integer id);

    /**
     * 批量删除言论评论回复
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpSpeechCommentReplyByIds(String[] ids);

    /**************************************API**********************************************/
    
    /**
     * 回复列表
     * @param speech
     * @return
     */
	public List<SpeechReplyResp> selectSpeechReplyResp(
			WpSpeechCommentReply speech);
	
	public int increaseOrDecrease(WpSpeechCommentReply speech);
}
