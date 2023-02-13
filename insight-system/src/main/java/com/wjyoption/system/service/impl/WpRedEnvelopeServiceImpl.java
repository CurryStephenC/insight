package com.wjyoption.system.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wjyoption.system.mapper.WpRedEnvelopeMapper;
import com.wjyoption.system.domain.WpRedEnvelope;
import com.wjyoption.system.service.IWpRedEnvelopeService;
import com.wjyoption.common.core.text.Convert;

/**
 * 理财红包Service业务层处理
 * 
 * @author wjyoption
 * @date 2021-06-05
 */
@Service
public class WpRedEnvelopeServiceImpl implements IWpRedEnvelopeService 
{
    @Autowired
    private WpRedEnvelopeMapper wpRedEnvelopeMapper;

    /**
     * 查询理财红包
     * 
     * @param id 理财红包ID
     * @return 理财红包
     */
    @Override
    public WpRedEnvelope selectWpRedEnvelopeById(Integer id)
    {
        return wpRedEnvelopeMapper.selectWpRedEnvelopeById(id);
    }

    /**
     * 查询理财红包列表
     * 
     * @param wpRedEnvelope 理财红包
     * @return 理财红包
     */
    @Override
    public List<WpRedEnvelope> selectWpRedEnvelopeList(WpRedEnvelope wpRedEnvelope)
    {
        return wpRedEnvelopeMapper.selectWpRedEnvelopeList(wpRedEnvelope);
    }

    /**
     * 新增理财红包
     * 
     * @param wpRedEnvelope 理财红包
     * @return 结果
     */
    @Override
    public int insertWpRedEnvelope(WpRedEnvelope wpRedEnvelope)
    {
        return wpRedEnvelopeMapper.insertWpRedEnvelope(wpRedEnvelope);
    }

    /**
     * 修改理财红包
     * 
     * @param wpRedEnvelope 理财红包
     * @return 结果
     */
    @Override
    public int updateWpRedEnvelope(WpRedEnvelope wpRedEnvelope)
    {
        return wpRedEnvelopeMapper.updateWpRedEnvelope(wpRedEnvelope);
    }

    /**
     * 删除理财红包对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWpRedEnvelopeByIds(String ids)
    {
        return wpRedEnvelopeMapper.deleteWpRedEnvelopeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除理财红包信息
     * 
     * @param id 理财红包ID
     * @return 结果
     */
    public int deleteWpRedEnvelopeById(Integer id)
    {
        return wpRedEnvelopeMapper.deleteWpRedEnvelopeById(id);
    }

	@Override
	public BigDecimal selectRedMoney(Integer uid) {
		return this.wpRedEnvelopeMapper.selectRedMoney(uid);
	}
}
