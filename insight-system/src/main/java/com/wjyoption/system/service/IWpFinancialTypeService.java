package com.wjyoption.system.service;

import com.wjyoption.system.domain.WpFinancialType;
import com.wjyoption.system.vo.param.FinancialTypeParam;
import com.wjyoption.system.vo.resp.FinancialTypeResp;

import java.util.List;

/**
 * 理财类型Service接口
 * 
 * @author wjyoption
 * @date 2021-06-04
 */
public interface IWpFinancialTypeService 
{
    /**
     * 查询理财类型
     * 
     * @param id 理财类型ID
     * @return 理财类型
     */
    public WpFinancialType selectWpFinancialTypeById(Integer id);

    /**
     * 查询理财类型列表
     * 
     * @param wpFinancialType 理财类型
     * @return 理财类型集合
     */
    public List<WpFinancialType> selectWpFinancialTypeList(WpFinancialType wpFinancialType);

    /**
     * 新增理财类型
     * 
     * @param wpFinancialType 理财类型
     * @return 结果
     */
    public int insertWpFinancialType(WpFinancialType wpFinancialType);

    /**
     * 修改理财类型
     * 
     * @param wpFinancialType 理财类型
     * @return 结果
     */
    public int updateWpFinancialType(WpFinancialType wpFinancialType);

    /**
     * 批量删除理财类型
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpFinancialTypeByIds(String ids);

    /**
     * 删除理财类型信息
     * 
     * @param id 理财类型ID
     * @return 结果
     */
    public int deleteWpFinancialTypeById(Integer id);
    
    
    /*******************API************************/
    
    /**
     * 
     * @param wpFinancialType
     * @return
     */
    public List<FinancialTypeResp> selectFinancialTypeResp(FinancialTypeParam wpFinancialType);
}
