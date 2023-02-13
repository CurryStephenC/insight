package com.wjyoption.system.service;

import java.util.List;

import com.wjyoption.system.domain.WpProductdata;
import com.wjyoption.system.vo.report.ProductKlineVo;

/**
 * 产品数据Service接口
 * 
 * @author wjyoption
 * @date 2021-07-10
 */
public interface IWpProductdataService 
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
     * 批量删除产品数据
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpProductdataByIds(String ids);

    /**
     * 删除产品数据信息
     * 
     * @param id 产品数据ID
     * @return 结果
     */
    public int deleteWpProductdataById(Integer id);
    /**
     * 获取K线图
     * @param pid
     * @param interval 1,15,30,60,d
     * @return
     */
    public List<ProductKlineVo> queryKlineData(Integer pid,String interval);

    /**
     * 根据产品ID获取对象
     * @param pid
     * @return
     */
	public WpProductdata selectWpProductdataByPid(Integer pid);

	/**
	 * 根据pid更新数据
	 * @param wpProductdata
	 * @return
	 */
	public int updateWpProductdataByPid(WpProductdata wpProductdata);
}
