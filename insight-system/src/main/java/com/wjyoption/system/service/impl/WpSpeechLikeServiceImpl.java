package com.wjyoption.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wjyoption.system.mapper.WpSpeechLikeMapper;
import com.wjyoption.system.domain.WpSpeechLike;
import com.wjyoption.system.service.IWpSpeechCommentReplyService;
import com.wjyoption.system.service.IWpSpeechCommentService;
import com.wjyoption.system.service.IWpSpeechLikeService;
import com.wjyoption.system.service.IWpSpeechService;
import com.wjyoption.common.core.text.Convert;
import com.wjyoption.common.utils.DateUtils;

/**
 * 言论模块点赞Service业务层处理
 * 
 * @author wjyoption
 * @date 2021-06-17
 */
@Service
public class WpSpeechLikeServiceImpl implements IWpSpeechLikeService 
{
    @Autowired
    private WpSpeechLikeMapper wpSpeechLikeMapper;
    @Autowired IWpSpeechService speechService;
    @Autowired IWpSpeechCommentService commentService;
    @Autowired IWpSpeechCommentReplyService replyService;

    /**
     * 查询言论模块点赞
     * 
     * @param id 言论模块点赞ID
     * @return 言论模块点赞
     */
    @Override
    public WpSpeechLike selectWpSpeechLikeById(Integer id)
    {
        return wpSpeechLikeMapper.selectWpSpeechLikeById(id);
    }

    /**
     * 查询言论模块点赞列表
     * 
     * @param wpSpeechLike 言论模块点赞
     * @return 言论模块点赞
     */
    @Override
    public List<WpSpeechLike> selectWpSpeechLikeList(WpSpeechLike wpSpeechLike)
    {
        return wpSpeechLikeMapper.selectWpSpeechLikeList(wpSpeechLike);
    }

    /**
     * 新增言论模块点赞
     * 
     * @param wpSpeechLike 言论模块点赞
     * @return 结果
     */
    @Override
    public int insertWpSpeechLike(WpSpeechLike wpSpeechLike)
    {
    	wpSpeechLike.setCreatetime(DateUtils.getNowSecond());
        return wpSpeechLikeMapper.insertWpSpeechLike(wpSpeechLike);
    }

    /**
     * 修改言论模块点赞
     * 
     * @param wpSpeechLike 言论模块点赞
     * @return 结果
     */
    @Override
    public int updateWpSpeechLike(WpSpeechLike wpSpeechLike)
    {
        return wpSpeechLikeMapper.updateWpSpeechLike(wpSpeechLike);
    }

    /**
     * 删除言论模块点赞对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWpSpeechLikeByIds(String ids)
    {
        return wpSpeechLikeMapper.deleteWpSpeechLikeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除言论模块点赞信息
     * 
     * @param id 言论模块点赞ID
     * @return 结果
     */
    public int deleteWpSpeechLikeById(Integer id)
    {
        return wpSpeechLikeMapper.deleteWpSpeechLikeById(id);
    }

	@Override
	public WpSpeechLike selectUserLike(WpSpeechLike wpSpeechLike) {
		return wpSpeechLikeMapper.selectUserLike(wpSpeechLike);
	}

	@Override
	@Transactional
	public void like(WpSpeechLike like) {
		WpSpeechLike bean = this.wpSpeechLikeMapper.selectUserLike(like);
		int num = 1;
		if(bean == null){
			this.insertWpSpeechLike(like);
		}else{
			this.wpSpeechLikeMapper.deleteWpSpeechLikeById(bean.getId());
			num = -1;
		}
		updateLikeNum(like.getRefid(), like.getType(), num);
		
	}
	private void updateLikeNum(Integer refid,Integer type,Integer num){
		if(type == 1){
			this.speechService.updateNum(refid,num,null);
		}else if(type == 2){
			this.commentService.updateNum(refid,num,null);
		}else{
			this.replyService.updateNum(refid,num,null);
		}
	}
}
