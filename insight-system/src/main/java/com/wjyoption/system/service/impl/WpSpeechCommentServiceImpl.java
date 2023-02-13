package com.wjyoption.system.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wjyoption.common.core.domain.Response;
import com.wjyoption.common.core.text.Convert;
import com.wjyoption.common.utils.DateUtils;
import com.wjyoption.common.utils.ThreadLocals;
import com.wjyoption.common.vo.UserLogin;
import com.wjyoption.system.domain.WpSpeechComment;
import com.wjyoption.system.domain.WpSpeechLike;
import com.wjyoption.system.mapper.WpSpeechCommentMapper;
import com.wjyoption.system.service.IWpSpeechCommentService;
import com.wjyoption.system.service.IWpSpeechLikeService;
import com.wjyoption.system.service.IWpSpeechService;
import com.wjyoption.system.vo.resp.SpeechCommentResp;

/**
 * 言论评论Service业务层处理
 * 
 * @author wjyoption
 * @date 2021-06-17
 */
@Service
public class WpSpeechCommentServiceImpl implements IWpSpeechCommentService 
{
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(WpSpeechCommentServiceImpl.class);
    @Autowired
    private WpSpeechCommentMapper wpSpeechCommentMapper;
    
    @Autowired IWpSpeechLikeService likeService;
    @Autowired IWpSpeechService speechService;

    /**
     * 查询言论评论
     * 
     * @param id 言论评论ID
     * @return 言论评论
     */
    @Override
    public WpSpeechComment selectWpSpeechCommentById(Integer id)
    {
        return wpSpeechCommentMapper.selectWpSpeechCommentById(id);
    }

    /**
     * 查询言论评论列表
     * 
     * @param wpSpeechComment 言论评论
     * @return 言论评论
     */
    @Override
    public List<WpSpeechComment> selectWpSpeechCommentList(WpSpeechComment wpSpeechComment)
    {
        return wpSpeechCommentMapper.selectWpSpeechCommentList(wpSpeechComment);
    }

    /**
     * 新增言论评论
     * 
     * @param wpSpeechComment 言论评论
     * @return 结果
     */
    @Override
    public int insertWpSpeechComment(WpSpeechComment wpSpeechComment)
    {
    	if(wpSpeechComment.getCreatetime() == null){
    		wpSpeechComment.setCreatetime(DateUtils.getNowSecond());
    	}
        return wpSpeechCommentMapper.insertWpSpeechComment(wpSpeechComment);
    }

    /**
     * 修改言论评论
     * 
     * @param wpSpeechComment 言论评论
     * @return 结果
     */
    @Override
    @Transactional
    public int updateWpSpeechComment(WpSpeechComment wpSpeechComment)
    {
    	int count = wpSpeechCommentMapper.updateWpSpeechComment(wpSpeechComment);
    	if(wpSpeechComment.getStatus() != null && wpSpeechComment.getStatus() == 1){
    		WpSpeechComment bean = this.wpSpeechCommentMapper.selectWpSpeechCommentById(wpSpeechComment.getId());
    		this.speechService.updateNum(bean.getSid(), null, 1);
    	}
        return count;
    }

    /**
     * 删除言论评论对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWpSpeechCommentByIds(String ids)
    {
        return wpSpeechCommentMapper.deleteWpSpeechCommentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除言论评论信息
     * 
     * @param id 言论评论ID
     * @return 结果
     */
    public int deleteWpSpeechCommentById(Integer id)
    {
        return wpSpeechCommentMapper.deleteWpSpeechCommentById(id);
    }

	@Override
	public List<SpeechCommentResp> selectSpeechCommentResp(
			WpSpeechComment speech) {
		List<SpeechCommentResp> list = this.wpSpeechCommentMapper.selectSpeechCommentResp(speech);
		UserLogin user = ThreadLocals.getUser();
		if(user == null){
			return list;
		}
		WpSpeechLike wpSpeechLike = null;
		for(SpeechCommentResp bean : list){
			wpSpeechLike = new WpSpeechLike();
			wpSpeechLike.setUid(user.getUid());
			wpSpeechLike.setType(2);
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
		WpSpeechComment speech = new WpSpeechComment();
		speech.setId(refid);
		if(likeNum != null && likeNum != 0){
			speech.setLikenum(likeNum);
		}
		if(commentNum != null && commentNum != 0){
			speech.setCommentnum(commentNum);
		}
		this.wpSpeechCommentMapper.increaseOrDecrease(speech);
	}

	@Override
	public void comment(String content, Integer sid, Response<Object> response) {
		UserLogin user = ThreadLocals.getUser();
//		if(content.length() > 3000){
//			ErrorConstants.setResponse(response, ErrorConstants);
//		}
		WpSpeechComment bean = new WpSpeechComment();
		bean.setSid(sid);
		bean.setUid(user.getUid());
		bean.setPhone(user.getUtel().substring(0,3) + "****" + user.getUtel().substring(user.getUtel().length() - 3));
		bean.setPicurl(user.getUserphoto());
		bean.setContent(content);
		bean.setCreatetime(DateUtils.getNowSecond());
		this.insertWpSpeechComment(bean);
	}
}
