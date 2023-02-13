package com.wjyoption.system.mapper;

import com.wjyoption.system.domain.WpProductinfo;
import com.wjyoption.system.vo.resp.ProductResp;

import java.util.List;

/**
 * 产品列表Mapper接口
 * 
 * @author wjyoption
 * @date 2021-06-19
 */
public interface WpProductinfoMapper 
{
    /**
     * 查询产品列表
     * 
     * @param pid 产品列表ID
     * @return 产品列表
     */
    public WpProductinfo selectWpProductinfoById(Integer pid);

    /**
     * 查询产品列表列表
     * 
     * @param wpProductinfo 产品列表
     * @return 产品列表集合
     */
    public List<WpProductinfo> selectWpProductinfoList(WpProductinfo wpProductinfo);

    /**
     * 新增产品列表
     * 
     * @param wpProductinfo 产品列表
     * @return 结果
     */
    public int insertWpProductinfo(WpProductinfo wpProductinfo);

    /**
     * 修改产品列表
     * 
     * @param wpProductinfo 产品列表
     * @return 结果
     */
    public int updateWpProductinfo(WpProductinfo wpProductinfo);

    /**
     * 删除产品列表
     * 
     * @param pid 产品列表ID
     * @return 结果
     */
    public int deleteWpProductinfoById(Long pid);

    /**
     * 批量删除产品列表
     * 
     * @param pids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpProductinfoByIds(String[] pids);

    /**
     * API获取所有产品
     */
	public List<ProductResp> queryAllProduct();
}
