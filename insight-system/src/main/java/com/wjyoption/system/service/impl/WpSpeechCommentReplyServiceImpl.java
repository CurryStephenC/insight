package com.wjyoption.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wjyoption.common.core.domain.Response;
import com.wjyoption.common.core.text.Convert;
import com.wjyoption.common.enums.ErrorConstants;
import com.wjyoption.common.utils.DateUtils;
import com.wjyoption.common.utils.ThreadLocals;
import com.wjyoption.common.vo.UserLogin;
import com.wjyoption.system.domain.WpSpeechComment;
import com.wjyoption.system.domain.WpSpeechCommentReply;
import com.wjyoption.system.domain.WpSpeechLike;
import com.wjyoption.system.mapper.WpSpeechCommentReplyMapper;
import com.wjyoption.system.service.IWpSpeechCommentReplyService;
import com.wjyoption.system.service.IWpSpeechCommentService;
import com.wjyoption.system.service.IWpSpeechLikeService;
import com.wjyoption.system.vo.resp.SpeechReplyResp;

/**
 * 言论评论回复Service业务层处理
 * 
 * @author wjyoption
 * @date 2021-06-17
 */
@Service
public class WpSpeechCommentReplyServiceImpl implements IWpSpeechCommentReplyService 
{
    @Autowired
    private WpSpeechCommentReplyMapper wpSpeechCommentReplyMapper;
    @Autowired IWpSpeechLikeService likeService;
    @Autowired IWpSpeechCommentService commentService;

    /**
     * 查询言论评论回复
     * 
     * @param id 言论评论回复ID
     * @return 言论评论回复
     */
    @Override
    public WpSpeechCommentReply selectWpSpeechCommentReplyById(Integer id)
    {
        return wpSpeechCommentReplyMapper.selectWpSpeechCommentReplyById(id);
    }

    /**
     * 查询言论评论回复列表
     * 
     * @param wpSpeechCommentReply 言论评论回复
     * @return 言论评论回复
     */
    @Override
    public List<WpSpeechCommentReply> selectWpSpeechCommentReplyList(WpSpeechCommentReply wpSpeechCommentReply)
    {
        return wpSpeechCommentReplyMapper.selectWpSpeechCommentReplyList(wpSpeechCommentReply);
    }

    /**
     * 新增言论评论回复
     * 
     * @param wpSpeechCommentReply 言论评论回复
     * @return 结果
     */
    @Override
    public int insertWpSpeechCommentReply(WpSpeechCommentReply wpSpeechCommentReply)
    {
    	if(wpSpeechCommentReply.getCreatetime() != null){
    		wpSpeechCommentReply.setCreatetime(DateUtils.getNowSecond());
    	}
        return wpSpeechCommentReplyMapper.insertWpSpeechCommentReply(wpSpeechCommentReply);
    }

    /**
     * 修改言论评论回复
     * 
     * @param wpSpeechCommentReply 言论评论回复
     * @return 结果
     */
    @Override
    public int updateWpSpeechCommentReply(WpSpeechCommentReply wpSpeechCommentReply)
    {
    	int count = wpSpeechCommentReplyMapper.updateWpSpeechCommentReply(wpSpeechCommentReply);
    	if(wpSpeechCommentReply.getStatus() != null && wpSpeechCommentReply.getStatus() == 1){
    		WpSpeechCommentReply bean = this.wpSpeechCommentReplyMapper.selectWpSpeechCommentReplyById(wpSpeechCommentReply.getId());
    		this.commentService.updateNum(bean.getCid(), null, 1);
    	}
        return count;
    }

    /**
     * 删除言论评论回复对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWpSpeechCommentReplyByIds(String ids)
    {
        return wpSpeechCommentReplyMapper.deleteWpSpeechCommentReplyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除言论评论回复信息
     * 
     * @param id 言论评论回复ID
     * @return 结果
     */
    public int deleteWpSpeechCommentReplyById(Integer id)
    {
        return wpSpeechCommentReplyMapper.deleteWpSpeechCommentReplyById(id);
    }

	@Override
	public List<SpeechReplyResp> selectSpeechReplyResp(
			WpSpeechCommentReply speech) {
		List<SpeechReplyResp> list = this.wpSpeechCommentReplyMapper.selectSpeechReplyResp(speech);
		UserLogin user = ThreadLocals.getUser();
		if(user == null){
			return list;
		}
		WpSpeechLike wpSpeechLike = null;
		for(SpeechReplyResp bean : list){
			wpSpeechLike = new WpSpeechLike();
			wpSpeechLike.setUid(user.getUid());
			wpSpeechLike.setType(3);
			wpSpeechLike.setRefid(bean.getId());
			WpSpeechLike like = this.likeService.selectUserLike(wpSpeechLike);
			if(like != null){
				bean.setLike(1);
			}
		}
		return list;
	}
	
	@Override
	public void updateNum(Integer refid, Integer likeNum,Integer commentNum) {
		WpSpeechCommentReply speech = new WpSpeechCommentReply();
		speech.setId(refid);
		if(likeNum != null && likeNum != 0){
			speech.setLikenum(likeNum);
		}
		if(commentNum != null && commentNum != 0){
			speech.setCommentnum(commentNum);
		}
		this.wpSpeechCommentReplyMapper.increaseOrDecrease(speech);
	}

	@Override
	public void reply(String content, Integer cid,Integer toreplyid, Response<Object> response) {
		WpSpeechComment comment = this.commentService.selectWpSpeechCommentById(cid);
		if(comment == null || comment.getStatus() != 1){
			ErrorConstants.setResponse(response, ErrorConstants.RECORD_NOT_EXISTS);
			return;
		}
		UserLogin user = ThreadLocals.getUser();
		WpSpeechCommentReply bean = new WpSpeechCommentReply();
		bean.setUid(user.getUid());
		bean.setCid(cid);
		bean.setSid(comment.getSid());
		bean.setPhone(user.getUtel().substring(0,3) + "****" + user.getUtel().substring(user.getUtel().length() - 3));
		bean.setPicurl(user.getUserphoto());
		bean.setContent(content);
		bean.setCreatetime(DateUtils.getNowSecond());
		if(toreplyid != null && toreplyid != 0){
			WpSpeechCommentReply replyBean = this.wpSpeechCommentReplyMapper.selectWpSpeechCommentReplyById(toreplyid);
			if(replyBean != null){
				bean.setToreplyid(toreplyid);
				bean.setReplyphone(replyBean.getPhone());
				bean.setTouid(replyBean.getUid());
			}
		}
		this.insertWpSpeechCommentReply(bean);
	}
}
