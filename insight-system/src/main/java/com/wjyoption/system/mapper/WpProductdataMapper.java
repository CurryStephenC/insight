package com.wjyoption.system.mapper;

import com.wjyoption.system.domain.WpProductdata;

import java.util.List;

/**
 * 产品数据Mapper接口
 * 
 * @author wjyoption
 * @date 2021-07-10
 */
public interface WpProductdataMapper 
{
    /**
     * 查询产品数据
     * 
     * @param id 产品数据ID
     * @return 产品数据
     */
    public WpProductdata selectWpProductdataById(Integer id);

    /**
     * 查询产品数据列表
     * 
     * @param wpProductdata 产品数据
     * @return 产品数据集合
     */
    public List<WpProductdata> selectWpProductdataList(WpProductdata wpProductdata);

    /**
     * 新增产品数据
     * 
     * @param wpProductdata 产品数据
     * @return 结果
     */
    public int insertWpProductdata(WpProductdata wpProductdata);

    /**
     * 修改产品数据
     * 
     * @param wpProductdata 产品数据
     * @return 结果
     */
    public int updateWpProductdata(WpProductdata wpProductdata);

    /**
     * 删除产品数据
     * 
     * @param id 产品数据ID
     * @return 结果
     */
    public int deleteWpProductdataById(Integer id);

    /**
     * 批量删除产品数据
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpProductdataByIds(String[] ids);

    /**
     * 根据产品ID获取产品
     * @param pid
     * @return
     */
	public WpProductdata selectWpProductdataByPid(Integer pid);

	public int updateWpProductdataByPid(WpProductdata wpProductdata);
}
