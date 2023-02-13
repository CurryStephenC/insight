package com.wjyoption.system.mapper;

import com.wjyoption.system.domain.WpSpeech;
import com.wjyoption.system.vo.resp.SpeechResp;

import java.util.List;

/**
 * 言论Mapper接口
 * 
 * @author wjyoption
 * @date 2021-06-15
 */
public interface WpSpeechMapper 
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
     * 删除言论
     * 
     * @param id 言论ID
     * @return 结果
     */
    public int deleteWpSpeechById(Integer id);

    /**
     * 批量删除言论
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpSpeechByIds(String[] ids);

    
    /*********************************************API****************************************************/
    /**
     * api评论列表
     * @param speech
     * @return
     */
	public List<SpeechResp> selectSpeechResp(WpSpeech speech);

	public int increaseOrDecrease(WpSpeech speech);
}
