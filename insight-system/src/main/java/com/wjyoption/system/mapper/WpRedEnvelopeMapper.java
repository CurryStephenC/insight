package com.wjyoption.system.mapper;

import com.wjyoption.system.domain.WpRedEnvelope;

import java.math.BigDecimal;
import java.util.List;

/**
 * 理财红包Mapper接口
 * 
 * @author wjyoption
 * @date 2021-06-05
 */
public interface WpRedEnvelopeMapper 
{
    /**
     * 查询理财红包
     * 
     * @param id 理财红包ID
     * @return 理财红包
     */
    public WpRedEnvelope selectWpRedEnvelopeById(Integer id);

    /**
     * 查询理财红包列表
     * 
     * @param wpRedEnvelope 理财红包
     * @return 理财红包集合
     */
    public List<WpRedEnvelope> selectWpRedEnvelopeList(WpRedEnvelope wpRedEnvelope);

    /**
     * 新增理财红包
     * 
     * @param wpRedEnvelope 理财红包
     * @return 结果
     */
    public int insertWpRedEnvelope(WpRedEnvelope wpRedEnvelope);

    /**
     * 修改理财红包
     * 
     * @param wpRedEnvelope 理财红包
     * @return 结果
     */
    public int updateWpRedEnvelope(WpRedEnvelope wpRedEnvelope);

    /**
     * 删除理财红包
     * 
     * @param id 理财红包ID
     * @return 结果
     */
    public int deleteWpRedEnvelopeById(Integer id);

    /**
     * 批量删除理财红包
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpRedEnvelopeByIds(String[] ids);
    
    
    
    public BigDecimal selectRedMoney(Integer uid);
    
}
