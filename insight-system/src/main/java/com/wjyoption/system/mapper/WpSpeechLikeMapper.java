package com.wjyoption.system.mapper;

import com.wjyoption.system.domain.WpSpeechLike;
import java.util.List;

/**
 * 言论模块点赞Mapper接口
 * 
 * @author wjyoption
 * @date 2021-06-17
 */
public interface WpSpeechLikeMapper 
{
    /**
     * 查询言论模块点赞
     * 
     * @param id 言论模块点赞ID
     * @return 言论模块点赞
     */
    public WpSpeechLike selectWpSpeechLikeById(Integer id);
    public WpSpeechLike selectUserLike(WpSpeechLike wpSpeechLike);

    /**
     * 查询言论模块点赞列表
     * 
     * @param wpSpeechLike 言论模块点赞
     * @return 言论模块点赞集合
     */
    public List<WpSpeechLike> selectWpSpeechLikeList(WpSpeechLike wpSpeechLike);

    /**
     * 新增言论模块点赞
     * 
     * @param wpSpeechLike 言论模块点赞
     * @return 结果
     */
    public int insertWpSpeechLike(WpSpeechLike wpSpeechLike);

    /**
     * 修改言论模块点赞
     * 
     * @param wpSpeechLike 言论模块点赞
     * @return 结果
     */
    public int updateWpSpeechLike(WpSpeechLike wpSpeechLike);

    /**
     * 删除言论模块点赞
     * 
     * @param id 言论模块点赞ID
     * @return 结果
     */
    public int deleteWpSpeechLikeById(Integer id);

    /**
     * 批量删除言论模块点赞
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpSpeechLikeByIds(String[] ids);
}
