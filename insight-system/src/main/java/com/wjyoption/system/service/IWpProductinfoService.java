package com.wjyoption.system.service;

import com.wjyoption.system.domain.WpProductinfo;
import com.wjyoption.system.vo.resp.ProductResp;

import java.util.List;

/**
 * 产品列表Service接口
 * 
 * @author wjyoption
 * @date 2021-06-19
 */
public interface IWpProductinfoService 
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
     * 批量删除产品列表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpProductinfoByIds(String ids);

    /**
     * 删除产品列表信息
     * 
     * @param pid 产品列表ID
     * @return 结果
     */
    public int deleteWpProductinfoById(Long pid);
    
    
    /**
     * 获取所有上架产品列表
     * @return
     */
    public List<ProductResp> queryAllProduct();
    
    /**
     * 是否处于休市状态
     * @param pid
     * @param product
     * @return
     */
    public boolean canBuy(Integer pid,WpProductinfo product);
}
