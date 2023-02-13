package com.wjyoption.system.mapper;

import java.util.List;

import com.wjyoption.system.domain.WpSpeechComment;
import com.wjyoption.system.vo.resp.SpeechCommentResp;

/**
 * 言论评论Mapper接口
 * 
 * @author wjyoption
 * @date 2021-06-17
 */
public interface WpSpeechCommentMapper 
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
     * 删除言论评论
     * 
     * @param id 言论评论ID
     * @return 结果
     */
    public int deleteWpSpeechCommentById(Integer id);

    /**
     * 批量删除言论评论
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpSpeechCommentByIds(String[] ids);

    
    
    /*******************************************API***********************************************/
    /**
     * 评论列表
     * @param speech
     * @return
     */
	public List<SpeechCommentResp> selectSpeechCommentResp(
			WpSpeechComment speech);
	
	public int increaseOrDecrease(WpSpeechComment speech);
}
